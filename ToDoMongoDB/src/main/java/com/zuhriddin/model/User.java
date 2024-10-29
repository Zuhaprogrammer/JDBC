package com.zuhriddin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean active = true;
    private Date createdDate;
    private Date updatedDate;

    public User (String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public User (Document doc) {
        this.id = String.valueOf(doc.getObjectId("_id"));
        this.username = doc.getString("username");
        this.email = doc.getString("email");
        this.phoneNumber = doc.getString("phoneNumber");
        this.createdDate = doc.getDate("createdDate");
        this.updatedDate = doc.getDate("updatedDate");
    }
}
