package com.example.phonebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebook.dataHandler.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListHolder> {

    private List<Contact> contactList;
    private Context context;

    public ContactListAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.contact_cell, parent, false);
        return new ContactListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListHolder holder, final int position) {
        String name;
        char buttonText =contactList.get(position).getFirstName().charAt(0);
        if(contactList.get(position).getLastName() == null) {
            name = contactList.get(position).getFirstName();
        }
        else {
            name = contactList.get(position).getFirstName() + " " + contactList.get(position).getLastName();
        }
        holder.contactName.setText(name);
        holder.phoneNo.setText(contactList.get(position).getPhoneNo());
        holder.iconButton.setText(Character.toUpperCase(buttonText));
        holder.cellLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, addOrEditContact.class);
                intent.putExtra("edit", contactList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactListHolder extends RecyclerView.ViewHolder {

        Button iconButton;
        TextView contactName, phoneNo;
        ConstraintLayout cellLayout;

        public ContactListHolder(@NonNull View itemView) {
            super(itemView);
            iconButton = itemView.findViewById(R.id.icon_button);
            contactName = itemView.findViewById(R.id.contact_name);
            phoneNo = itemView.findViewById(R.id.contact_number);
            cellLayout = itemView.findViewById(R.id.contact_cell_layout);
        }
    }
}
