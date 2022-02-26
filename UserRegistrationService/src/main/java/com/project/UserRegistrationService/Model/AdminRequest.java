package com.project.UserRegistrationService.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminRequest {

    @Id
    private String emailId;
    private String user_name;
    @Column(columnDefinition = "varchar(64) default 'Pending'" )
    private String status = "Pending";

    public AdminRequest(){}

    public AdminRequest(String emailId, String user_name) {
        this.emailId = emailId;
        this.user_name = user_name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
