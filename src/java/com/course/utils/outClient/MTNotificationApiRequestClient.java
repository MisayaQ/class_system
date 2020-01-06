package com.course.utils.outClient;

import com.course.base.Ret;
import com.course.utils.feignClient.NotificationFeignClient;
import com.course.utils.feignDto.MTNotificationSendReqDTO;
import com.course.utils.feignDto.MTUserGetsReqDTO;
import com.course.utils.feignDto.MTUserInfoRspDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 消息通知接口调用
 * @author David
 */
@Component
public class MTNotificationApiRequestClient {

    static final Logger logger = LoggerFactory.getLogger(MTNotificationApiRequestClient.class);

    @Autowired
    private NotificationFeignClient notificationFeignClient;
    @Autowired
    private MTCotactApiRequestClient mtCotactApiRequestClient;
    @Autowired
    private INotificationService notificationService;

    /**
     * 发送通知
     * @param mtNotificationSendReqDTO
     * @return
     */
    public boolean send(MTNotificationSendReqDTO mtNotificationSendReqDTO,String type) {
        try{
            List<String> userIds = mtNotificationSendReqDTO.getReceiverIds();
            List<String> accountIds = new ArrayList<>();
            MTUserGetsReqDTO mtUserGetsReqDTO = new MTUserGetsReqDTO();
            mtUserGetsReqDTO.setCorpId(mtNotificationSendReqDTO.getCorpId());
            mtUserGetsReqDTO.setIds(userIds);
            List<MTUserInfoRspDTO> allUserList = mtCotactApiRequestClient.userGets(mtUserGetsReqDTO);
            if(allUserList != null && allUserList.size() > 0){
                for(MTUserInfoRspDTO mtUserInfoRspDTO : allUserList){
                    accountIds.add(mtUserInfoRspDTO.getAccountId());
                }
            }
            logger.info("消息发送人accountId:" + accountIds);
            mtNotificationSendReqDTO.setReceiverIds(accountIds);
            mtNotificationSendReqDTO.setChannels("ALL");
            mtNotificationSendReqDTO.setCategoryCodes(Arrays.asList(new String[]{type}));
            Ret ret = notificationFeignClient.sendMessage(mtNotificationSendReqDTO,"app",(long)1);
            logger.info("++++++++++++++++++++++++:" + ret.getCode());
            if(ret != null && "M0000".equals(ret.getCode())){
                return true;
            }
            return false;
        }catch(Exception e){
            logger.error("发送智信消息失败");
        }
        return false;
    }

}
