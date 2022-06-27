package com.example.backup_restore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.backup_restore.databinding.MainBinding;
import com.example.backup_restore.databinding.MainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    MainBinding main2Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2Binding=MainBinding.inflate(getLayoutInflater());
        setContentView(main2Binding.getRoot());

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

        event();

    }

    private void event() {
        main2Binding.btnBackupContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,BackupContactActivity.class);
                startActivity(intent);
            }
        });
    }
}