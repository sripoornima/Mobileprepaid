package com.login.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.login.service.AdminLogService;

@RestController

@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class AdminLogController {

    @Autowired
    private AdminLogService adminLogService;

  @PostMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password) {
        return adminLogService.validateAdmin(username, password) != null;
    }
}


