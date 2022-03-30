package com.enctyptednotepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eRegister;
    private CheckBox eRememberMe;

    boolean isValid = false;

    public Credentials credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.etLogin);
        eRegister = findViewById(R.id.tvRegister);
        eRememberMe = findViewById(R.id.cbrememberMe);

        credentials = new Credentials();

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        // check whether file sharedPreferences exists and takes cred from there
        if (sharedPreferences != null) {

            Map<String, ?> preferencesMap = sharedPreferences.getAll();

            if (preferencesMap.size() != 0) {
                credentials.loadCredentials(preferencesMap);
            }

            String savedUsername = sharedPreferences.getString("LastSavedUsername", "");
            String savedPassword = sharedPreferences.getString("LastSavedPassword", "");

            if (sharedPreferences.getBoolean("RememberMeCheckbox", false)) {
                eName.setText(savedUsername);
                ePassword.setText(savedPassword);
                eRememberMe.setChecked(true);
            }
        }

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Short", Toast.LENGTH_LONG).show();
                } else {
                    isValid = validate(inputName, inputPassword);

                    if (!isValid) {

                        Toast.makeText(MainActivity.this, "Wrong login or password", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();

                        sharedPreferencesEditor.putString("LastSavedUsername", inputName);
                        sharedPreferencesEditor.putString("LastSavedPassword", inputPassword);

                        sharedPreferencesEditor.putBoolean("RememberMeCheckbox", eRememberMe.isChecked());

                        sharedPreferencesEditor.apply();
                        // new activity

                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean validate(String name, String password) {
        return credentials.verifyCredentials(name, password);
    }
}