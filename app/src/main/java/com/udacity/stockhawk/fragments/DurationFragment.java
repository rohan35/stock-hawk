package com.udacity.stockhawk.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.udacity.stockhawk.R;

import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Intent.getIntent;

/**
 * Created by admin on 1/26/2017.
 */

public class DurationFragment extends Fragment {
    private static int tab_pos = 0;
    @BindView(R.id.chart)
    LineChart chart;

    private Unbinder unbinder;

    public DurationFragment() {
        //empty public constructor
    }

    public final static DurationFragment newInstance(int i) {
        DurationFragment durationFragment = new DurationFragment();


        Bundle args = new Bundle();
        args.putInt("position", i);

        durationFragment.setArguments(args);
        return durationFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.duration_fragment, container, false);
        unbinder = ButterKnife.bind(this, v);
        tab_pos=getArguments().getInt("position");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (tab_pos == 1) {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -3);
            long result = cal.getTimeInMillis();
            String date = dateFormat.format(cal.getTimeInMillis());


            getData(date);
            tab_pos=0;
        }
        else if(tab_pos==2)
        {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -6);
            String date = dateFormat.format(cal.getTimeInMillis());


            getData(date);
            tab_pos=0;
        }
        else if(tab_pos==3)
        {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -1);
            String date = dateFormat.format(cal.getTimeInMillis());


            getData(date);
            tab_pos=0;
        }

        else if(tab_pos==0)
        {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            String date = dateFormat.format(cal.getTimeInMillis());


            getData(date);

        }


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void getData(String result)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> s = new ArrayList<>();
        ArrayList<Entry> t = new ArrayList<>();
        List<String> history_list = new ArrayList<>();
        String history;

        Intent i = getActivity().getIntent();
        history = i.getStringExtra("history");
        String history_array[] = history.split("/");
        history_list = Arrays.asList(history_array);
        String split_history[];

        for (int k = 0; k < history_list.size(); k++) {
            split_history = history_list.get(k).split(":");

            try {
                if(sdf.parse(split_history[0]).before(sdf.parse(result)))
                {

                    break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


            t.add(new Entry(Float.parseFloat(split_history[1]), k));
            s.add(split_history[0]);

        }
        LineDataSet dataset = new LineDataSet(t, "months");
        LineData data = new LineData(s, dataset);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.animateX(1000);
        chart.animateY(1000);
        chart.animateXY(1000,1000);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);


        chart.setData(data);

    }

}