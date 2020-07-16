package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.phonebook.dataHandler.Contact;
import com.example.phonebook.dataHandler.PhonebookRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST = 1;
    private PhonebookRepository phonebookRepository;
    private List<Contact> contactList = new ArrayList();
    private int contactCount = 0;
    private RecyclerView recyclerView;
    private ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkRequiredPermissions();
        phonebookRepository = new PhonebookRepository(this.getApplication());
        fetchDataForList();
        setupListData();

    }

    public void addContact(View view) {
        Intent intent = new Intent(this, addOrEditContact.class);
        this.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDataForList();
        contactListAdapter.updateData(contactList);
        contactListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fetchDataForList();
        contactListAdapter.updateData(contactList);
        contactListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        Log.i("In Activity reenter", "");
        fetchDataForList();
        contactListAdapter.updateData(contactList);
        contactListAdapter.notifyDataSetChanged();
    }

    private void fetchDataForList() {
        contactList = phonebookRepository.getAllContactsFromDatabase();
        contactCount = phonebookRepository.getContactCountInDatabase();
    }

    private void setupListData() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        contactListAdapter = new ContactListAdapter(MainActivity.this, contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(contactListAdapter);
    }

    private void checkRequiredPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                Toast.makeText(MainActivity.this, "Permissions required to save data in device!", Toast.LENGTH_LONG);
            } else
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST);

            }
        }
    }
}