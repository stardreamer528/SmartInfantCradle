package com.paneerbuttermasala.studios.smartinfantcradle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button continuenext = findViewById(R.id.continue_button);

        continuenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHomePage();
            }
        });

    }

    public void gotoHomePage()
    {
        startActivity(new Intent(getApplicationContext(),Homepage.class));
    }
}