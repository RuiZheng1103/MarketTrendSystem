package com.rui.trend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rui.trend.pojo.IndexData;
import com.rui.trend.service.BackTestService;

@RestController
public class BackTestController {

	@Autowired
	BackTestService backTestService;
	
	@GetMapping("/simulate/{code}")
	@CrossOrigin
	public Map<String, Object> backTest(@PathVariable("code") String code) throws Exception {
		List<IndexData> allIndexDatas = backTestService.listIndexData(code);
		Map<String, Object> result = new HashMap<>();
		result.put("indexDatas", allIndexDatas);
		return result;
	}
}