package com.rivan.trashcanv4.loginregis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rivan.trashcanv4.MainActivity;
import com.rivan.trashcanv4.R;
import com.rivan.trashcanv4.tipesampah.TipeSampah;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tnRegis = findViewById(R.id.tn_regis);
        tnRegis.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tn_regis:
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);
        }
    }
}
