package com.rui.trend.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rui.trend.pojo.Index;
import com.rui.trend.util.SpringContextUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;

@Service
@CacheConfig(cacheNames="indexes")
public class IndexService {
	
	private List<Index> indexes;
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "LostIndexesFromThirdPart")
	public List<Index> fresh() {
		indexes = fetchIndexesFromThirdPart();
		IndexService indexService = SpringContextUtil.getBean(IndexService.class);
		indexService.remove();
		return indexService.store();
	}
	
	
	
//	Cache Operations
    @CacheEvict(allEntries=true)
    public void remove(){
 
    }
 
    @Cacheable(key="'all_codes'")
    public List<Index> store(){
        System.out.println(this);
        return indexes;
    }
 
    @Cacheable(key="'all_codes'")
    public List<Index> get(){
        return CollUtil.toList();
    }	
	
	public List<Index> fetchIndexesFromThirdPart(){
		
		List<Map> rawData = restTemplate.getForObject("http://localhost:8090/indexes/codes.json", List.class);
		return map2Index(rawData); 
	}
	
	public List<Index> LostIndexesFromThirdPart(){
		
		System.out.println("third part is not connected");
		Index index =new Index();
		index.setCode("000000");
		index.setName("Error Code");
		return CollectionUtil.toList(index);
	}	
		
	
	private List<Index> map2Index(List<Map> rawData) {
        List<Index> indexes = new ArrayList<>();
        for (Map map : rawData) {
            String code = map.get("code").toString();
            String name = map.get("name").toString();
            Index index= new Index();
            index.setCode(code);
            index.setName(name);
            indexes.add(index);
        }
 
        return indexes;
    }
	
}
