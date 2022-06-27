package com.example.backup_restore.presentation.view.backup_sms;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.backup_restore.R;
import com.example.backup_restore.databinding.ActivityBackupSmsBinding;
import com.example.backup_restore.model.Contacts;
import com.example.backup_restore.model.Sms;
import com.example.backup_restore.presentation.adapter.SmsAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BackupSmsActivity extends AppCompatActivity {
    ActivityBackupSmsBinding mBinding;
    List<Sms> mlstSMS, current;
    SmsAdapter mSmsAdapter;
    public static final String READ_SMS = "content://sms/inbox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBackupSmsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        addView();

    }

    private void addView() {
        mlstSMS = getAllSmsFromDevice();
        mSmsAdapter = new SmsAdapter(mlstSMS);
        mBinding.rcvBackupSMS.setAdapter(mSmsAdapter);
    }

    private List<Sms> getAllSmsFromDevice() {
        current = new ArrayList<>();
        Uri uri = Uri.parse(READ_SMS);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //get row
                int idPhone = cursor.getColumnIndex("address");
                int idTime = cursor.getColumnIndex("date");
                int idBody = cursor.getColumnIndex("body");
                //get index in row
                String phone = cursor.getString(idPhone);
                String time = cursor.getString(idTime);
                String body = cursor.getString(idBody);
                //add to list
                current.add(new Sms(phone, time, body));
            }
        }
        cursor.close();
        return current;
    }
}