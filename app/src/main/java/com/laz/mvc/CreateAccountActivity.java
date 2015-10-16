package com.laz.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity {

    EditText etNewUsername, etNewPassword1, etNewPassword2;
    Button btnCreate;
    String sUser, sPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        etNewUsername = (EditText) findViewById(R.id.etNewUsername);
        etNewPassword1 = (EditText) findViewById(R.id.etNewPassword1);
        etNewPassword2 = (EditText) findViewById(R.id.etNewPassword2);
        btnCreate = (Button) findViewById(R.id.btnCreate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void create(View view) {
        sUser = etNewUsername.getText().toString();
        if (etNewPassword1.equals(etNewPassword2)) {
            sPass = etNewPassword1.toString();
        }

        System.out.println("Username: " + sUser);
        System.out.println("Password: " + sPass);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
    }
}
