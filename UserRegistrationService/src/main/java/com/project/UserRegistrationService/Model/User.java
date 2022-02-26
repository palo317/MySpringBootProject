package com.project.UserRegistrationService.Model;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userID;
    private String userName;
    private String emailId;
    private String password;
    private String phoneNo;
    private String  role="User";


    public User(){}

    public User(Integer userID, String userName, String emailId, String password, String phoneNo, String role) {
        this.userID = userID;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNo = phoneNo;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getphoneNo() {
        return phoneNo;
    }

    public void setphoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = "User";
    }
}
