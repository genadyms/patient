package com.gmail.genadyms.server.service;

import com.gmail.genadyms.web.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		return "hello world!";
	}
}