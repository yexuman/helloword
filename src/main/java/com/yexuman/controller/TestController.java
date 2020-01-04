package com.yexuman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yexuman
 * @date 2019/12/16 14:31
 */
@Controller
public class TestController {

    @GetMapping("test")
    public String test(){
        return "test";
    }
}
