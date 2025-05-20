package com.login.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.login.entity.AdminLog;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Integer> {
    AdminLog findByUsernameAndPassword(String username,String password);
}

