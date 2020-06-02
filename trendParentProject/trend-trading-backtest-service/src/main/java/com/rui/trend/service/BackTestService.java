package com.rui.trend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.trend.client.IndexDataClient;
import com.rui.trend.pojo.IndexData;

@Service
public class BackTestService {
	
    @Autowired IndexDataClient indexDataClient;
     
    public List<IndexData> listIndexData(String code){
        List<IndexData> result = indexDataClient.getIndexData(code);
        Collections.reverse(result);
         
        for (IndexData indexData : result) {
            System.out.println(indexData.getDate());
        }
         
        return result;
    }
}
