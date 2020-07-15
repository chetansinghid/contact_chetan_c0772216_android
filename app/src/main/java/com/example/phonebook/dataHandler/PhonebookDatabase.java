package com.example.phonebook.dataHandler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class PhonebookDatabase extends RoomDatabase {

    private static final String PHONEBOOK_DB = "phonebookDatabase.db";
    public static volatile PhonebookDatabase instance;
    public abstract ContactDataInterface contactDataInterface();

    static synchronized PhonebookDatabase getInstance(Context context) {
        if(instance == null) {
            instance = createInstance(context);
        }
        return instance;
    }

    private static PhonebookDatabase createInstance(final Context context) {
        return Room.databaseBuilder(context, PhonebookDatabase.class, PHONEBOOK_DB)
                .fallbackToDestructiveMigration()
                .build();
    }
}
