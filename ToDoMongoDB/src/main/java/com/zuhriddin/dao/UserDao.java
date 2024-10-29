package com.zuhriddin.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zuhriddin.model.User;
import org.bson.Document;

import java.util.*;

public class UserDao {
    private static final String DATABASE = "to_do";

    public void addUser(User user) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> users = database.getCollection("users");

            Document newDoc = new Document("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("email", user.getEmail())
                    .append("phoneNumber", user.getPhoneNumber())
                    .append("active", user.isActive())
                    .append("createdDate", user.getCreatedDate())
                    .append("updatedDate", user.getUpdatedDate());
            users.insertOne(newDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User checkUser(String username, String password) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> users = database.getCollection("users");

            Document query = new Document("username", username)
                    .append("password", password)
                    .append("active", true);

            Document doc = users.find(query).first();
            if (doc != null) {
                return new User(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
