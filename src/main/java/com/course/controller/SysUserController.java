package com.course.controller;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.SysCount;
import com.course.entity.SysUser;
import com.course.service.ISysCountService;
import com.course.service.ISysUserService;
import cn.hutool.core.io.file.FileWriter;
import com.course.utils.StringUtil;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户管理  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysCountService iSysCountService;

//    @Value("${pic.Location}")
//    private String picLocation;

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getUserByPage")
    public Ret getUserByPage(Integer page,Integer pageSize,SysUser sysUser) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(sysUser.getUname())){
            queryWrapper.like("uname",sysUser.getUname());
        }
        if(StringUtils.isNotEmpty(sysUser.getAccount())){
            queryWrapper.like("account",sysUser.getAccount());
        }
        if(StringUtils.isNotEmpty(sysUser.getUserRole())){
            queryWrapper.eq("user_role",sysUser.getUserRole());
        }
        if(sysUser.getValidFlag() != null){
            queryWrapper.eq("valid_flag",sysUser.getValidFlag());
        }
        queryWrapper.orderByDesc("updated_time");
        Page pageInfo = new Page(page,pageSize);
        Page<SysUser> getList = iSysUserService.page(pageInfo,queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="注册或新增用户")
    @PostMapping("/addUser")
    public Ret addUser(SysUser sysUser) {
        if (StringUtils.isNotEmpty(sysUser.getAccount())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account",sysUser.getAccount());
            List<SysUser> getList = iSysUserService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                return Ret.error().setMsg("账号已存在");
            } else {
                sysUser.setId(UUIDUtil.getUUID());
                sysUser.setValidFlag(0);
                sysUser.setCreatedTime(LocalDateTime.now());
                sysUser.setUpdatedTime(LocalDateTime.now());
                boolean result = iSysUserService.save(sysUser);
                if (result) {
                    return Ret.ok().setData(sysUser);
                } else {
                    return Ret.error().setMsg("新增失败");
                }
            }
        } else {
            return Ret.error().setMsg("账号不能为空");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateUser")
    public Ret updateUser(@RequestBody SysUser sysUser) {
        if (StringUtils.isNotEmpty(sysUser.getAccount())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account",sysUser.getAccount());
            List<SysUser> getList = iSysUserService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                if (getList.size() > 1 || (getList.size() == 1 && !sysUser.getId().equals(getList.get(0).getId()))) {
                    return Ret.error().setMsg("账号已存在");
                }
            }
            sysUser.setUpdatedTime(LocalDateTime.now());
            boolean result = iSysUserService.updateById(sysUser);
            if (result) {
                return Ret.ok().setData(sysUser);
            } else {
                return Ret.error().setMsg("修改失败");
            }
        } else {
            return Ret.error().setMsg("账号不能为空");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iSysUserService.removeByIds(idList);
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
    @GetMapping("/getUserById")
    public Ret getUserById(String id) {
        //QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("ID",id);
        SysUser getInfo = iSysUserService.getById(id);
        return Ret.ok().setData(getInfo);
    }

    @ApiOperation(value="列表查询", notes="")
    @GetMapping("/getUserByList")
    public Ret getUserByList(SysUser sysUser) {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<SysUser> getList = iSysUserService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="查询下拉列表", notes="")
    @GetMapping("/getUserBySelect")
    public Ret getUserBySelect(SysUser sysUser) {
//        Map<String,String> getMap = new HashMap<>(50);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(sysUser.getUserRole())){
            queryWrapper.eq("user_role",sysUser.getUserRole());
        } else {
            return Ret.error().setMsg("输入查询人员类型，userRole字段，1为学生2为教师");
        }
        List<SysUser> getList = iSysUserService.list(queryWrapper);
        /*if(getList!=null && !getList.isEmpty()){
            for(SysUser sys : getList){
                getMap.put(sys.getId(),sys.getUname());
            }
        }*/
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="登录", notes="")
    @GetMapping("/login")
    public Ret userLogin(String account,String password,String userRole) throws Exception {
        if(StringUtils.isEmpty(account)){
            return Ret.error().setMsg("用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            return Ret.error().setMsg("密码不能为空");
        } else if (StringUtils.isEmpty(userRole)) {
            return Ret.error().setMsg("角色不能为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("user_role",userRole);
        List<SysUser> getInfo = iSysUserService.list(queryWrapper);
        if (getInfo != null && !getInfo.isEmpty()) {
            if (password.equals(getInfo.get(0).getPassword())) {
                if (getInfo.get(0).getValidFlag() == 1) {
                    return Ret.error().setMsg("账号已被禁用");
                } else {
                    //增加访问量
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateNowStr = sdf.format(d);
//                    Date today = sdf.parse(dateNowStr);
                    QueryWrapper queryCount = new QueryWrapper();
                    queryCount.like("count_date",dateNowStr);
                    List<SysCount> getCountList = iSysCountService.list(queryCount);
                    if (getCountList != null && !getCountList.isEmpty()) {
                        SysCount sysCount = getCountList.get(0);
                        sysCount.setCounts(sysCount.getCounts() + 1);
                        iSysCountService.updateById(sysCount);
                    } else {
                        SysCount sysCount = new SysCount();
                        sysCount.setId(UUIDUtil.getUUID());
                        sysCount.setCounts(1);
                        sysCount.setCountDate(d);
                        sysCount.setValidFlag("0");
                        iSysCountService.save(sysCount);
                    }
                    return Ret.ok().setData(getInfo.get(0));
                }
            } else {
                return Ret.error().setMsg("密码错误");
            }
        } else {
            return Ret.error().setMsg("该角色下账号不存在");
        }
    }

//    @ApiOperation(value = "上传头像")
//    @PostMapping(path = "/uploadProfilePhoto")
//    public Ret uploadProfilePhoto(@RequestParam(name = "photo") MultipartFile multipartFile, @RequestParam(name = "uId") String uId) throws IOException {
//        if (multipartFile.isEmpty() || StringUtils.isEmpty(uId)) {
//            return Ret.ok().setMsg("上传图片为空");
//        }
//        // 构造文件
//        String uploadPath = picLocation + File.separator + uId + File.separator;
//        String fileOriginalName = multipartFile.getOriginalFilename();
//        String ext = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
//        String fileNewName = UUIDUtil.getUUID() + ext;
//        File pic = new File(uploadPath + fileNewName);
//        FileUtil.touch(pic);
//        // 写数据
//        FileWriter writer = new FileWriter(pic);
//        writer.writeFromStream(multipartFile.getInputStream());
//
//        if (0 < this.userService.savePic(uId, pic)) {
//            return Ret.ok().setMsg("头像修改成功");
//        }
//        return Ret.ok().setMsg("头像修改失败");
//    }
//
//    @ApiOperation(value = "获取头像图片流")
//    @GetMapping(path = "/getProfilePicStream/{uId}")
//    public void getProfilePicStream(@PathVariable String uId, HttpServletResponse response) throws IOException {
//        File file = this.userPicService.getProfilePic(uId);
//        String fileMime = FileUtil.getMimeType(file.getAbsolutePath());
//        if (MimeTypeUtils.IMAGE_PNG_VALUE.equalsIgnoreCase(fileMime)) {
//            response.setContentType(MimeTypeUtils.IMAGE_PNG_VALUE);
//        } else {
//            response.setContentType(MimeTypeUtils.IMAGE_JPEG_VALUE);
//        }
//
//        OutputStream os = response.getOutputStream();
//        FileUtil.writeToStream(file, os);
//        os.flush();
//        os.close();
//    }
//
//    @ApiOperation(value = "获取头像Base64")
//    @GetMapping(path = "/getProfilePicBase64/{uId}")
//    public ReturnEntity getProfilePicBase64(@PathVariable String uId) throws IOException {
//        File file = this.userPicService.getProfilePic(uId);
///*        byte[] bytes;
//        InputStream is = new FileInputStream(file);
//        bytes = new byte[is.available()];
//        is.read(bytes);
//        is.close();
//        logger.warn(Base64Utils.encodeToString(bytes));*/
//        return ReturnEntity.ok().setData(Base64.encode(file)).setMsg("图片转为base64");
//    }

}
