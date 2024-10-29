package com.zuhriddin.service;

import com.zuhriddin.dao.ToDoDao;
import com.zuhriddin.model.ToDo;

import java.util.*;

public class ToDoService {
    private final ToDoDao toDoDao = new ToDoDao();

    public void addToDo(ToDo toDo) {
        toDoDao.addToDo(toDo);
    }

    public List<ToDo> getMyTodos(String userid) {
        return toDoDao.getMyTodos(userid);
    }

    public void completeToDo(String toDoId){
        toDoDao.completeToDo(toDoId);
    }

    public List<ToDo> getOthersTodos(String username) {
        return toDoDao.getOthersToDos(username);
    }
}
