package com.gpc.controller;

import com.gpc.utils.HttpClientUtil;
import com.gpc.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class WeatherController {
    @RequestMapping("/weather/{city}")
    public JsonResult weather(@PathVariable String city) {
        HashMap<String,Object> paramMap = new HashMap<>();
        String url = "https://way.jd.com/jisuapi/weather";
        //paramMap.put("url","https://way.jd.com/jisuapi/weather");
        paramMap.put("city",city);
        paramMap.put("appkey","32319a6baf21feb0c7a4ab4b2b0a83f9");
        String response = HttpClientUtil.doPost(url, paramMap);
        return JsonResult.ok(response);
    }

}
