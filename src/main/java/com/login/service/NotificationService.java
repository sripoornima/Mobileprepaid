package com.login.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

@Service
public class NotificationService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.from_number}")
    private String fromNumber;

    // Initialize SDK after Spring injects the config values
    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }

    // OTP storage with timestamp
    private static final Map<String, OtpData> otpStore = new HashMap<>();

    public String sendOtp(String toNumber) {
        String normalizedNumber = normalizePhoneNumber(toNumber);
        System.out.println("sendOtp() - Normalized number: " + normalizedNumber);

        String otp = generateOtp();
        otpStore.put(normalizedNumber, new OtpData(otp, System.currentTimeMillis()));

        String body = "Your OTP is: " + otp;

        Message message = Message.creator(
                new PhoneNumber(normalizedNumber),
                new PhoneNumber(fromNumber),
                body
        ).create();

        System.out.println("OTP Sent to " + normalizedNumber + ". SID: " + message.getSid());
        return otp;
    }

    public boolean verifyOtp(String phone, String otp) {
        String normalizedNumber = normalizePhoneNumber(phone);
        System.out.println("verifyOtp() - Normalized number: " + normalizedNumber);

        OtpData otpData = otpStore.get(normalizedNumber);

        if (otpData != null) {
            // Expire OTP after 5 minutes
            if (System.currentTimeMillis() - otpData.timestamp > 5 * 60 * 1000) {
                otpStore.remove(normalizedNumber);
                System.out.println("OTP expired for " + normalizedNumber);
                return false;
            }
            if (otpData.code.equals(otp)) {
                otpStore.remove(normalizedNumber);
                System.out.println("OTP verified successfully for " + normalizedNumber);
                return true;
            }
        }

        System.out.println("OTP verification failed for " + normalizedNumber + ". Expected OTP: " 
            + (otpData != null ? otpData.code : "null") + ", Received OTP: " + otp);
        return false;
    }

    private String normalizePhoneNumber(String phone) {
        phone = phone.replaceAll("\\s+", ""); // remove all spaces
        if (phone.startsWith("0")) {
            phone = phone.substring(1); // remove leading 0
        }
        if (!phone.startsWith("+")) {
            if (phone.startsWith("91")) {
                phone = "+" + phone;
            } else {
                phone = "+91" + phone;
            }
        }
        return phone;
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Helper inner class for OTP + time stamp
    private static class OtpData {
        String code;
        long timestamp;

        OtpData(String code, long timestamp) {
            this.code = code;
            this.timestamp = timestamp;
        }
    }
}
