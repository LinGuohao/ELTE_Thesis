package com.guohaohome.moviedb.controller;


import com.guohaohome.moviedb.dao.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeployInfo {

    @Autowired
    MovieMapper movieMapper;
    @RequestMapping("/")
    @ResponseBody
    public String prjInfo(){
         //return movieMapper.getAllID().get(0);
        return "Movie-db deployed successfully";
    }
}
