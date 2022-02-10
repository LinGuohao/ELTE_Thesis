package com.guohaohome.moviedb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeployInfo {
    @RequestMapping("/")
    @ResponseBody
    public String prjInfo(){
        return "Movie-db deployed successfully";
    }
}
