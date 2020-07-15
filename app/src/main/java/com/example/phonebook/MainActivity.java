package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.phonebook.dataHandler.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}