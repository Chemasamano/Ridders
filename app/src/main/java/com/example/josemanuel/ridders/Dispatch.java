package com.example.josemanuel.ridders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by Jose Manuel on 02/03/2015.
 */
public class Dispatch extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Parse.initialize(this, "sOmaQXBLoo5kFgILRLGEkkKvAeyzdxLx03SJx8r4", "ZzWp9aWI1zF2FNvqTULdJVxBiddQUwnRVb8HvQ3V");

        //check if there is current user info
        if (ParseUser.getCurrentUser()!=null){
            //start an intent for the logged in activity
            startActivity(new Intent(this,MainActivity.class));
        } else {
            //start an intent for the logged out activity
            startActivity(new Intent(this,SignUpOrLogin.class));
        }
    }

}
