package com.rui.trend;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
 
import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
 
@SpringBootApplication
@EnableEurekaClient
public class TrendTradingBackTestViewApplication {
    public static void main(String[] args) {
        int port = 0;
        int defaultPort = 8041;
        int eurekaServerPort = 8761;
 
        if(NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.printf("eureka start Fail with PORT : %d%n", eurekaServerPort );
            System.exit(1);
        }
 
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
         
        if(0==port) {
            Future<Integer> future = ThreadUtil.execAsync(() ->{
                int p = 0;
                System.out.printf("Pleast input PORT number , recommand %d %n",defaultPort);
                Scanner scanner = new Scanner(System.in);
                while(true) {
                    String strPort = scanner.nextLine();
                    if(!NumberUtil.isInteger(strPort)) {
                        System.err.println("Please input PORT number");
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
            port=future.get(5,TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e){
            port = defaultPort;
        }          
        }
         
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("Port %d has been used%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(TrendTradingBackTestViewApplication.class).properties("server.port=" + port).run(args);
         
    }
}