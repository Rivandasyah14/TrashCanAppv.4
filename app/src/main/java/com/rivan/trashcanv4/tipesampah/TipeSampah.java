package com.rivan.trashcanv4.tipesampah;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.rivan.trashcanv4.R;

public class TipeSampah extends AppCompatActivity {

    private SectionsPagerAdapter mSectionPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipe_sampah);

        mSectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.vp_tipe_sampah);
        mViewPager.setAdapter(mSectionPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_tipe_sampah);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    public static class PlaceholderFragment extends Fragment{
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static final PlaceholderFragment newInstance(int sectionNumber){
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
            if (getArguments().getInt(ARG_SECTION_NUMBER)==1){
                View rootView = inflater.inflate(R.layout.fragment_tipe_plastik, container, false);
                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER)==2){
                View rootView = inflater.inflate(R.layout.fragment_tipe_kertas, container, false);
                return rootView;
            }
            return null;
        }
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }


}
