package com.rui.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

@SpringBootApplication
@EnableEurekaClient
public class ThirdPartIndexDataApplication {
	
    public static void main(String[] args) {
    	
        int port = 8090;
        int eurekaServerPort = 8761;
 
        if(NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.println("Eureka Server with PORT : "+ eurekaServerPort+" started Fail" );
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
            System.err.println("Client ThirdPartIndexDataApplication port " + port + " has been used");
            System.exit(1);
        }
        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).properties("server.port=" + port).run(args);
   
    }
       
}
