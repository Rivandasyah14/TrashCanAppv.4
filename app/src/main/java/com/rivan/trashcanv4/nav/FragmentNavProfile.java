package com.rivan.trashcanv4.nav;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rivan.trashcanv4.R;
import com.rivan.trashcanv4.loginregis.LoginActivity;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNavProfile extends Fragment implements View.OnClickListener {
    FirebaseAuth mAuth;
    Button tnLogout;
    ImageView imageView;
    TextView tUser, tTransaction, tPhone, tEmail, tAlamat;

    public FragmentNavProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nav_profile, container, false);

        imageView = v.findViewById(R.id.prov_iv);
        tAlamat = v.findViewById(R.id.prov_address);
        tUser = v.findViewById(R.id.prov_user);
        tTransaction = v.findViewById(R.id.prov_transaksi);
        tPhone = v.findViewById(R.id.prov_phone);
        tEmail = v.findViewById(R.id.prov_email);
        tnLogout = v.findViewById(R.id.tnlogout);
        tnLogout.setOnClickListener(this);

        FirebaseUser user = mAuth.getCurrentUser();
        Glide.with(this).load(user.getPhotoUrl()).into(imageView);
        tUser.setText(user.getDisplayName());
        tUser.setText(user.getEmail());
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tnlogout:
                mAuth.getInstance().signOut();
                break;
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (mAuth.getCurrentUser() == null){
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }
//    }
}
