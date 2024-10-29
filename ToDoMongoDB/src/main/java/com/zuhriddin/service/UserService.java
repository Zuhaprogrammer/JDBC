package com.zuhriddin.service;

import com.zuhriddin.dao.UserDao;
import com.zuhriddin.model.User;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void registerUser(User user) {
        if(checkLengthOfPassword(user.getPassword()) && checkCharactersInPassword(user.getPassword())
                && checkEmail(user.getEmail())) {
            userDao.addUser(user);
        }
    }

    private boolean checkLengthOfPassword(String password) {
        return password.length() >= 8;
    }

    private boolean checkCharactersInPassword(String password) {
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        boolean otherCharacter = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                upperCase = true;
            } else if (Character.isLowerCase(password.charAt(i))) {
                lowerCase = true;
            } else if (Character.isDigit(password.charAt(i))) {
                digit = true;
            } else if (!Character.isLetterOrDigit(password.charAt(i))) {
                otherCharacter = true;
            }
        }
        return upperCase && lowerCase && digit && otherCharacter;
    }

    private boolean checkEmail(String email) {
        return email.contains("@gmail.com") || email.contains("@mail.ru");
    }

    public User login(String username, String password) {
        return userDao.checkUser(username, password);
    }
}
