package com.gpc.controller;

import com.gpc.pojo.Users;
import com.gpc.service.SearchService;
import com.gpc.utils.JsonResult;
import com.gpc.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;
    @RequestMapping("/search")
    public JsonResult search() {
        List<String> users = searchService.searchMatching();
        return JsonResult.ok(users);
    }
    @RequestMapping("/getSearch")
    public JsonResult getSearch(String name) {
        List<Users> users = searchService.selectByName(name);
        String s = JsonUtils.objectToJson(users);
        return JsonResult.ok(s);
    }
}
