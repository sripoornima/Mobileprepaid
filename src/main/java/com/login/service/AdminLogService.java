package com.login.service;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.login.entity.AdminLog;
import com.login.repo.AdminLogRepository;

@Service
public class AdminLogService {

    @Autowired
    private AdminLogRepository adminLogRepository;

    public AdminLog validateAdmin(String username, String password) {
        AdminLog admin = adminLogRepository.findByUsernameAndPassword(username, password);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin; 
        }
        return null; 
    }
}
