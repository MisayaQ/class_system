package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.CourseFile;
import com.course.entity.CoursePurchase;
import com.course.service.ICourseFileService;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 课程附件表  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/course-file")
public class CourseFileController {

    @Autowired
    private ICourseFileService iCourseFileService;

    @Value("${file.path}")
    private String fileOriPath;

    @ApiOperation(value="上传学习视频", notes="")
    @PostMapping(value = "/fileUpload")
    public Ret fileUpload(@RequestParam(value = "file") MultipartFile file,CourseFile courseFile, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = fileOriPath + UUIDUtil.getUUID() + "//"; // 上传后的路径
//        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            courseFile.setFileName(fileName);
            courseFile.setFilePath(filePath);
            insertFileInfo(courseFile);
        } catch (IOException e) {
            return Ret.ok().setData("上传失败");
        }
        return Ret.ok().setData("上传成功");
    }

    public void insertFileInfo (CourseFile courseFile) {
        courseFile.setId(UUIDUtil.getUUID());
        courseFile.setCreatedTime(LocalDateTime.now());
        courseFile.setUpdatedTime(LocalDateTime.now());
        courseFile.setShowFlag("1");
        iCourseFileService.save(courseFile);
    }

    @ApiOperation(value="下载", notes="")
    @GetMapping("/downloadFile")
    public Ret downloadFile(String fileId, HttpServletResponse response, HttpServletRequest request) throws Exception {
        CourseFile attFileEO = iCourseFileService.getById(fileId);
        if (attFileEO != null) {
            InputStream is = null;
            OutputStream os = null;
            response.reset();
            try {
                String fileOldName = fileNameEncoding(attFileEO.getFileName(),request);
                response.setHeader("Content-Disposition", "attachment;filename=\""+fileOldName+"\"");
                response.setContentType("application/octet-stream");
                is = new FileInputStream(attFileEO.getFilePath()+attFileEO.getFileName());
                os = response.getOutputStream();
                IOUtils.copy(is, os);
                os.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                IOUtils.closeQuietly(is);
                IOUtils.closeQuietly(os);
            }
            return Ret.ok();
        } else {
            return Ret.error().setMsg("未找到文件地址");
        }

    }

    public String fileNameEncoding(String fileName, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent");
        if (agent.contains("Firefox")) {
            /*BASE64Encoder base64Encoder = new BASE64Encoder();
            fileName = "=?utf-8?B?"
                    + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";*/
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
        } else {
            fileName = URLEncoder.encode(fileName, "utf-8");
            //谷歌中空格变为+问题
            fileName = fileName.replaceAll("\\+","%20");
        }
        return fileName;
    }

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getFileByPage")
    public Ret getFileByPage(Integer page, Integer pageSize, CourseFile courseFile) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Page pageInfo = new Page(page,pageSize);
        if(StringUtils.isNotEmpty(courseFile.getFileType())){
            queryWrapper.eq("file_type",courseFile.getFileType());
        }
        if(StringUtils.isNotEmpty(courseFile.getFileName())){
            queryWrapper.like("file_name",courseFile.getFileName());
        }
        queryWrapper.orderByDesc("updated_time");
        Page<CourseFile> getList = iCourseFileService.page(pageInfo,queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateCourseFile")
    public Ret updateCourseFile(@RequestBody CourseFile courseFile) {
        courseFile.setUpdatedTime(LocalDateTime.now());
        boolean result = iCourseFileService.updateById(courseFile);
        if (result) {
            return Ret.ok().setData(courseFile);
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
            boolean result = iCourseFileService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

    @ApiOperation(value="新增任务", notes="新增任务")
    @PostMapping("/addCourseTask")
    public Ret addCourseTask(@RequestBody CourseFile courseFile) {
        if (StringUtils.isNotEmpty(courseFile.getFileContent())) {
            courseFile.setId(UUIDUtil.getUUID());
            courseFile.setValidFlag(0);
            courseFile.setCreatedTime(LocalDateTime.now());
            courseFile.setUpdatedTime(LocalDateTime.now());
            boolean result = iCourseFileService.save(courseFile);
            if (result) {
                return Ret.ok().setData(courseFile);
            } else {
                return Ret.error().setMsg("新增失败");
            }
        } else {
            return Ret.error().setMsg("任务内容不能为空");
        }
    }

    @ApiOperation(value="上传任务", notes="")
    @PostMapping(value = "/uploadTask")
    public Ret uploadTask(@RequestParam(value = "file") MultipartFile file, @RequestBody CourseFile courseFile, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = fileOriPath + UUIDUtil.getUUID() + "//"; // 上传后的路径
//        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            courseFile.setFileName(fileName);
            courseFile.setFilePath(filePath);
            courseFile.setUpdatedTime(LocalDateTime.now());
            boolean result = iCourseFileService.updateById(courseFile);
        } catch (IOException e) {
            return Ret.ok().setData("上传失败");
        }
        return Ret.ok().setData("上传成功");
    }

}
