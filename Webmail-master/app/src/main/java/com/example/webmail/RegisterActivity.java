package com.example.webmail;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    EditText textfullname,textemail,textpassword;

    Button btnSubmit ;
    LoginDBAdapter loginDatabaseAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        btnSubmit = (Button) findViewById(R.id.btnRegister);
   try{
        loginDatabaseAdapter=new LoginDBAdapter(this);
       loginDatabaseAdapter=loginDatabaseAdapter.open();
   }catch (Exception e){
       e.printStackTrace();
   }
        textfullname=(EditText)findViewById(R.id.reg_fullname);
        textpassword=(EditText)findViewById(R.id.reg_password);
        textemail=(EditText)findViewById(R.id.reg_email);

        btnSubmit.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                String fullname = textfullname.getText().toString();
                String email = textemail.getText().toString();
                String password = textpassword.getText().toString();

                if(fullname.equals("")||email.equals("")||password.equals(""))
                    Toast.makeText(RegisterActivity.this,"Fields are empty", Toast.LENGTH_LONG);
                else
                {
                    loginDatabaseAdapter.insertEntry(fullname,email,password);

                }

            }
        });
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
    }
}