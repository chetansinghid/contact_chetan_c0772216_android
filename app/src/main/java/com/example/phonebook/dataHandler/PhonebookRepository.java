package com.example.phonebook.dataHandler;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PhonebookRepository {

    private static ContactDataInterface contactDataInterface;

    public PhonebookRepository(Application application) {
        PhonebookDatabase phonebookDatabase = PhonebookDatabase.getInstance(application);
        contactDataInterface = phonebookDatabase.contactDataInterface();
    }

    public void insertContactInDatabase(Contact contact) {
        if(contact == null) {
            Log.i("DB insertion error!", "Null value provided");
        }
        else {
            new PhonebookRepository.InsertContact(contactDataInterface).execute(contact);
            Log.i("DB insertion!", "Added!");
        }
    }

    public void updateContactInDatabase(Contact contact) {
        if(contact == null) {
            Log.i("DB updation error!", "Null value provided");
        }
        else {
            new PhonebookRepository.UpdateContact(contactDataInterface).execute(contact);
        }
    }

    public void deleteContactFromDatabase(Contact contact) {
        if(contact == null) {
            Log.i("DB deletion error!", "Null value provided");
        }
        else {
            new PhonebookRepository.DeleteContact(contactDataInterface).execute(contact);
        }
    }

    public List<Contact> getAllContactsFromDatabase() {
        List<Contact> contactList = new ArrayList<>();
        try {
            contactList = new FetchContact(contactDataInterface).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public int getContactCountInDatabase() {
        int contactCount = 0;
        try {
            contactCount = new FetchContactCount(contactDataInterface).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contactCount;
    }


//    async methods

    private static class InsertContact extends AsyncTask<Contact, Void, Void> {

        private ContactDataInterface contactDataInterface;

        private InsertContact(ContactDataInterface contactDataInterface) {
            this.contactDataInterface = contactDataInterface;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDataInterface.insertContact(contacts[0]);
            return null;
        }
    }

    private static class UpdateContact extends AsyncTask<Contact, Void, Void> {

        private ContactDataInterface contactDataInterface;

        private UpdateContact(ContactDataInterface contactDataInterface) {
            this.contactDataInterface = contactDataInterface;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDataInterface.updateContact(contacts[0]);
            return null;
        }
    }

    private static class DeleteContact extends AsyncTask<Contact, Void, Void> {

        private ContactDataInterface contactDataInterface;

        private DeleteContact(ContactDataInterface contactDataInterface) {
            this.contactDataInterface = contactDataInterface;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDataInterface.deleteContact(contacts[0]);
            return null;
        }
    }

    private static class FetchContact extends AsyncTask<Void, Void, List<Contact>> {

        private ContactDataInterface contactDataInterface;

        private FetchContact(ContactDataInterface contactDataInterface) {
            this.contactDataInterface = contactDataInterface;
        }

        @Override
        protected List<Contact> doInBackground(Void... voids) {
            return contactDataInterface.getAllContacts();
        }
    }

    private static class FetchContactCount extends AsyncTask<Void, Void, Integer> {

        private ContactDataInterface contactDataInterface;

        private FetchContactCount(ContactDataInterface contactDataInterface) {
            this.contactDataInterface = contactDataInterface;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return contactDataInterface.getContactCount();
        }
    }
}
