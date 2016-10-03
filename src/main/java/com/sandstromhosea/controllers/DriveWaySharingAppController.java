package com.sandstromhosea.controllers;

import com.google.maps.*;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlacesSearchResponse;

import com.sandstromhosea.entities.Driveway;
import com.sandstromhosea.entities.Park;
import com.sandstromhosea.entities.User;
import com.sandstromhosea.services.DrivewayRepository;
import com.sandstromhosea.services.ParkRepository;
import com.sandstromhosea.services.UserRepository;
import com.sandstromhosea.utils.PasswordStorage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.driveController;
import org.h2.tools.Server;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by hoseasandstrom on 9/30/16.
 */
@RestController
public class DriveWaySharingAppController {
    @Autowired
    UserRepository users;

    @Autowired
    DrivewayRepository driveways;

    @Autowired
    ParkRepository parkingSpots;

    String APIkey = APIreader();

    public DriveWaySharingAppController() throws Exception {

    }

    @PostConstruct
    public void init() throws Exception {
        Server.createWebServer();
    }

    //creating driveways table
           if (driveways.count() == 0) {
            String filename = "Driveways.csv";
            File f = new File(filename);
            Scanner filescanner = new Scanner(f);
            filescanner.nextLine();
            while (filescanner.hasNext()) {
                String line = filescanner.nextLine();
                String[] columns = line.split("\\,");
                GeoApiContext context = new GeoApiContext()
                        .setApiKey(APIkey);
                TextSearchRequest request = PlacesApi.textSearchQuery(context, columns[2] + " Charleston");
                PlacesSearchResponse results = request.await();


                Driveway driveway = new Driveway(columns[0],
                        columns[1],
                        columns[2],
                        results.results[0].formattedAddress,
                        results.results[0].geometry.location.lat,
                        results.results[0].geometry.location.lng);
                driveways.save(driveway);
        }
    }

    Iterable<Driveway> drives = driveways.findAll();
        for (Driveway drive : drives) {
            if (drive.getAddress() == null || drive.getLat() == null || drive.getLng() == null){
                GeoApiContext context = new GeoApiContext()
                        .setApiKey(" ");
                TextSearchRequest request = PlacesApi.textSearchQuery(context, drive.getAddressInput() + " Charleston");
                PlacesSearchResponse results = request.await();
                if (drive.getLat() == null){
                    drive.setLat(results.results[0].geometry.location.lat);
                }
                if (drive.getLng() == null ) {
                    drive.setLng(results.results[0].geometry.location.lng);
                }
                if (drive.getAddress() == null) {
                    drive.setAddress(results.results[0].formattedAddress);
                }
                driveways.save(drive);
            }
    }

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

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/parkingspots", method = RequestMethod.POST)

    public void addItinerary(HttpSession session, @RequestBody HashMap data) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You must be registered");
        }

        Park park = new Park();
        User user = users.findByUsername(username);
        String addressInput = (String) data.get("addressInput");
        Driveway temp = driveways.findFirstByAddressInput(addressInput);
        if (driveways.findFirstByAddressInput(addressInput)==null) {
            park.setDrive(false);
            int id = (int) data.get("id");
            park.setEventId(id);
            park.setUsers(user);
            Park spots = parkingSpots.findFirstByEventid(id);
            if (spots==null || spots.getUsers() != park.getUsers()) {
                parkingSpots.save(park);
            }
        } else {
            park.setDrive(true);
            int id = (int) data.get("id");
            park.setEventId(id);
            park.setUsers(user);
            Park spots = parkingSpots.findFirstByEventid(id);
            if (spots==null || spots.getUsers() != spots.getUsers()) {
                parkingSpots.save(park);
            }
        }

    }

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public Iterable<User> getUsers () {
        return users.findAll();
    }

    @RequestMapping (path = "/driveways", method = RequestMethod.GET)
    public Iterable<Driveway> getDrives () {

        return driveways.findAll();
    }

    public String APIreader () throws FileNotFoundException {
        File file = new File("API.csv");
        Scanner fscan  = new Scanner(file);
        return fscan.nextLine();
    }

}
