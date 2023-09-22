package com.arifdwi.simpleloginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView namaText, emailText;
    Button buttonLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CALL FIREBASE AUTHENTICATION
        mAuth = FirebaseAuth.getInstance();

        namaText = findViewById(R.id.namaText);
        emailText = findViewById(R.id.emailText);
        buttonLogout = findViewById(R.id.logoutButton);

        // GET USER DATA FROM LOGIN ACCOUNT
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String namaLengkap = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            namaText.setText("Name : " + namaLengkap);
            emailText.setText("Email : " + email);
        }else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        // WHEN BUTTON LOGOUT CLICKED
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

    }
    private void logoutUser() {
        mAuth.signOut();

        // GO TO LOGINACVITY
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Tutup MainActivity agar tidak dapat dikembali lagi
    }
}