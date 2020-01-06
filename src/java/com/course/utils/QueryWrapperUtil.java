package com.course.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

public class QueryWrapperUtil {

    public static<T> QueryWrapper getQueryWrapper(T t){
        QueryWrapper qw = new QueryWrapper();
        if(t instanceof SearchMeetingManagerRDTO){
            if(StringUtils.isNotBlank(((SearchMeetingManagerRDTO) t).getMeetingName())){
                qw.like("name",((SearchMeetingManagerRDTO) t).getMeetingName());
            }
            if(StringUtils.isNotBlank(((SearchMeetingManagerRDTO) t).getMeetingStatus())){
                qw.eq("status",((SearchMeetingManagerRDTO) t).getMeetingStatus());
            }
            qw.ne("delete_status","1");
        }
        if(t instanceof PersonCreateAllThingRDTO){
            if(StringUtils.isNotBlank(((PersonCreateAllThingRDTO) t).getPerson())){
                qw.eq("creator",((PersonCreateAllThingRDTO) t).getPerson());
            }
            if(StringUtils.isNotBlank(((PersonCreateAllThingRDTO) t).getType())){
                qw.eq("type",((PersonCreateAllThingRDTO) t).getType());
            }
            if(StringUtils.isNotBlank(((PersonCreateAllThingRDTO) t).getStatus())){
                qw.eq("status",((PersonCreateAllThingRDTO) t).getStatus());
            }
        }
        if(t instanceof SearchThingAppRDTO){
            if(StringUtils.isNotBlank(((SearchThingAppRDTO) t).getSearchContent())){
                qw.like("subject",((SearchThingAppRDTO) t).getSearchContent());
            }
            if(StringUtils.isNotBlank(((SearchThingAppRDTO) t).getPerson())){
                qw.eq("creator",((SearchThingAppRDTO) t).getPerson());
            }
        }
        if(t instanceof GetMeetingSummaryRDTO){
            if(StringUtils.isNotBlank(((GetMeetingSummaryRDTO) t).getThingId())){
                qw.eq("thing_id",((GetMeetingSummaryRDTO) t).getThingId());
            }
            if(StringUtils.isNotBlank(((GetMeetingSummaryRDTO) t).getDay())){
                qw.eq("day",((GetMeetingSummaryRDTO) t).getDay());
            }
        }
        return qw;
    }

    public static QueryWrapper getQueryByIdWrapper(String id){
        QueryWrapper qw = new QueryWrapper();
        qw.eq("id",id);
        return qw;
    }
}
