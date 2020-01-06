package com.course.utils.outClient;

import com.alibaba.fastjson.JSON;
import com.course.utils.OkHttpUtil;
import com.course.utils.feignDto.MTLogReqDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David
 */
@Component
public class MTLogApiRequestClient {

    private static final Logger logger = LoggerFactory.getLogger(MTLogApiRequestClient.class);

    @Autowired
    private OkHttpUtil okHttpUtil;

    @Value("${mt.api}")
    private String api;

    /**
     *
     * @param zxAccountId
     * @param accessToken
     * @return
     */
    public String save(String zxAccountId, MTLogReqDTO mtLogReqDTO, String accessToken) {
        Map<String, String> headerParams = new HashMap<>(2);
        headerParams.put("clientType", "app");
        headerParams.put("Authorization", "Bearer " + accessToken);
        return okHttpUtil.postForJson(api + "log/api/v1/applog/save?zxAccountId=" + zxAccountId, JSON.toJSONString(mtLogReqDTO), headerParams);
    }

}
