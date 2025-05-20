package com.login.controller;

import org.springframework.web.bind.annotation.*;
import com.login.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/notify")
@CrossOrigin(origins = "http://localhost:5500")
public class NotifyController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/otp")
    public String sendOtp(@RequestParam String phone) {
        return notificationService.sendOtp(phone);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String phone, @RequestParam String otp) {
        if (notificationService.verifyOtp(phone, otp)) {
            return ResponseEntity.ok("SUCCESS");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID OTP");
        }
    }
}
