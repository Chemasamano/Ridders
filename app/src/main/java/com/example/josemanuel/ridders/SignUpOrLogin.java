package com.example.josemanuel.ridders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jose Manuel on 02/03/2015.
 */
public class SignUpOrLogin extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_or_login);

        //log in button click

        ((Button)findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start an intent of the log in activity
                startActivity(new Intent(SignUpOrLogin.this,Login.class));
            }
        });

        //sign up button click
        ((Button)findViewById(R.id.signup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start an intent for the sign up activity
                startActivity(new Intent(SignUpOrLogin.this,SignUp.class));
            }
        });
    }
}
