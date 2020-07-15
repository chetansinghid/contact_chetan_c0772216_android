package com.example.phonebook.dataHandler;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDataInterface {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertContact(Contact contact);

    @Update(onConflict = OnConflictStrategy.ABORT)
    void updateContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);

    @Transaction
    @Query("SELECT * FROM contact_table ORDER BY firstName ASC")
    List<Contact> getAllContacts();

    @Transaction
    @Query("SELECT COUNT(*) FROM contact_table ORDER BY firstName ASC")
    int getContactCount();
}
