package com.example.phonebook.dataHandler;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {

    @NonNull
    private String firstName;
    private String lastName;
    private String email;
    @PrimaryKey
    @NonNull
    private int phoneNo;
    private String address;

//    constructor

    public Contact(@NonNull String firstName, int phoneNo) {
        this.firstName = firstName;
        this.phoneNo = phoneNo;
    }

//    getters and setters

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

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
