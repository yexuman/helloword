package com.yexuman.netty_catelina.servlets;

import com.alibaba.fastjson.JSON;
import com.yexuman.netty_catelina.http.GPRequest;
import com.yexuman.netty_catelina.http.GPResponse;
import com.yexuman.netty_catelina.http.GPServlet;


public class SecondServlet extends GPServlet {

	@Override
	public void doGet(GPRequest request, GPResponse response) {
		doPost(request, response);
	}
	
	@Override
	public void doPost(GPRequest request, GPResponse response) {
	    String str = JSON.toJSONString(request.getParameters(),true);  
	    response.write(str,200);
	}
	
}
