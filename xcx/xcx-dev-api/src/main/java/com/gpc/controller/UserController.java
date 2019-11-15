package com.gpc.controller;

import com.gpc.pojo.Users;
import com.gpc.pojo.UsersReport;
import com.gpc.service.UserService;
import com.gpc.utils.JsonResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/user")
public class UserController extends BasicController{
    @Autowired
    private UserService userService;
    //头像上传接口
    @PostMapping("/uploadFace/{userId}")
    public JsonResult uploadFace(@PathVariable("userId") String userId,
                                 @RequestParam("file") MultipartFile[] files) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return JsonResult.errorMsg("用户id不能为空...");
        }
        // 文件保存的命名空间
        String fileSpace = FILE_SPACE;
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/face";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {
                //得到上传时文件名
                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    // 文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                return JsonResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(uploadPathDB);
        userService.updateUserInfo(user);
        return JsonResult.ok(uploadPathDB);
    }

    @PostMapping("/reportUser")
    public JsonResult reportUser(@RequestBody UsersReport usersReport) throws Exception {
        // 保存举报信息
        userService.reportUser(usersReport);
        return JsonResult.errorMsg("举报成功...有你平台变得更美好...");
    }
}
