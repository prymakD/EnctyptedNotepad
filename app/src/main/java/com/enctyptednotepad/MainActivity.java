package com.enctyptednotepad;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;

    private String Username = "Admin";
    private String Password = "1234";

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.etLogin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Short", Toast.LENGTH_LONG).show();
                } else  {
                    isValid = validate(inputName, inputPassword);

                    if (!isValid) {
                        Toast.makeText(MainActivity.this, "Wrong login or password", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();

                        // new activity

                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean validate (String name, String password) {

        if (name.equals(Username) && password.equals(Password)) {
            return true;
        }

        return false;
    }
}