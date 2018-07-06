package com.management.system.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.beans.LonginBean;

@RestController
public class MyRestController {
	
	 @RequestMapping(value = "/test", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody LonginBean getData(@RequestBody LonginBean loginBe) {
		 	System.out.println(loginBe.getUsername());
		 	System.out.println(loginBe.getPassword());	     
	        return loginBe;
	    }
}
