package com.yexuman.netty_catelina.servlets;


import com.yexuman.netty_catelina.http.GPRequest;
import com.yexuman.netty_catelina.http.GPResponse;
import com.yexuman.netty_catelina.http.GPServlet;

public class FirstServlet extends GPServlet {

	
	@Override
	public void doGet(GPRequest request, GPResponse response) {
		doPost(request, response);
	}

	
	@Override
	public void doPost(GPRequest request, GPResponse response) {
		String param = "name";  
	    String str = request.getParameter(param);  
	    response.write(param + ":" + str,200);
	}
	
}
