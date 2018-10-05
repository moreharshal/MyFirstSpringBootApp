package com.management.system.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/authenticate")
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String setAgentPollInterval(LoginBean json) {
        System.out.println(" UserName "+ json.getUsername());
        return "sdfgdf";
    }
}
