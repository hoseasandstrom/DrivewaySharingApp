package com.sandstromhosea.entities;

import javax.persistence.*;

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
    String name;

    @Column (nullable = false)
    String price;

    @Column (nullable = false)
    String description;

    @Column
    String address;

    @Column (nullable = false)
    Double lat;

    @Column (nullable = false)
    Double lng;

    public Driveway() {
    }

    public Driveway(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Driveway(String name, String price, String description, String address, Double lat, Double lng) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public Driveway(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
