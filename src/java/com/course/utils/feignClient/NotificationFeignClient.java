package com.course.utils.feignClient;

import com.course.base.Ret;
import com.course.utils.feignDto.MTNotificationSendReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 智信消息客户端接口
 */
@FeignClient(value = "ZX-NOTIFICATION")
public interface NotificationFeignClient {

    /**
     * 发送智信消息
     * @return
     */
    @RequestMapping(value = "/notification/send",method = RequestMethod.POST)
    Ret sendMessage(@RequestBody MTNotificationSendReqDTO mtNotificationSendReqDTO,
                    @RequestParam("zxClientType") String zxClientType,
                    @RequestParam("zxAccountId") Long zxAccountId
    );
}
