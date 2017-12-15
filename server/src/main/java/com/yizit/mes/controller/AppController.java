package com.yizit.mes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 李江峰
 * Date: 2017-12-15
 * Time: 9:33
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @RequestMapping("info")
    public Object info() {
        Map<String,Object> map = new HashMap<>();
        map.put("info","hello world");
        return map;
    }

}
