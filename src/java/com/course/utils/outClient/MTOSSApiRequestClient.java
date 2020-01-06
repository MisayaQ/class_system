package com.course.utils.outClient;

import com.course.utils.feignClient.FileFeignClient;
import com.course.utils.feignDto.MTOssRspDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 消息通知接口调用
 * @author David
 */
@Component
public class MTOSSApiRequestClient {

    private static final Logger logger = LoggerFactory.getLogger(MTOSSApiRequestClient.class);

    @Autowired
    private FileFeignClient fileFeignClient;

    public MTOssRspDTO upload(MultipartFile file) {
        return fileFeignClient.uploadFile(file,2,2,"app",(long)1).getData();
    }

}
