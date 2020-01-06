package com.course.utils.feignClient;

import com.course.utils.feignDto.DimCodeReqDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 设备库客户端
 */
@FeignClient(value = "SF-EUQIPMENT")
public interface EuqipmentFeignClient {

    @RequestMapping(value = "/api/equipment/v1/dictionary/getDictionaryBySearch",method = RequestMethod.POST)
    @Headers({"clientType: app"})
    ResponseEntity<String> getDictionaryBySearch(@RequestBody DimCodeReqDTO dimCodeReqDTO,
            @RequestParam("dictionaryCode") String dictionaryCode,
            @RequestHeader("corpId") String corpId,
            @RequestParam("zxClientType") String zxClientType,
            @RequestParam("zxAccountId") Long zxAccountId);

    @RequestMapping(value = "/api/equipment/v1//data/getEquipment/{equipmentCodeOrName}",method = RequestMethod.GET)
    ResponseEntity<String> getEquipment(@PathVariable("equipmentCodeOrName") String equipmentCodeOrName,
            @RequestParam("dimCode") String dimCode,
            @RequestHeader("corpId") String corpId,
            @RequestParam("zxClientType") String zxClientType,
            @RequestParam("zxAccountId") Long zxAccountId);
}
