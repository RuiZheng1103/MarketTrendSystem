package com.rui.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;


@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCaching
public class IndexGatherStoreApplication {
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
    public static void main(String[] args) {
    	
        int port = 8001;
        int redisPort = 6379;
        int eurekaServerPort = 8761;
 
        if(NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.println("Eureka Server with PORT : "+ eurekaServerPort+" started Fail" );
            System.exit(1);
        }
        
        if(NetUtil.isUsableLocalPort(redisPort)) {
            System.err.println("redis Server with PORT : "+ redisPort+" started Fail" );
            System.exit(1);
        }
        
//      use custom port as this client port (configure it in 'Program Arguments')
        if(null!=args && 0!=args.length) {
            for (String arg : args) {
                if(arg.startsWith("port=")) {
                    String strPort= StrUtil.subAfter(arg, "port=", true);
                    if(NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }
          
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.println("Client IndexGatherStoreApplication port " + port + " has been used");
            System.exit(1);
        }
        new SpringApplicationBuilder(IndexGatherStoreApplication.class).properties("server.port=" + port).run(args);
   
    }
       
}
