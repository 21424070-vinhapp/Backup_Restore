package com.example.backup_restore.presentation.view.backup_contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.backup_restore.presentation.adapter.ContactAdapter;
import com.example.backup_restore.databinding.ActivityBackupContactBinding;
import com.example.backup_restore.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class BackupContactActivity extends AppCompatActivity {
    ActivityBackupContactBinding mBidBinding;
    List<Contacts> lstContact;
    List<Contacts> current;
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBidBinding=ActivityBackupContactBinding.inflate(getLayoutInflater());
        setContentView(mBidBinding.getRoot());

        addView();
    }

    private List<Contacts> getAllContacFromDevice() {
        current=new ArrayList<>();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String idName=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
                int colNameIndex=cursor.getColumnIndex(idName);
                String name="Name: "+cursor.getString(colNameIndex);

                String idPhone=ContactsContract.CommonDataKinds.Phone.NUMBER;
                int colPhoneIndex=cursor.getColumnIndex(idPhone);
                String phone="Phone: "+cursor.getString(colPhoneIndex);
                //Log.d("bbb", "getAllContacFromDevice: "+ name + " "+ phone);
                current.add(new Contacts(name,phone));
            }
        }
        cursor.close();
        return current;
    }

    private void addView() {
        lstContact=getAllContacFromDevice();
        contactAdapter=new ContactAdapter(lstContact);
        mBidBinding.rcvBackupContact.setAdapter(contactAdapter);
    }
}