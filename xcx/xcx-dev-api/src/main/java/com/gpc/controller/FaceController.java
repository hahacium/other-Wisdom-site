package com.gpc.controller;

import com.gpc.service.SiteCheckInService;
import com.gpc.utils.HttpClient;
import com.gpc.utils.JsonResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

@RestController
public class FaceController extends BasicController{
    @Autowired
    private SiteCheckInService siteCheckInService;
    @RequestMapping(value = "/faceTest", headers="content-type=multipart/form-data")
    public JsonResult faceTest(MultipartFile file, String userId , String idCard , String realName , String address) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return JsonResult.errorMsg("用户id不能为空...");
        }
        //System.out.println(idCard);

        // 文件保存的命名空间
        String fileSpace = FILE_SPACE;
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/faceTest";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        String fileName = "";
        try {
            if (file != null ){
                //得到上传时文件名
                fileName = file.getOriginalFilename();
                System.out.println(fileName);
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
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);

                    if(StringUtils.isNotBlank(address)) {
                        HashMap<String,String> paramMap = new HashMap<>();
                        paramMap.put("uid",userId);
                        paramMap.put("path",uploadPathDB);
                        paramMap.put("address",address);
                        int insert = siteCheckInService.insert(paramMap);
                        return JsonResult.ok(insert);
                    }else {
        //              D:/file/1910248RD0ZGZ5S8/faceTest/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.95ZkjGxSwTvof185aa05f090fca077639450d0630c7f.jpg
        //              System.out.println(FILE_SPACE + "/" + userId + "/faceTest/" + fileName);
        //              String img = FILE_SPACE + "/" + userId + "/faceTest/" + fileName;
        //              String image = HttpClient.GetImageStr(img);
        //              HashMap<String , String> paramMap = new HashMap<>();
        //              paramMap.put("idCardNum",idCard);
        //              paramMap.put("image",image);
        //              paramMap.put("realName",realName);
        //              String result = HttpClient.faceEquals(paramMap);
                        String result = "{" +
                                "\"Code\": \"0\"," +
                                "\"charge\": false," +
                                "\"remain\": 1305," +
                                "\"Data\": \"80\"," +
                                "\"result\": {" +
                                "\"error_code\": 0," +
                                "\"reason\": \"成功\"," +
                                "\"result\": {" +
                                "\"realname\": \"乐天磊\"," +
                                "\"idcard\": \"350721197702134399\"," +
                                "\"isok\": true" +
                                "}" +
                                "}" +
                                "}";
                        return JsonResult.ok(result);
                    }
                }
            } else {
                System.out.println("上传出错...");
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
        return JsonResult.ok();
    }
}
