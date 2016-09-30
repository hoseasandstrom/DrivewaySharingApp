package com.sandstromhosea.entities;


import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Created by hoseasandstrom on 9/30/16.
 */
@Entity
@Table(name = "driveways")
public class Driveway {
    @Id
    @GeneratedValue
    int id;

    @Column (nullable = false)
    String location;

    @Column (nullable = false)
    int price;

    @Column (nullable = false)
    String description;

    public Driveway() {
    }

    public Driveway(String location, int price, String description) {
        this.location = location;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
