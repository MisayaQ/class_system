package com.course.utils.feignClient;

import com.course.base.Ret;
import com.course.utils.feignDto.RefreshDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 网关客户端接口
 */
@FeignClient(value = "ZX-GATEWAY")
public interface GateWayFeignClient {

    /**
     * 根据刷新token获取验证token
     * @return
     */
    @RequestMapping(value = "/refresh/token",method = RequestMethod.GET)
    Ret<RefreshDTO> refreshToken(@RequestParam("refresh_token") String refresh_token,
                                 @RequestParam("zxClientType") String zxClientType,
                                 @RequestParam("zxAccountId") Long zxAccountId
    );

}
