package com.socialpresencenetwork.phodo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

    EditText userField;
    EditText pwField;
    ProgressBar progressSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);

        //get our fields and buttons
        userField = (EditText) findViewById(R.id.userField);
        pwField = (EditText) findViewById(R.id.pwField);
        Button loginBtn = (Button) findViewById(R.id.btnLogin);
        Button registerBtn = (Button) findViewById(R.id.btnRegister);
        progressSpinner = (ProgressBar) findViewById(R.id.spinnerProgress);

        // Hide progress spinner
        progressSpinner.setVisibility(View.GONE);

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

        //listen for login button touched
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseLogin();
            }
        });
    }

    private void parseLogin() {
        progressSpinner.setVisibility(View.VISIBLE);

        ParseUser.logInInBackground(userField.getText().toString(), pwField.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Login success
                    progressSpinner.setVisibility(View.GONE);
                    Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(i);
                } else {
                    // Login failed
                    progressSpinner.setVisibility(View.GONE);
                    showAlert("Login failed");
                }
            }
        });
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setCancelable(false);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert1 = builder1.create();
        alert1.show();
    }
}