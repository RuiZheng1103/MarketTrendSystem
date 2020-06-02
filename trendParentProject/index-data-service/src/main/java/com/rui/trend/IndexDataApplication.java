package com.rui.trend;
 
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
 
@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class IndexDataApplication {
    public static void main(String[] args) {
        int port = 0;
        int defaultPort = 8021;
        int redisPort = 6379;
        int eurekaServerPort = 8761;
 
        if (NetUtil.isUsableLocalPort(eurekaServerPort)) {
			System.err.printf("Eureka port %d has been used %n", eurekaServerPort);
			System.exit(1);
		}

		if (NetUtil.isUsableLocalPort(redisPort)) {
			System.err.printf("Redis port %d has been used %n", redisPort);
			System.exit(1);
		}

		if (null != args && 0 != args.length) {
			for (String arg : args) {
				if (arg.startsWith("port=")) {
					String strPort = StrUtil.subAfter(arg, "port=", true);
					if (NumberUtil.isNumber(strPort)) {
						port = Convert.toInt(strPort);
					}
				}
			}
		}       
         
        if(0==port) {
            Future<Integer> future = ThreadUtil.execAsync(() ->{
                int p = 0;
                System.out.printf("please typein custom port within 10 sec(default is %d )%n", defaultPort);
				Scanner scanner = new Scanner(System.in);
                while(true) {
                    String strPort = scanner.nextLine();
                    if(!NumberUtil.isInteger(strPort)) {
                        System.err.println("Only allow Numbers !");
                        continue;
                    }
                    else {
                        p = Convert.toInt(strPort);
                        scanner.close();
                        break;
                    }
                }
                return p;
        });
        try{
            port=future.get(10,TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e){
            port = defaultPort;
        }          
        }
         
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("Port %d has been used ，shut down now %n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(IndexDataApplication.class).properties("server.port=" + port).run(args);
         
    }
     
}
