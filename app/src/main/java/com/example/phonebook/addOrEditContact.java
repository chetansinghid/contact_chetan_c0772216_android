package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phonebook.dataHandler.Contact;
import com.example.phonebook.dataHandler.PhonebookRepository;

public class addOrEditContact extends AppCompatActivity {

    private EditText firstName, lastName, address, phoneNumber, email;
    private Button initialsText, saveButton, deleteButton;
    private boolean isNew = true;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_contact);
        initializeViews();
        checkIntent();
        setupViews();
        setupButtons();
    }

    private void checkIntent() {
        if(getIntent().getExtras() != null) {
            contact = (Contact) getIntent().getSerializableExtra("edit");
            isNew = false;
        }
    }

    private void initializeViews() {
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        address = findViewById(R.id.address);
        phoneNumber = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        initialsText = findViewById(R.id.icon_name_button);
        saveButton = findViewById(R.id.save_update_button);
        deleteButton = findViewById(R.id.delete_button);
    }

    private void setupViews() {
        if(isNew) {
            deleteButton.setVisibility(View.GONE);
        }
        else {
            char initials = contact.getFirstName().charAt(0);
            firstName.setText(contact.getFirstName());
            phoneNumber.setText(contact.getPhoneNo());
            if(contact.getLastName() != null) {
                lastName.setText(contact.getLastName());
            }
            if(contact.getEmail() != null) {
                email.setText(contact.getEmail());
            }
            if(contact.getAddress() != null) {
                address.setText(contact.getAddress());
            }
            saveButton.setText("Update");
            initialsText.setText(Character.toUpperCase(initials));
        }
    }

    private void setupButtons() {
        if(isNew) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveContact();
                }
            });
        }
        else {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateContact();
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteContact();
                }
            });
        }
    }

    private void saveContact() {
        if(checkFields()) {
            setObject();
            PhonebookRepository phonebookRepository = new PhonebookRepository(this.getApplication());
            phonebookRepository.insertContactInDatabase(contact);
            finish();
        }
    }

    private void updateContact() {
        if(checkFields()) {
            setObject();
            PhonebookRepository phonebookRepository = new PhonebookRepository(this.getApplication());
            phonebookRepository.updateContactInDatabase(contact);
            finish();
        }
    }

    private void deleteContact() {
        PhonebookRepository phonebookRepository = new PhonebookRepository(this.getApplication());
        phonebookRepository.deleteContactFromDatabase(contact);
        finish();
    }

    private void setObject() {
        if(isNew) {
            contact = new Contact(firstName.getText().toString(), Integer.parseInt(phoneNumber.getText().toString()));
        }
        else {
            contact.setFirstName(firstName.getText().toString());
            contact.setPhoneNo(Integer.parseInt(phoneNumber.getText().toString()));
        }

        if(lastName.getText() != null) {
            contact.setLastName(lastName.getText().toString());
        }
        if(email.getText() != null) {
            contact.setEmail(email.getText().toString());
        }
        if(address.getText() != null) {
            contact.setAddress(address.getText().toString());
        }

    }

    private boolean checkFields() {
        boolean status = false;
        if(firstName.getText() == null || phoneNumber.getText() == null || firstName.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(addOrEditContact.this, "First name and phone number can't be empty!", Toast.LENGTH_LONG);
        }
        else {
            status = true;
        }
        return status;
    }

}