package com.rui.trend.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.rui.trend.pojo.Index;
import com.rui.trend.service.IndexDataService;
import com.rui.trend.service.IndexService;

import cn.hutool.core.date.DateUtil;

public class IndexDataSyncJob extends QuartzJobBean {
    
    @Autowired
    private IndexService indexService;
 
    @Autowired
    private IndexDataService indexDataService;
     
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Start Time ：" + DateUtil.now());
        List<Index> indexes = indexService.fresh();
        for (Index index : indexes) {
             indexDataService.fresh(index.getCode());
        }
        System.out.println("End Time：" + DateUtil.now());
 
    }
 
}
