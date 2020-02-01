package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.SysNotice;
import com.course.service.ISysNoticeService;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 公告  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/sys-notice")
public class SysNoticeController {

    @Autowired
    private ISysNoticeService iSysNoticeService;

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getNoticeByPage")
    public Ret getNoticeByPage(Integer page, Integer pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Page pageInfo = new Page(page,pageSize);
        queryWrapper.orderByDesc("updated_time");
        Page<SysNotice> getList = iSysNoticeService.page(pageInfo,queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addNotice")
    public Ret addNotice(SysNotice sysNotice) {
        sysNotice.setId(UUIDUtil.getUUID());
        sysNotice.setValidFlag(0);
        sysNotice.setCreatedTime(LocalDateTime.now());
        sysNotice.setUpdatedTime(LocalDateTime.now());
        boolean result = iSysNoticeService.save(sysNotice);
        if (result) {
            return Ret.ok().setData(sysNotice);
        } else {
            return Ret.error().setMsg("新增失败");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateNotice")
    public Ret updateNotice(SysNotice sysNotice) {
        sysNotice.setUpdatedTime(LocalDateTime.now());
        boolean result = iSysNoticeService.updateById(sysNotice);
        if (result) {
            return Ret.ok().setData(sysNotice);
        } else {
            return Ret.error().setMsg("修改失败");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iSysNoticeService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

    @ApiOperation(value="根据id查询个人信息", notes="")
    @GetMapping("/getNoticeById")
    public Ret getNoticeById(String id) {
        //QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("ID",id);
        SysNotice getInfo = iSysNoticeService.getById(id);
        return Ret.ok().setData(getInfo);
    }
}
