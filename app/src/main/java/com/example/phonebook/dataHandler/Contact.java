package com.example.phonebook.dataHandler;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "contact_table")
public class Contact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String firstName;
    private String lastName;
    private String email;
    @NonNull
    private String phoneNo;
    private String address;

//    constructor

    public Contact(@NonNull String firstName, @NonNull String phoneNo) {
        this.firstName = firstName;
        this.phoneNo = phoneNo;
    }

//    getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NonNull
    public String getPhoneNo() {
        return phoneNo;
    }
    @NonNull
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
