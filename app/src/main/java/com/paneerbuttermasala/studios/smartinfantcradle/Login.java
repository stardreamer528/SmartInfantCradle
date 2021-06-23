package com.paneerbuttermasala.studios.smartinfantcradle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button_signin = findViewById(R.id.signIn_button);
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSignUp();
            }
        });
    }

    void gotoSignUp()
    {
       Intent intent = new Intent(getApplicationContext(),SignUp.class);
       startActivity(intent);
    }
}