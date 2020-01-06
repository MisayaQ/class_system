package com.course.controller;

import com.course.base.Ret;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: yangxuenan
 * date: 2020/1/6 15:34
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value="获取列表", notes="")
    @GetMapping("/getMenuList")
    public Ret getMenuList(String id) {
        return Ret.ok().setData(id);
    }
}
