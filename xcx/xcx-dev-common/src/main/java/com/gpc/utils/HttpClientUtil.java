package com.gpc.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 *  httpclient相关工具类
 */
public class HttpClientUtil {


    /**
     * post
     * @param url
     * @param paramMap
     * @return
     */
    public static String doPost(String url, HashMap<String, Object> paramMap) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            //创建httpclient实例，可以认为是创建了一个浏览器
            client = HttpClients.createDefault();
            //创建httpget远程连接实例，可以认为是在浏览器的地址栏中写上url
            HttpPost httpPost = new HttpPost(url);

            //配置请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000)//设置连接主机服务超时时间
                    .setConnectionRequestTimeout(10000)//设置连接请求超时时间
                    .setSocketTimeout(50000)//设置读取数据连接超时时间
                    .build();

            //将参数配置到httppost中
            httpPost.setConfig(requestConfig);

            //准备向服务器端发送的参数
            if (paramMap != null && paramMap.size() > 0) {
                ArrayList<BasicNameValuePair> list = new ArrayList<>();

                //获取map的entryset
                Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
                //获取迭代器
                Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> objectEntry = iterator.next();
                    list.add(new BasicNameValuePair(objectEntry.getKey(), objectEntry.getValue().toString()));
                }

                //将请求参数放到httpPost对象中
                httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
                //执行post请求
                response = client.execute(httpPost);
            }

            //从response中获取返回的数据
            HttpEntity entity = response.getEntity();

            //将返回数据对象转成字符串格式
            String result = EntityUtils.toString(entity);
            System.out.println(result);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static String doPostByXml(String url, String requestDataXml) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            //创建httpclient实例，可以认为是创建了一个浏览器
            client = HttpClients.createDefault();
            //创建httpget远程连接实例，可以认为是在浏览器的地址栏中写上url
            HttpPost httpPost = new HttpPost(url);

            //配置请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000)//设置连接主机服务超时时间
                    .setConnectionRequestTimeout(10000)//设置连接请求超时时间
                    .setSocketTimeout(50000)//设置读取数据连接超时时间
                    .build();

            //将参数配置到httppost中
            httpPost.setConfig(requestConfig);

            httpPost.setEntity(new StringEntity(requestDataXml,"UTF-8"));

            httpPost.addHeader("Content-Type","text/xml");

            response = client.execute(httpPost);

            //从response中获取返回的数据
            HttpEntity entity = response.getEntity();

            //将返回数据对象转成字符串格式
            String result = EntityUtils.toString(entity);
            System.out.println(result);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
