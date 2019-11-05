package com.rivan.trashcanv4.loginregis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rivan.trashcanv4.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tnLogin = findViewById(R.id.tn_login);

        tnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tn_login :
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
        }
    }
}
