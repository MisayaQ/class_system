package com.course.controller;


import com.course.base.Ret;
import com.course.entity.CourseFile;
import com.course.service.ICourseFileService;
import com.course.utils.UUIDUtil;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

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

    @PostMapping(value = "/fileUpload")
    public Ret fileUpload(@RequestParam(value = "file") MultipartFile file, String courseId, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
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
            CourseFile courseFile = new CourseFile();
            courseFile.setDetailsId(courseId);
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
        courseFile.setFileType("1");
        courseFile.setCreatedTime(LocalDateTime.now());
        courseFile.setUpdatedTime(LocalDateTime.now());
        courseFile.setShowFlag("1");
        iCourseFileService.save(courseFile);
    }

}
