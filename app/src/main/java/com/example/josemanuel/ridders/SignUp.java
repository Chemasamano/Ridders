package com.example.josemanuel.ridders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Jose Manuel on 02/03/2015.
 */
public class SignUp extends Activity {
    private EditText usernameV;
    private EditText passwordV;
    private EditText passwordAgainV;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.signup_activity);

        //set up the signup form
        usernameV = (EditText) findViewById(R.id.username);
        passwordV = (EditText) findViewById(R.id.password);
        passwordAgainV = (EditText) findViewById(R.id.passwordAgain);

        //set up the submit button click
        findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate de signup data
                boolean validationError=false;
                StringBuilder validationErrorMessage=
                        new StringBuilder("Por Favooor ...");
                if (isEmpty(usernameV)){
                    validationError=true;
                    validationErrorMessage.append("Ingrese el nombre de Usuario");

                }
                if (isEmpty(passwordV)){
                    if (validationError){
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError=true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
                }
                if (!isMatching(passwordV,passwordAgainV)){
                    if (validationError){
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError=true;
                    validationErrorMessage.append(getResources().getString(R.string.error_mismatched_passwords));
                }

                validationErrorMessage.append(getResources().getString(R.string.error_end));

                //if there is a validation error, display the error
                if (validationError){
                    Toast.makeText(SignUp.this, validationErrorMessage.toString(),Toast.LENGTH_LONG).show();
                    return;
                }

                //set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(SignUp.this);
                dlg.setTitle("Por favor espere ...");
                dlg.setMessage("Accesando ... espere ...");
                dlg.show();

                //set up a Parse User
                ParseUser user= new ParseUser();
                user.setUsername(usernameV.getText().toString());
                user.setPassword(passwordV.getText().toString());

                //call the Parse signing method
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null){
                            //show the error message
                            Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }else {
                            //Start an intent for the dispatch activity
                            Intent intent=new Intent(SignUp.this,Dispatch.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });

            }
        });

    }

    private boolean isEmpty(EditText etText){
        if (etText.getText().toString().trim().length()>0){
            return false;
        }else {
            return true;
        }
    }

    private boolean isMatching(EditText etText1, EditText etText2){
        if (etText1.getText().toString().equals(etText2.getText().toString())){
            return true;
        }else {
            return false;
        }
    }

}
