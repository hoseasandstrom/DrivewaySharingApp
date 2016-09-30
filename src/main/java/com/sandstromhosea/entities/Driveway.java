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
    String address;

    @Column (nullable = false)
    int price;

    @Column (nullable = false)
    String description;

    public Driveway() {
    }

    public Driveway(String address, int price, String description) {
        this.address = address;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
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
