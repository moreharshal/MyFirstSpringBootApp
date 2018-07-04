package com.management.system.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.beans.LonginBean;

@RestController
public class MyRestController {
	
	 @RequestMapping(value = "/test", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody LonginBean getData() {
	    	System.out.println(" I am here in test");	   
	    	LonginBean bean = new LonginBean();
	    	bean.setPassword("Testrs");
	    	bean.setUsername("Testese");
	        return bean;
	    }
}
