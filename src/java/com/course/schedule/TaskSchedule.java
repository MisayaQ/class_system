package com.course.schedule;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.enums.ScheduleType;
import com.course.utils.ScheduleUtil;
import com.course.utils.UUIDUtil;
import com.course.utils.feignDto.MTNotificationSendReqDTO;
import com.course.utils.outClient.MTCotactApiRequestClient;
import com.course.utils.outClient.MTNotificationApiRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class TaskSchedule {

    @Value("${server.ip}")
    private String serverIp;

    @Autowired
    private IScheduleThingService thingService;

    @Autowired
    private IScheduleThingEventService eventService;

    @Autowired
    private IScheduleQuartzRemindService quartzRemindService;

    @Autowired
    private IScheduleThingHandleService thingHandleService;

    @Autowired
    private MTCotactApiRequestClient mtCotactApiRequestClient;

    @Autowired
    private MTNotificationApiRequestClient mtNotificationApiRequestClient;

    Logger logger = LoggerFactory.getLogger(TaskSchedule.class);

    /**
     * 扫描当天未开始的日程
     * 处理内容：到点自动变更状态为：进行中
     * ++扫描频率：每秒一次，每15分钟节点处理一次
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void handleThingEvent(){
        Date now = new Date();
        String minute = DateUtil.format(now,"mm");
        String second = DateUtil.format(now,"ss");
        if(/*Integer.parseInt(minute) % 15 == 0 && */"00".equalsIgnoreCase(second)){
            QueryWrapper qw = new QueryWrapper();
            qw.notIn("status",Arrays.asList(new String[]{"-1","-2"}));
            List<ThingDO> thingDOList = thingService.list(qw);
            List<ThingDO> result = ScheduleUtil.findScheduleArr(thingDOList,DateUtil.format(now,"yyyy-MM-dd"));
            for(ThingDO thingDO : result){
                Date newStartTime = thingDO.getStartTime();
                if(newStartTime.equals(now) || now.after(newStartTime)){
                    QueryWrapper eventQw = new QueryWrapper();
                    eventQw.eq("thing_id",thingDO.getThingId());
                    eventQw.eq("event_day",DateUtil.format(now,"yyyy-MM-dd"));
                    List<ThingEventDO> eventDOList = eventService.list(eventQw);
                    if(eventDOList == null || eventDOList.isEmpty()){
                        ThingEventDO thingEventDO = ThingEventDO.builder()
                                .eventId(UUIDUtil.getUUID())
                                .thingId(thingDO.getThingId())
                                .eventDay(DateUtil.format(now,"yyyy-MM-dd"))
                                .eventStatus("1")
                                .build();
                        eventService.save(thingEventDO);
                    }
                }
            }
        }

    }


    /**
     * 扫描所有的日程提醒信息，到点发送提醒信息，然后清除掉
     * ++扫描频率：每秒一次，每5分钟节点处理一次
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void handleThingRemind(){
        Date now = new Date();
        String minute = DateUtil.format(now,"mm");
        String second = DateUtil.format(now,"ss");
        if(Integer.parseInt(minute) % 5 == 0 && "00".equalsIgnoreCase(second)){
            QueryWrapper remindQw = new QueryWrapper();
            remindQw.eq("remind_time",DateUtil.format(now,"yyyy-MM-dd HH:mm:ss"));
            List<QuartzRemind> quartzRemindList = quartzRemindService.list(remindQw);
            //发送提醒消息
            List<String> thingIdList = new ArrayList<>();
            Map<String,String> thingSubjectMap = new HashMap<>();
            Map<String,String> thingTypeMap = new HashMap<>();
            Map<String,String> thingCreatorMap = new HashMap<>();
            for(QuartzRemind quartzRemind : quartzRemindList){
                thingIdList.add(quartzRemind.getThingId());
            }
            List<ThingHandleDO> thingHandleDOList = new ArrayList<>();
            if(!thingIdList.isEmpty()){
                //查询日程信息
                QueryWrapper thingQw = new QueryWrapper();
                thingQw.in("id",thingIdList);
                List<ThingDO> thingDOList = thingService.list(thingQw);
                for(ThingDO thingDO : thingDOList){
                    thingSubjectMap.put(thingDO.getThingId(),thingDO.getSubject());
                    thingTypeMap.put(thingDO.getThingId(),thingDO.getType());
                    thingCreatorMap.put(thingDO.getThingId(),thingDO.getCreator());
                }
                //查询日程参与人
                QueryWrapper handleQw = new QueryWrapper();
                handleQw.in("thing_id",thingIdList);
                handleQw.eq("status","1");
                thingHandleDOList = thingHandleService.list(handleQw);
            }
            for(QuartzRemind quartzRemind : quartzRemindList){
                String tingType = "1".equals(thingTypeMap.get(quartzRemind.getThingId())) ? ScheduleType.SCHEDULE.toString() : ScheduleType.MEETING.toString();
                String tingTypeName = "1".equals(thingTypeMap.get(quartzRemind.getThingId())) ? "日程" : "会议";
                List<String> joinPersonList = new ArrayList<>();
                for(ThingHandleDO thingHandleDO : thingHandleDOList){
                    if(quartzRemind.getThingId().equalsIgnoreCase(thingHandleDO.getThingId())){
                        joinPersonList.add(thingHandleDO.getParticipant());
                    }
                }
                String url;
                if("1".equals(thingTypeMap.get(quartzRemind.getThingId()))){
                    url = serverIp + "/schedule/scheduleDetails?thingId=" + quartzRemind.getThingId() + "&currentDate=" + quartzRemind.getDay() + "&from=notice";
                }else{
                    url = serverIp + "/meeting/meetingDetails?thingId=" + quartzRemind.getThingId() + "&currentDate=" + quartzRemind.getDay() + "&from=notice";
                }
                Map<String,String> map = new HashMap<>();
                map.put("url",url);
                map.put("iosUrl",url);
                map.put("createName",tingTypeName);
                //给创建人发送消息
                String creatorContent = "您的 " + thingSubjectMap.get(quartzRemind.getThingId()) + " " + tingTypeName + quartzRemind.getRemindType() +  "后开始。";
                if("0分钟".equalsIgnoreCase(quartzRemind.getRemindType())){
                    creatorContent = "您的 " + thingSubjectMap.get(quartzRemind.getThingId()) + " " + tingTypeName  +  "已经开始了。";
                }
                MTNotificationSendReqDTO createMt = MTNotificationSendReqDTO.builder()
                        .receiverIds(Arrays.asList(new String[]{thingCreatorMap.get(quartzRemind.getThingId())}))
                        .content(creatorContent)
                        .corpId(quartzRemind.getCorpId())
                        .properties(map)
                        .build();
                mtNotificationApiRequestClient.send(createMt, tingType);

                //给参与人发送消息
                Set<String> userSet = new HashSet<>();
                userSet.add(thingCreatorMap.get(quartzRemind.getThingId()));
                Map<String,String> userMap = mtCotactApiRequestClient.userMapGets(userSet,quartzRemind.getCorpId());
                String joinContent = userMap.get(thingCreatorMap.get(quartzRemind.getThingId())) + " 发起的 " + thingSubjectMap.get(quartzRemind.getThingId()) + " " + tingTypeName + quartzRemind.getRemindType() +  "后开始。";
                if("0分钟".equalsIgnoreCase(quartzRemind.getRemindType())){
                    joinContent = userMap.get(thingCreatorMap.get(quartzRemind.getThingId())) + " 发起的 " + thingSubjectMap.get(quartzRemind.getThingId()) + " " + tingTypeName  +  "已经开始了。";
                }
                MTNotificationSendReqDTO joinMt = MTNotificationSendReqDTO.builder()
                        .receiverIds(joinPersonList)
                        .content(joinContent)
                        .corpId(quartzRemind.getCorpId())
                        .properties(map)
                        .build();
                mtNotificationApiRequestClient.send(joinMt,tingType);
            }
            //删除
            quartzRemindService.remove(remindQw);
        }
    }
}
