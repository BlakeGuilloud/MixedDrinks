package com.theironyard.controllers;

import com.theironyard.entities.Drink;
import com.theironyard.entities.Favorite;
import com.theironyard.entities.User;
import com.theironyard.services.DrinkRepository;
import com.theironyard.services.FavoriteRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jack on 11/19/15.
 */

@RestController
public class MixedDrinksController {
    @Autowired
    DrinkRepository drinks;
    @Autowired
    UserRepository users;
    @Autowired
    FavoriteRepository favorites;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException {
        User admin = users.findOneByUsername("Admin");
        if (admin == null) {
            admin = new User();
            admin.username = "Admin";
            admin.password = PasswordHash.createHash("Admin");
            users.save(admin);
        }

        if (drinks.count() == 0) {
            Scanner scanner = new Scanner(new File("mixeddrinks1.csv"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");

                Drink drink = new Drink();

                try {
                    drink.name = columns[1];
                    drink.ingredient1 = columns[2];
                    drink.ingredient2 = columns[3];
                    drink.ingredient3 = columns[4];
                    drink.ingredient4 = columns[5];
                    drink.ingredient5 = columns[6];
                    drink.ingredient6 = columns[7];
                    drink.ingredient7 = columns[8];
                    drink.ingredient8 = columns[9];
                    drink.ingredient9 = columns[10];
                    drink.ingredient10 = columns[11];
                    drink.ingredient11 = columns[12];
                    drink.ingredient12 = columns[13];
                } catch (Exception e) {
                }
                drink.user = admin;
                drinks.save(drink);
            }
        }
    }

    @RequestMapping("/drinks")
    public List<Drink> drinks() {
        return (List<Drink>) drinks.findAll();
    }

    @RequestMapping("/users")
    public List<User> users() {
        return (List<User>) users.findAll();
    }

    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpSession session, String username, String password, String image) throws Exception {
        session.setAttribute("username", username);
        User user = users.findOneByUsername(username);
        if (user == null) {
            user = new User();
            user.username = username;
            user.password = PasswordHash.createHash(password);
            user.image = image;
            users.save(user);
        }
/*
        if (!password.equals(user.password) || username == null || password == null) {
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/");
        }
        */
        else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Wrong password, try again!");
        }
        else if (username == null || password == null) {
            throw new Exception("Please enter both a username and password!");
        }

        response.sendRedirect("/");
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping("/create")
    public void create(HttpSession session,
                         HttpServletResponse response,
                         String name,
                         String ingredient1,
                         String ingredient2,
                         String ingredient3,
                         String ingredient4,
                         String ingredient5,
                         String ingredient6,
                         String ingredient7,
                         String ingredient8,
                         String ingredient9,
                         String ingredient10,
                         String ingredient11,
                         String ingredient12) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in");
        }
        User user = users.findOneByUsername(username);
        Drink drink = new Drink();
        drink.name = name;
        drink.ingredient1 = ingredient1;
        drink.ingredient2 = ingredient2;
        drink.ingredient3 = ingredient3;
        drink.ingredient4 = ingredient4;
        drink.ingredient5 = ingredient5;
        drink.ingredient6 = ingredient6;
        drink.ingredient7 = ingredient7;
        drink.ingredient8 = ingredient8;
        drink.ingredient9 = ingredient9;
        drink.ingredient10 = ingredient10;
        drink.ingredient11 = ingredient11;
        drink.ingredient12 = ingredient12;
        drink.user = user;
        drinks.save(drink);
    }

    @RequestMapping("/favorite")
    public void addFav(HttpSession session, HttpServletResponse response, int id) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        Drink selectedDrink = drinks.findOne(id);

        if (username == null) {
            response.sendRedirect("/");
        }

        Favorite fav = new Favorite();
        fav.favUser = user;
        fav.drink = selectedDrink;

        favorites.save(fav);
    }

/*
    @RequestMapping("/created")
    public
 */
}