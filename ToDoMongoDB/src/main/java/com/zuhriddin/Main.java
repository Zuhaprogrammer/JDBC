package com.zuhriddin;

import com.zuhriddin.dao.UserDao;
import com.zuhriddin.model.ToDo;
import com.zuhriddin.model.User;
import com.zuhriddin.service.ToDoService;
import com.zuhriddin.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanStr = new Scanner(System.in);
    private static final Scanner scanInt = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) {
        while (true) {
            int stepCode = 10;
            System.out.println("1. Register, 2. Login.");
            stepCode = scanInt.nextInt();

            if (stepCode == 1) {
                register();
            } else if (stepCode == 2) {
                login();
            }
        }
    }

    private static void register() {
        System.out.print("Enter your username: ");
        String username = scanStr.nextLine();
        System.out.print("Enter your password: ");
        String password = scanStr.nextLine();
        System.out.print("Enter your email: ");
        String email = scanStr.nextLine();
        System.out.print("Enter your phoneNumber: ");
        String phoneNumber = scanStr.nextLine();

        User user = new User(username, password, email, phoneNumber);
        userService.registerUser(user);
        System.out.println("You are successfully registered!");
    }

    private static void login() {
        System.out.print("Enter your username: ");
        String username = scanStr.nextLine();
        System.out.print("Enter your password: ");
        String password = scanStr.nextLine();

        User login = userService.login(username, password);
        todo(login);
    }

    private static void todo(User user) {
        int stepCode = 10;
        while (stepCode != 0) {
            System.out.println("1. Add Todo, 2. List My Todos, 3. Complete My Todo, 4. See others toDos");
            stepCode = scanInt.nextInt();

            if (stepCode == 1) {
                addTodo(user);
            } else if (stepCode == 2) {
                listMyTodos(user);
            } else if (stepCode == 3) {
                deleteMyCard(user);
            } else if (stepCode == 4) {
                listOthersTodos();
            }
        }
    }

    private static void addTodo(User user) {
        System.out.print("Enter todo title: ");
        String title = scanStr.nextLine();
        System.out.print("Enter todo deadline (yyyy-MM-dd): ");
        String deadline = scanStr.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ToDo toDo = new ToDo(title, date, user.getId());
        toDoService.addToDo(toDo);
        System.out.println("Your todo is successfully added!");
    }

    private static List<ToDo> listMyTodos(User user) {
        List<ToDo> todos = toDoService.getMyTodos(user.getId());

        int count = 0;
        for (ToDo toDo: todos) {
            System.out.print(count + 1 + ". ");
            System.out.println("Title: " + toDo.getTitle() + "\tDeadline: " +
                    toDo.getDeadline());
            count++;
        }
        return todos;
    }

    private static void deleteMyCard(User user) {
        List<ToDo> toDos = listMyTodos(user);
        int count = scanInt.nextInt();

        toDoService.completeToDo(toDos.get(count - 1).getId());
    }

    private static void listOthersTodos() {
        System.out.print("Enter username: ");
        String username = scanStr.nextLine();
        List<ToDo> todos = toDoService.getOthersTodos(username);

        int count = 0;
        for (ToDo toDo: todos) {
            System.out.print(count + 1 + ". ");
            System.out.println("Title: " + toDo.getTitle() + "\tDeadline: " +
                    toDo.getDeadline());
            count++;
        }
    }
}