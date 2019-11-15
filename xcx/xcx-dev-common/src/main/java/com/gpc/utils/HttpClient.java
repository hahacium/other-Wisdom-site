package com.gpc.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {
    public static String faceEquals(HashMap<String , String> paramMap) throws Exception {
        String host = "https://facecheck.market.alicloudapi.com";
        String path = "/facecheck";
        String method = "POST";
        String appcode = "cc331f115e484063b6f221be64f8eb93";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("idCardNum", paramMap.get("idCardNum"));
        bodys.put("image", paramMap.get("image"));
        bodys.put("realName", paramMap.get("realName"));
        HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
        System.out.println(response.toString());
        //获取response的body
        //System.out.println(EntityUtils.toString(response.getEntity()));
        return EntityUtils.toString(response.getEntity());
    }
    /*public static void main(String[] args) {
        String host = "https://facecheck.market.alicloudapi.com";
        String path = "/facecheck";
        String method = "POST";
        String appcode = "47c507490f554fa89f7d58d8472e6a61";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fxxxxxxxxxxxx
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        String s = GetImageStr("D:/file/1910248RD0ZGZ5S8/faceTest/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.95ZkjGxSwTvof185aa05f090fca077639450d0630c7f.jpg");
        bodys.put("idCardNum", "440582199808020472");
        bodys.put("image", s);
        bodys.put("realName", "陈浩楠");
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgFilePath 图片路径
     * @return String
     */
    public static String GetImageStr(String imgFilePath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return Base64.getEncoder().encodeToString(data);
    }
}
