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

    @Column (nullable = false)
    Double lat;

    @Column (nullable = false)
    Double lng;

    public Driveway() {
    }

    public Driveway(String address, int price, String description) {
        this.address = address;
        this.price = price;
        this.description = description;
    }

    public Driveway(String address, int price, String description, Double lat, Double lng) {
        this.address = address;
        this.price = price;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
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
