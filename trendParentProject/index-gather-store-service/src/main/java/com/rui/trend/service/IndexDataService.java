package com.rui.trend.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rui.trend.pojo.Index;
import com.rui.trend.pojo.IndexData;
import com.rui.trend.util.SpringContextUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;

@Service
@CacheConfig(cacheNames="index_datas")
public class IndexDataService {
	
	private Map<String, List<IndexData>> indexDatas = new HashMap<>();
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "LostFromThirdPart")
	public List<IndexData> fresh(String code) {
		List<IndexData> indexData = fetchIndexesFromThirdPart(code);
		indexDatas.put(code, indexData);
		
		System.out.println("code :" + code);
		System.out.println("indexDatas' size :" + indexDatas.get(code).size());
		
		IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataService.class);
		indexDataService.remove(code);
		return indexDataService.store(code);
	}
	
	
	
//	Cache Operations
    @CacheEvict(key="'indexData-code-'+ #p0")
    public void remove(String code){
 
    }
 
    @CachePut(key="'indexData-code-'+ #p0")
    public List<IndexData> store(String code){
        return indexDatas.get(code);
    }
 
    @Cacheable(key="'indexData-code-'+ #p0")
    public List<IndexData> get(String code){
        return CollUtil.toList();
    }	
	
	public List<IndexData> fetchIndexesFromThirdPart(String code){
		
		List<Map> rawData = restTemplate.getForObject("http://localhost:8090/indexes/"+code+".json", List.class);
		return map2IndexData(rawData); 
	}
	
	public List<IndexData> LostFromThirdPart(String code){
		
		System.out.println("third part is not connected");
		IndexData indexData =new IndexData();
		indexData.setDate("n/a");
		indexData.setClosePoint(0);
		return CollectionUtil.toList(indexData);
	}	
		
	
	private List<IndexData> map2IndexData(List<Map> rawData) {
        List<IndexData> indexDatas = new ArrayList<>();
        for (Map map : rawData) {
            String date = map.get("date").toString();
            float closePoint = Convert.toFloat(map.get("closePoint"));
            IndexData indexData = new IndexData();
            
            indexData.setDate(date);
            indexData.setClosePoint(closePoint);
            indexDatas.add(indexData);
        }
         
        return indexDatas;
    }
	
}
