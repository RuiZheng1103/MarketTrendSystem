package com.rui.trend.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent>{
//	Obtain current Client's server port
	private int serverPORT;
	
	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		this.serverPORT = event.getWebServer().getPort();
	}
	
	public int getPort() {
		return this.serverPORT;
	}
}
