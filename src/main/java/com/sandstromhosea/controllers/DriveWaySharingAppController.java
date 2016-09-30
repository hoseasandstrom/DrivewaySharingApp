package com.sandstromhosea.controllers;

import com.sandstromhosea.entities.User;
import com.sandstromhosea.services.DrivewayRepository;
import com.sandstromhosea.services.UserRepository;
import com.sandstromhosea.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by hoseasandstrom on 9/30/16.
 */
@RestController
public class DriveWaySharingAppController {
    @Autowired
    UserRepository users;

    @Autowired
    DrivewayRepository driveways;

    //user and password check(passwords are hashed)
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login (HttpSession session, @RequestBody User user) throws Exception {
        User dummy = users.findByUsername(user.getUsername());
        if (dummy == null ) {
            throw new Exception("Username does not exist! Please register");
        } else if (!PasswordStorage.verifyPassword(user.getPassword(), dummy.getPassword())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("username", user.getUsername());
    }

    //registering new user
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register (HttpSession session, @RequestBody User user) throws Exception {
        User regi = users.findByUsername(user.getUsername());
        if (regi == null) {
            regi = new User(user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), PasswordStorage.createHash(user.getPassword()));
            users.save(regi);
            session.setAttribute("username", user.getUsername());
        }
    }

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public Iterable<User> getUsers () {
        return users.findAll();
    }

}
