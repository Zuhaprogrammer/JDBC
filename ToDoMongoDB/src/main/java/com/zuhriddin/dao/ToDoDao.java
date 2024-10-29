package com.zuhriddin.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zuhriddin.model.ToDo;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

public class ToDoDao {
    private static final String DATABASE = "to_do";

    public void addToDo(ToDo toDo) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> toDos = database.getCollection("toDo");

            ObjectId objectId = new ObjectId(toDo.getUserId());

            Document newDoc = new Document("title", toDo.getTitle())
                    .append("active", toDo.isActive())
                    .append("deadline", toDo.getDeadline())
                    .append("userId", objectId)
                    .append("createdDate", toDo.getCreatedDate())
                    .append("updatedDate", toDo.getUpdatedDate());
            toDos.insertOne(newDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ToDo> getMyTodos(String userId) {
        List<ToDo> toDoList = new ArrayList<>();
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> toDos = database.getCollection("toDo");

            ObjectId objectId = new ObjectId(userId);

            Document query = new Document("userId", objectId)
                    .append("active", true);
            for (Document doc: toDos.find(query)) {
                ToDo toDo = new ToDo(doc);
                toDoList.add(toDo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    public void completeToDo(String toDoId) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> toDos = database.getCollection("toDo");

            ObjectId objectId = new ObjectId(toDoId);

            Document filter = new Document("_id", objectId);

            Document update = new Document("$set", new Document("active", false));

            toDos.updateOne(filter, update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ToDo> getOthersToDos(String username) {
        List<ToDo> toDoList = new ArrayList<>();
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<Document> toDos = database.getCollection("toDo");
            MongoCollection<Document> users = database.getCollection("users");

            Document query = new Document("username", username)
                    .append("active", true);
            Document first = users.find(query).first();
            ObjectId userId = null;
            if (first != null) {
                userId = first.getObjectId("_id");
            }

            Document filter = new Document("userId", userId)
                    .append("active", true);
            for (Document doc: toDos.find(filter)) {
                ToDo toDo = new ToDo(doc);
                toDoList.add(toDo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toDoList;
    }
}
