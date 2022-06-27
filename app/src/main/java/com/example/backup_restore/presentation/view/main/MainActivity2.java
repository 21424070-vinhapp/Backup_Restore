package com.example.backup_restore.presentation.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.backup_restore.presentation.view.backup_call_logs.BackupCallLogActivity;
import com.example.backup_restore.presentation.view.backup_contact.BackupContactActivity;
import com.example.backup_restore.databinding.MainBinding;
import com.example.backup_restore.presentation.view.backup_sms.BackupSmsActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity2 extends AppCompatActivity {
    MainBinding main2Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2Binding=MainBinding.inflate(getLayoutInflater());
        setContentView(main2Binding.getRoot());

        addview();
        event();

    }

    private void addview() {
        //get person name from gg account
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
            main2Binding.txtTextView.setText(personName);
//            Log.d("BBB", "onCreate: " + personEmail);
//            Log.d("BBB", "onCreate: " + personGivenName);
//            Log.d("BBB", "onCreate: " + personFamilyName);
//            Log.d("BBB", "onCreate: " + personId);
        }
    }

    private void event() {
        main2Binding.btnBackupContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackupContactActivity();
            }
        });

        main2Binding.btnBackupSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackupSmsActivity();
            }
        });

        main2Binding.btnBackupCallLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackupCallLogsActivity();
            }
        });
    }

    private void openBackupCallLogsActivity() {
        //check permission
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,new String[]{Manifest.permission.READ_CALL_LOG},0);
        }
        else
        {
            Intent intent=new Intent(MainActivity2.this, BackupCallLogActivity.class);
            startActivity(intent);
        }
    }

    private void openBackupSmsActivity() {
        //check permission
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,new String[]{Manifest.permission.READ_SMS},0);
        }
        else
        {
            Intent intent=new Intent(MainActivity2.this, BackupSmsActivity.class);
            startActivity(intent);
        }

    }

    private void openBackupContactActivity() {
        //check permission
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,new String[]{Manifest.permission.READ_CONTACTS},0);
        }
        else{
            Intent intent=new Intent(MainActivity2.this, BackupContactActivity.class);
            startActivity(intent);
        }
    }
}