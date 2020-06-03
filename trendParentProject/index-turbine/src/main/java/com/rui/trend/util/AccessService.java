package com.rui.trend.util;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

public class AccessService {
	public static void main(String[] args) {
        while(true) {
            ThreadUtil.sleep(1000);
            access(8051);
            access(8052);
        }
    }
 
    public static void access(int port) {
        try {
            String html= HttpUtil.get(String.format("http://127.0.0.1:%d/simulate/399975/20/1.01/0.99/0/null/null/",port));
            System.out.printf("Port %d is available %n" ,port);
        }
        catch(Exception e) {
            System.err.printf("Port: %d cannot be reached %n",port);
        }
    }
}

