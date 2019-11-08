package com.rivan.trashcanv4.tipesampah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rivan.trashcanv4.R;

public class DetailTipeSampah extends AppCompatActivity {
    TextView titleHal, nameLain, description, tips;
    ImageView imgOne, imgTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tipe_sampah);

        titleHal = findViewById(R.id.tipe_judul);
        imgOne = findViewById(R.id.tipe_img);
        imgTwo = findViewById(R.id.tipe_imgt);
        nameLain = findViewById(R.id.tipe_namalain);
        description = findViewById(R.id.tipe_deskripsi);
        tips = findViewById(R.id.tipe_tips);

    }
}
