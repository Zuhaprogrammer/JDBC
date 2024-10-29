package com.zuhriddin;

import com.zuhriddin.model.Card;
import com.zuhriddin.model.User;
import com.zuhriddin.service.CardService;
import com.zuhriddin.service.UserService;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanStr = new Scanner(System.in);
    private static final Scanner scanInt = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CardService cardService = new CardService();

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
        User registerUser = userService.registerUser(user);
        if (registerUser == null) {
            System.out.println("Your email or password is incorrect!");
        } else {
            System.out.println("You are successfully registered!");
        }
    }

    private static void login() {
        System.out.print("Enter your username: ");
        String username = scanStr.nextLine();
        System.out.print("Enter your password: ");
        String password = scanStr.nextLine();

        User login = userService.login(username, password);
        card(login);
    }

    private static void card(User user) {
        int stepCode = 10;
        while (stepCode != 0) {
            System.out.println("1. Add Card, 2. List My Cards, 3. Delete My Card");
            stepCode = scanInt.nextInt();

            if (stepCode == 1) {
                addCard(user);
            } else if (stepCode == 2) {
                listMyCards(user);
            } else if (stepCode == 3) {
                deleteMyCard(user);
            }
        }
    }

    private static void addCard(User user) {
        System.out.print("Enter card name: ");
        String name = scanStr.nextLine();
        System.out.print("Enter card number: ");
        String number = scanStr.nextLine();
        System.out.print("Enter expiry date: ");
        String expiryDate = scanStr.nextLine();
        System.out.print("Enter card password: ");
        String password = scanStr.nextLine();
        System.out.print("Enter balance: ");
        double balance = scanInt.nextDouble();

        Card card = new Card(name, number, password, expiryDate, balance, user.getId());
        Card addCard = cardService.addCard(card);
        System.out.println("Your card is successfully added!");
    }

    private static List<Card> listMyCards(User user) {
        List<Card> cards = cardService.getMyCards(user.getId());

        int count = 0;
        for (Card card: cards) {
            System.out.print(count + 1 + ". ");
            System.out.println("CardName: " + card.getName() + "\tCardNumber: " +
                    card.getCardNumber() + "\tCardExpiryDate: " + card.getExpiryDate() +
                    "\tCardBalance: " + card.getBalance());
            count++;
        }
        return cards;
    }

    private static void deleteMyCard(User user) {
        List<Card> cards = listMyCards(user);
        int count = scanInt.nextInt();

        cardService.deleteMyCard(user.getId(), cards.get(count - 1).getId());
    }
}