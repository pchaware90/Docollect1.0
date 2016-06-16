package com.example.webmail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    Button btnSignIn;
    EditText textUsername, textPassword;
    LoginDBAdapter loginDBAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        try {
            loginDBAdapter = new LoginDBAdapter((this));
            loginDBAdapter = loginDBAdapter.open();
        }catch (Exception e){e.printStackTrace();}

        textUsername=(EditText)findViewById(R.id.editEmail);
        textPassword=(EditText)findViewById(R.id.editPassword);
        btnSignIn = (Button) findViewById(R.id.btnLogin);
            btnSignIn.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v) {
                        Toast.makeText(LoginActivity.this,"Login Successfull", Toast.LENGTH_LONG);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});
    }
}