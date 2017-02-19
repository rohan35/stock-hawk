package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.fragments.DurationFragment;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 1/24/2017.
 */

public class StockDetails extends AppCompatActivity {
    //
    @BindView(R.id.duration)
     ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    static int i=0;
    DurationAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_details);
        ButterKnife.bind(this);



        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(0);



        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
    adapter=new DurationAdapter(getSupportFragmentManager());
    adapter.addFragment(DurationFragment.newInstance(0),"1M");
    adapter.addFragment(DurationFragment.newInstance(1),"3M");
    adapter.addFragment(DurationFragment.newInstance(2),"6M");
    adapter.addFragment(DurationFragment.newInstance(3),"1Y");
        viewPager.setAdapter(adapter);
    }


}
