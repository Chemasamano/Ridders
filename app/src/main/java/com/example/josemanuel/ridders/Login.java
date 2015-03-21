package com.example.josemanuel.ridders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Jose Manuel on 02/03/2015.
 */
public class Login extends Activity {
    private EditText usernameV;
    private EditText passwordV;

    @Override
    public void onCreate(Bundle saveIntanceState){
        super.onCreate(saveIntanceState);
        setContentView(R.layout.login_activity);

        //set up login form
        usernameV = (EditText) findViewById(R.id.username);
        passwordV = (EditText) findViewById(R.id.password);

        //set up submit button click
        findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate log in data
                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder("Por favoooor ....");
                if (isEmpty(usernameV)){
                    validationError = true;
                    validationErrorMessage.append("Ingrese el nombre de usuario");
                }
                if (isEmpty(passwordV)){
                    if (validationError){
                        validationErrorMessage.append(" e ");
                    }
                    validationError = true;
                    validationErrorMessage.append("Ingrese la Contraseña");
                }
                validationErrorMessage.append(".");

                if (validationError){
                    Toast.makeText(Login.this,validationErrorMessage.toString(),Toast.LENGTH_LONG).show();
                    return;
                }

                final ProgressDialog dlg= new ProgressDialog(Login.this);
                dlg.setTitle("Por Favor ... espere ...");
                dlg.setMessage("Ingresando ... Espere ...");
                dlg.show();

                //Call de Parse login method
                ParseUser.logInInBackground(usernameV.getText().toString(),passwordV.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        dlg.dismiss();
                        if (e!=null){
                            //show the error message
                            Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }else {
                            //Start an intent for the dispatch
                            Intent intent= new Intent(Login.this,Dispatch.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });

            }
        });

    }

    private boolean isEmpty(EditText etText){
        if (etText.getText().toString().trim().length() > 0){
            return false;
        }else{
            return true;
        }

    }
}
