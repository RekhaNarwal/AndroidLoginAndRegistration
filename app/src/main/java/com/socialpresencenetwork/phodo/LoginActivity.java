package com.socialpresencenetwork.phodo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);

        //get our fields and buttons
        EditText userField = (EditText) findViewById(R.id.userField);
        EditText pwField = (EditText) findViewById(R.id.pwField);
        Button loginBtn = (Button) findViewById(R.id.btnLogin);
        Button registerBtn = (Button) findViewById(R.id.btnRegister);

        //Load our font
        Typeface openSansFont = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        //set our fields and buttons to our font
        userField.setTypeface(openSansFont);
        pwField.setTypeface(openSansFont);
        loginBtn.setTypeface(openSansFont);
        registerBtn.setTypeface(openSansFont);

        //listen for register button touched
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}