package com.management.system.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String setAgentPollInterval(@RequestBody String json) {
        System.out.println(" UserName "+ json);
        return "";
    }
}
