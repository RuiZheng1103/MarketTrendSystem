package com.rui.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

import cn.hutool.core.util.NetUtil;

@SpringBootApplication
@EnableTurbine
public class IndexTurbineApplication {
	public static void main(String[] args) {
        int port = 8081;
        int eurekaServerPort = 8761;
        if(NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.printf("eureka with port %d start fail %n", eurekaServerPort );
            System.exit(1);
        }
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("Port %d has been uesd, now stop%n", port );
            System.exit(1);
        }
 
        new SpringApplicationBuilder(IndexTurbineApplication.class).properties("server.port=" + port).run(args);
   
    }
}
