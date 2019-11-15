package com.gpc.controller;

import com.gpc.pojo.Videos;
import com.gpc.service.VideoService;
import com.gpc.utils.FetchVideoCover;
import com.gpc.utils.JsonResult;
import com.gpc.utils.PagedResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class videoController extends BasicController{
    @Autowired
    private VideoService videoService;
    @PostMapping(value="/upload", headers="content-type=multipart/form-data")
    public JsonResult upload(String userId,
                             double videoSeconds,
                             int videoWidth, int videoHeight,
                             String desc,
                             MultipartFile file) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return JsonResult.errorMsg("用户id不能为空...");
        }
        // 文件保存的命名空间
        //String fileSpace = "D:/file";
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        String coverPathDB = "/" + userId + "/video";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        String finalVideoPath = "";
        try {
            if (file != null) {
                //得到上传时文件名
                String fileName = file.getOriginalFilename();
                String randomId = UUID.randomUUID().toString();
                if (StringUtils.isNotBlank(fileName)) {
                    // 文件上传的最终保存路径
                    finalVideoPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    coverPathDB += ("/" + randomId + ".jpg");
                    File outFile = new File(finalVideoPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
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

        System.out.println("uploadPathDB=" + uploadPathDB);
        System.out.println("finalVideoPath=" + finalVideoPath);
        Videos videos = new Videos();
//		对视频进行截图
        FetchVideoCover videoInfo = new FetchVideoCover(FFMPEG_EXE);
        videoInfo.getCover(finalVideoPath, FILE_SPACE + coverPathDB);
        Videos video = new Videos();
        video.setVideoDesc(desc);
        video.setVideoSeconds((float) videoSeconds);
        video.setVideoWidth(videoWidth);
        video.setVideoHeight(videoHeight);
        video.setVideoPath(uploadPathDB);
        video.setUserId(userId);
        video.setStatus(1);
        video.setCoverPath(coverPathDB);
        videoService.uploadVideo(video);
        return JsonResult.ok();
    }
    /**
     *
     * @Description: 分页和搜索查询视频列表
     * isSaveRecord：1 - 需要保存
     * 				 0 - 不需要保存 ，或者为空的时候
     */
    @PostMapping(value="/showAll/{page}/{isSaveRecord}")
    public JsonResult showAll(@RequestBody Videos video, @PathVariable Integer isSaveRecord,
                              @PathVariable Integer page) throws Exception {
        if (page == null) {
            page = 1;
        }
		/*if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}*/
        PagedResult result = videoService.getAllVideos(video, isSaveRecord, page, PAGE_SIZE);
        return JsonResult.ok(result);
    }

    //获取热搜词
    @PostMapping(value="/hot")
    public JsonResult hot() throws Exception {
        return JsonResult.ok(videoService.getHotwords());
    }
}
