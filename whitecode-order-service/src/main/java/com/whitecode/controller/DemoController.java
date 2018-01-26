package com.whitecode.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('USER')")
    public String getDemo(){
        return "order test...";
    }
}
