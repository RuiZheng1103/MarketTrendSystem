package com.rui.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import cn.hutool.core.util.NetUtil;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableDiscoveryClient
public class IndexZuulServiceApplication {

    public static void main(String[] args) {
        int port = 8888;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("PORT %d has been used %n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(IndexZuulServiceApplication.class).properties("server.port=" + port).run(args);
    }
}
