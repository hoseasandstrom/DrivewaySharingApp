package com.sandstromhosea.entities;

import javax.persistence.*;

/**
 * Created by hoseasandstrom on 10/3/16.
 */
@Entity
@Table(name = "parkingSpots")
public class Park {
    @Id
    @GeneratedValue
    int id;

    @Column
    int eventId;

    @Column
    boolean isDrive;

    @ManyToOne
    User users;

    public Park() {
    }

    public boolean isDrive() {
        return isDrive;
    }

    public void setDrive(boolean drive) {
        isDrive = drive;
    }

    public Park(int id, int eventId, User users) {
        this.id = id;
        this.eventId = eventId;
        this.users = users;
    }

    public Park(int eventId, User users) {
        this.eventId = eventId;
        this.users = users;
    }

    public Park(int eventId, boolean isDrive, User users) {
        this.eventId = eventId;
        this.isDrive = isDrive;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
