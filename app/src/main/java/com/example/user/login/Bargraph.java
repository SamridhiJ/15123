package com.example.user.login;
import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarData;

public class Bargraph extends AppCompatActivity {

    BarChart barchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barchart=(BarChart) findViewById(R.id.bargraph);


        ArrayList<BarEntry> barEntries=new ArrayList();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(88f,1));
        barEntries.add(new BarEntry(66f,2));
        barEntries.add(new BarEntry(12f,3));
        barEntries.add(new BarEntry(19f,4));

        BarDataSet barDataSet= new BarDataSet(barEntries,"Data");

        ArrayList<String> theDates= new ArrayList<>();
        theDates.add("Electricity");
        theDates.add("Sanitation");
        theDates.add("Ragging");
        theDates.add("Hostel");
        theDates.add("Others");



        BarData theData= new BarData(theDates,barDataSet);
        barchart.setData(theData);

        barchart.setTouchEnabled(true);
        barchart.setDragEnabled(true);
        barchart.setScaleEnabled(true);
    }
}
