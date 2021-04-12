package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.SysUser;
import com.course.service.ICourseDetailsService;
import com.course.service.ISysUserService;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程详情表  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/course-details")
public class CourseDetailsController {

    @Autowired
    private ICourseDetailsService iCourseDetailsService;

    @Autowired
    private ISysUserService iSysUserService;

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getCourseDetailsByPage")
    public Ret getCourseDetailsByPage(Integer page, Integer pageSize,CourseDetails courseDetails) throws ParseException {
        QueryWrapper queryWrapper = new QueryWrapper();
        Page pageInfo = new Page(page,pageSize);
        if(StringUtils.isNotEmpty(courseDetails.getTeacherIds())){
            queryWrapper.like("teacher_ids",courseDetails.getTeacherIds());
        }
        if(StringUtils.isNotEmpty(courseDetails.getCName())){
            queryWrapper.like("c_name",courseDetails.getCName());
        }
        if(StringUtils.isNotEmpty(courseDetails.getCourseCode())){
            queryWrapper.like("course_code",courseDetails.getCourseCode());
        }
        if(courseDetails.getIsTop() != null){
            queryWrapper.eq("is_top",courseDetails.getIsTop());
        }
        queryWrapper.orderByDesc("updated_time");
        Page<CourseDetails> getList = iCourseDetailsService.page(pageInfo,queryWrapper);

        List<CourseDetails> detailsList = getList.getRecords();

        //计算结束时间剩余天数
        Date nowTime = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = simpleFormat.format(nowTime);
        Iterator<CourseDetails> iterator = detailsList.iterator();
        //删除过期课程
        while (iterator.hasNext()) {
            CourseDetails details = iterator.next();
            Date endTime = details.getEndTime();
            String end = simpleFormat.format(endTime);
            long timeNow = simpleFormat.parse(now).getTime();
            long timeEnd = simpleFormat.parse(end).getTime();
            int deadline =  (int) (timeEnd - timeNow) / (1000 * 3600 * 24);
            if (deadline <= 0) {
                iterator.remove();
            }
            details.setDeadLine(String.valueOf(deadline));
        }
        if (detailsList != null && !detailsList.isEmpty()) {
            for (CourseDetails cour : detailsList) {
                if (StringUtils.isNotEmpty(cour.getTeacherIds())) {
                    String teacherNames = "";
                    String[] ids = cour.getTeacherIds().split(",");
                    for(String id : ids){
                        QueryWrapper query = new QueryWrapper();
                        query.eq("ID",id);
                        SysUser getUser = iSysUserService.getById(id);
                        if (getUser != null) {
                            teacherNames += getUser.getUname() + ",";
                            teacherNames = teacherNames.substring(0, teacherNames.length() - 1);
                        }
                    }
                    cour.setTeacherNames(teacherNames);
                }
            }
        }
        getList.setRecords(detailsList);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addCourseDetails")
    public Ret addCourseDetails(@RequestBody CourseDetails courseDetails) {
        if (StringUtils.isNotEmpty(courseDetails.getCName())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("c_name",courseDetails.getCName());
            List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                return Ret.error().setMsg("课程名称已存在");
            } else {
                courseDetails.setId(UUIDUtil.getUUID());
                courseDetails.setValidFlag(0);
                courseDetails.setCreatedTime(new Date());
                courseDetails.setUpdatedTime(new Date());
                boolean result = iCourseDetailsService.save(courseDetails);
                if (result) {
                    return Ret.ok().setData(courseDetails);
                } else {
                    return Ret.error().setMsg("新增失败");
                }
            }
        } else {
            return Ret.error().setMsg("课程名称不能为空");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateCourseDetails")
    public Ret updateCourseDetails(@RequestBody CourseDetails courseDetails) {
        if (StringUtils.isNotEmpty(courseDetails.getCName())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("c_name",courseDetails.getCName());
            List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                if (getList.size() > 1 || (getList.size() == 1 && !courseDetails.getId().equals(getList.get(0).getId()))) {
                    return Ret.error().setMsg("课程名称已存在");
                }
            }
            courseDetails.setUpdatedTime(new Date());
            boolean result = iCourseDetailsService.updateById(courseDetails);
            if (result) {
                return Ret.ok().setData(courseDetails);
            } else {
                return Ret.error().setMsg("修改失败");
            }
        } else {
            return Ret.error().setMsg("课程名称不能为空");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iCourseDetailsService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

    @ApiOperation(value="根据id查询", notes="")
    @GetMapping("/getCourseDetailsById")
    public Ret getCourseDetailsById(String id) {
        //QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("ID",id);
        CourseDetails getInfo = iCourseDetailsService.getById(id);
        if (StringUtils.isNotEmpty(getInfo.getTeacherIds())) {
            String teacherNames = "";
            String[] ids = getInfo.getTeacherIds().split(",");
            for(String tid : ids){
                QueryWrapper query = new QueryWrapper();
                query.eq("ID",tid);
                SysUser getUser = iSysUserService.getById(tid);
                if (getUser != null) {
                    teacherNames += getUser.getUname() + ",";
                }
            }
            getInfo.setTeacherNames(teacherNames);
        }
        return Ret.ok().setData(getInfo);
    }

    @ApiOperation(value="列表查询", notes="")
    @GetMapping("/getCourseDetailsByList")
    public Ret getCourseDetailsByList(CourseDetails courseDetails) {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }
}
