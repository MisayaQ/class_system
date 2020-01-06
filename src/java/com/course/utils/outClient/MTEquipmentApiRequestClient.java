package com.course.utils.outClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.course.utils.feignClient.EuqipmentFeignClient;
import com.course.utils.feignDto.DimCodeReqDTO;
import com.course.utils.feignDto.DimCodeRspDTO;
import com.course.utils.feignDto.MTEquipmentInfoDTO;
import com.course.utils.feignDto.MTEquipmentInfoRspDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 设备接口
 * @author David
 */
@Component
public class MTEquipmentApiRequestClient {

    private static final Logger logger = LoggerFactory.getLogger(MTEquipmentApiRequestClient.class);

    @Autowired
    private EuqipmentFeignClient euqipmentFeignClient;

    public static String zxClientType = "app";

    public static Long zxAccountId = (long)1;

    /**
     * 模糊搜索列表
     * @return
     */
    public List<MTEquipmentInfoDTO> getEquipment(String equipmentCodeOrName, String dimCode, String corpId) {
        ResponseEntity<String> obj =  euqipmentFeignClient.getEquipment(equipmentCodeOrName,dimCode,corpId,zxClientType,zxAccountId);
        MTEquipmentInfoRspDTO mtEquipmentInfoRspDTO = JSON.parseObject(obj.getBody(), new TypeReference<MTEquipmentInfoRspDTO>() {});
        return mtEquipmentInfoRspDTO.getData();
    }

    /**
     * 查询所有的dimCode
     * @param corpId
     * @return
     */
    public DimCodeRspDTO getDictionaryBySearch(String corpId) {
        DimCodeReqDTO dimCodeReqDTO = DimCodeReqDTO.builder().name("").pageNum(1).pageSize(1000).build();
        ResponseEntity<String> obj =  euqipmentFeignClient.getDictionaryBySearch(dimCodeReqDTO,"dim",corpId,zxClientType,zxAccountId);
        DimCodeRspDTO list = JSON.parseObject(obj.getBody(), new TypeReference<DimCodeRspDTO>() {});
        return list;
    }

}
