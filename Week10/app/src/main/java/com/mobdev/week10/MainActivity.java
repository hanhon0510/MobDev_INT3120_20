package com.mobdev.week10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_list;
    List<String> list;
    EditText newName, oldName;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_list = findViewById(R.id.lv_list);

        newName = findViewById(R.id.editText);
        oldName = findViewById(R.id.editText1);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateContactName(oldName.getText().toString(), newName.getText().toString());
            }
        });

    }

    public void viewContacts(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    999);
        } else {
            Uri uri = Uri.parse("content://contacts/people");
            list = new ArrayList<>();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    list.add(name);
                    cursor.moveToNext();
                }
                cursor.close();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, list);
                lv_list.setAdapter(arrayAdapter);
            }
        }
    }

    public void updateContactName(String oldName, String newName) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CONTACTS},
                    999);
        } else {
            ContentResolver contentResolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ContactsContract.Data.DISPLAY_NAME, newName);

            String where = ContactsContract.Data.DISPLAY_NAME + " = ?";
            String[] params = new String[]{oldName};

            contentResolver.update(Uri.parse("content://contacts/people"), contentValues, where, params);
        }

    }


}