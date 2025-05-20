package com.login.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "adminlog")
public class AdminLog {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminid;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;

    // Getters and Setters
    public int getAdminid() { return adminid; }
    public void setAdminid(int adminid) { this.adminid = adminid; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
