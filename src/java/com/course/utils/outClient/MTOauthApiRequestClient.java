package com.course.utils.outClient;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.course.base.Ret;
import com.course.utils.OkHttpUtil;
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
public class MTOauthApiRequestClient {

    private static final Logger logger = LoggerFactory.getLogger(MTOauthApiRequestClient.class);

    @Autowired
    private OkHttpUtil okHttpUtil;

    @Value("${mt.api}")
    private String api;
    /**
     * 根据appId(分配的微应用ID)获取免登录code
     * @param corpId
     * @param appId
     * @param accessToken
     * @return
     */
    public String getAuthCode(String corpId, String appId, String accessToken) {
        Map<String, String> queries = new HashMap<>(2);
        queries.put("corpId", corpId);
        queries.put("appId", appId);

        Map<String, String> headerParams = new HashMap<>(2);
        headerParams.put("clientType", "app");
        headerParams.put("Authorization", "Bearer " + accessToken);
        String json = okHttpUtil.get(api + "oauth/getAuthCode", queries, headerParams);

        return JSON.parseObject(json, new TypeReference<Ret<String>>() {}).getData();
    }

    /**
     * 根据code获取token
     * @param code
     * @return
     */
    public Map<String, String> getTokenByCode(String code) {
        Map<String, String> queries = new HashMap<>(1);
        queries.put("code", code);
        String json = HttpUtil.get(api + "oauth/getTokenByCode?code="+code);
        //String json = okHttpUtil.get(api + "oauth/getTokenByCode", queries, null);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", JSON.parseObject(json, new TypeReference<Ret<Map<String, String>>>() {}).getData().get("access_token"));
        tokens.put("refreshToken", JSON.parseObject(json, new TypeReference<Ret<Map<String, String>>>() {}).getData().get("access_token"));
        return tokens;
    }

    /**
     * 根据code获取token
     * @param code
     * @return
     */
    public String getRefReshTokenByCode(String code) {
        Map<String, String> queries = new HashMap<>(1);
        queries.put("code", code);

        String json = okHttpUtil.get(api + "oauth/getTokenByCode", queries, null);

        return JSON.parseObject(json, new TypeReference<Ret<Map<String, String>>>() {}).getData().get("refresh_token");
    }

}
