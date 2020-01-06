package com.course.utils.feignClient;

import com.course.base.Ret;
import com.course.utils.feignDto.MTOssRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务客户端
 */
@FeignClient(value = "ZX-OSS")
public interface FileFeignClient {

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    Ret<MTOssRspDTO> uploadFile(@RequestParam("file") MultipartFile file,
                                @RequestParam("bucket") Integer bucket,
                                @RequestParam("path") Integer path,
                                @RequestParam("zxClientType") String zxClientType,
                                @RequestParam("zxAccountId") Long zxAccountId
    );
}
