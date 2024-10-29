package com.zuhriddin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDo {
    private String id;
    private String title;
    private Date deadline;
    private String userId;
    private boolean active = true;
    private Date createdDate;
    private Date updatedDate;

    public ToDo(String title, Date deadline, String userId) {
        this.title = title;
        this.deadline = deadline;
        this.userId = userId;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public ToDo(Document doc) {
        this.id = String.valueOf(doc.getObjectId("_id"));
        this.title = doc.getString("title");
        this.deadline = doc.getDate("deadline");
        this.userId = String.valueOf(doc.getObjectId("userId"));
        this.createdDate = doc.getDate("createdDate");
        this.updatedDate = doc.getDate("updatedDate");
    }
}
