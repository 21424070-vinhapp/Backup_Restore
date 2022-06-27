package com.example.backup_restore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

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
        //check permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},0);
        }
        current=new ArrayList<>();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String idName=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
                int colNameIndex=cursor.getColumnIndex(idName);
                String name=cursor.getString(colNameIndex).substring(1,2);

                String idPhone=ContactsContract.CommonDataKinds.Phone.NUMBER;
                int colPhoneIndex=cursor.getColumnIndex(idPhone);
                String phone=cursor.getString(colPhoneIndex).substring(1,3);
                Log.d("bbb", "getAllContacFromDevice: "+ name + " "+ phone);
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