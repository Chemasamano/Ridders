package com.example.josemanuel.ridders;


import android.app.Application;
import com.parse.Parse;


/**
 * Created by Jose Manuel on 02/03/2015.
 */
public class Sample extends Application {

    public void onCreate(){
        Parse.initialize(this, "sOmaQXBLoo5kFgILRLGEkkKvAeyzdxLx03SJx8r4","ZzWp9aWI1zF2FNvqTULdJVxBiddQUwnRVb8HvQ3V");
    }
}
