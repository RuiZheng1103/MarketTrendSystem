package com.rui.trend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rui.trend.config.IpConfiguration;
import com.rui.trend.pojo.Index;
import com.rui.trend.service.IndexService;

@RestController
public class IndexController {
	
    @Autowired 
    IndexService indexService;
    
    @Autowired 
    IpConfiguration ipConfiguration;
     
//  port 8011 is the default one
     
    @GetMapping("/codes")
    @CrossOrigin
    public List<Index> codes() throws Exception {
        System.out.println("current instance's port is "+ ipConfiguration.getPort());
        return indexService.get();
    }
}
