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
            admin.image = "http://vignette2.wikia.nocookie.net/muppet/images/7/78/AN_004.jpg/revision/latest?cb=20111004221237";
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

        if (favorites.count() == 0) {
            Favorite f = new Favorite();
            f.drink = drinks.findOne(1);
            f.favUser = users.findOneByUsername("Admin");
            Favorite g = new Favorite();
            g.drink = drinks.findOne(2);
            g.favUser = users.findOneByUsername("Admin");
            favorites.save(f);
            favorites.save(g);
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

    @RequestMapping("/register-user")
    public void addUser(HttpServletResponse response, String username, String password, String image) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        User user = new User();
        user.username = username;
        user.password = PasswordHash.createHash(password);
        user.image = image;

        users.save(user);

        response.sendRedirect("/");
    }

    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpSession session, String username, String password) throws Exception {
         User user = users.findOneByUsername(username);
        if (user == null) {
            response.sendRedirect("/register-user");
        }

        if (PasswordHash.validatePassword(password, user.password)) {
            session.setAttribute("username", username);
            response.sendRedirect("/");
        } else {
            throw new Exception("Your password is not correct");
        }
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping("/create-drink")
    public void create(HttpSession session,
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

    @RequestMapping("/add-favorite")
    public void addFav(HttpSession session, HttpServletResponse response, Integer id) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        Drink selectedDrink = drinks.findOne(id);
        if (username == null) {
            return;
        }

        Favorite fav = new Favorite();
        fav.drink = selectedDrink;
        fav.favUser = user;
        favorites.save(fav);

        response.sendRedirect("/");
    }

    @RequestMapping("/favorites")
    public List<Favorite> showFavorites(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return (List<Favorite>) favorites.findAll();
        }
        else {
            User user = users.findOneByUsername(username);
            return favorites.findAllByFavUser(user);
        }
    }

    @RequestMapping("/other-favorites")
    public List<Favorite> showOtherFavs(Integer id) {
        User otherUser = users.findOne(id);
        return favorites.findAllByFavUser(otherUser);
    }
}