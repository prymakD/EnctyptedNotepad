package com.enctyptednotepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName;
    private EditText eRegPassword;
    private Button eRegister;

    public static Credentials credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegister);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferenceEditor = sharedPreferences.edit();


        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String regUsername = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();

                if (validate(regUsername, regPassword)) {
                    credentials = new Credentials(regUsername, regPassword);

                    /* Store the credentials*/
                    sharedPreferenceEditor.putString("Username", regUsername);
                    sharedPreferenceEditor.putString("Password", regPassword);

                    //Commits the changes and adds them to the file
                    sharedPreferenceEditor.apply();

                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    Toast.makeText(RegistrationActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private boolean validate(String username, String password) {

        //Password is not less than 8 char
        if(username.isEmpty() || password.length() < 4) {
            Toast.makeText(this, "Password should be at least 4 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}