package com.rui.trend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rui.trend.pojo.Index;
import com.rui.trend.service.IndexService;

@RestController
public class IndexController {
	
	@Autowired
	IndexService indexService;
	
	@GetMapping("/freshCodes")
	public List<Index> getCodes() throws Exception {
		
		return indexService.fresh();
	}
	
    @GetMapping("/getCodes")
    public List<Index> get() throws Exception {
        return indexService.get();
    }
    
    @GetMapping("/removeCodes")
    public String remove() throws Exception {
        indexService.remove();
        return "remove codes successfully";
    }
}
