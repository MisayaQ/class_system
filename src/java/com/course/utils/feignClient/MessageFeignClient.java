package com.course.utils.feignClient;

import com.course.base.Ret;
import com.course.utils.feignDto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 聊天微服务接口
 */
@FeignClient(value = "ZX-CHAT")
public interface MessageFeignClient {

    /**
     * 创建群组
     * @param groupCreateRDTO
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/group/create",method = RequestMethod.POST)
    Ret create(@RequestBody GroupCreateRDTO groupCreateRDTO,
               @RequestParam("zxClientType") String zxClientType,
               @RequestParam("zxAccountId") Long zxAccountId
    );

    /**
     * 解散群组
     * @param groupDismissRDTO
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/group/dismiss",method = RequestMethod.POST)
    Ret dismiss(@RequestBody GroupDismissRDTO groupDismissRDTO,
               @RequestParam("zxClientType") String zxClientType,
               @RequestParam("zxAccountId") Long zxAccountId
    );

    /**
     * 群加人
     * @param groupAddPersonRDTO
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/group/join",method = RequestMethod.POST)
    Ret join(@RequestBody GroupAddPersonRDTO groupAddPersonRDTO,
             @RequestParam("zxClientType") String zxClientType,
             @RequestParam("zxAccountId") Long zxAccountId
    );

    /**
     * 群减人
     * @param groupQuitPersonRDTO
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/group/quit",method = RequestMethod.POST)
    Ret quit(@RequestBody GroupQuitPersonRDTO groupQuitPersonRDTO,
             @RequestParam("zxClientType") String zxClientType,
             @RequestParam("zxAccountId") Long zxAccountId
    );
}
