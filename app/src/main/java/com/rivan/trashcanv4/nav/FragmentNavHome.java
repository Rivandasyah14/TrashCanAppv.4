package com.rivan.trashcanv4.nav;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rivan.trashcanv4.R;
import com.rivan.trashcanv4.informasi.Informasi;
import com.rivan.trashcanv4.tipesampah.TipeSampah;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNavHome extends Fragment {

    Button testTipe, testInform;

    public FragmentNavHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nav_home, container, false);


        testTipe = v.findViewById(R.id.test_tipe);
        testTipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TipeSampah.class));
            }
        });

        testInform= v.findViewById(R.id.test_informasi);
        testInform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Informasi.class));
            }
        });
        return v;
    }

}
