package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chun_yuanmo.assignment111.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by chun-yuanmo on 2017/11/6.
 */

//source from https://www.youtube.com/watch?v=pi1tq-bp7uA

/**
 * This fragmet will show a simple bar graph of the evio from the user tidwall
 * The bar graph will shows commits times each week from past month of the repo
 */
public class Graph extends Fragment {
    View myView;
    BarChart repo_graph;
    @Nullable
    @Override
    /**
     * The fragment view it will displays when clikicng the corresponding name from the navigation drawer
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.graph_layout, container, false);
        repo_graph = (BarChart) myView.findViewById(R.id.repo_graph);

        //bar graph y-axis data
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 0));
        entries.add(new BarEntry(24f, 1));
        entries.add(new BarEntry(47f, 2));
        entries.add(new BarEntry(3f, 3));
        BarDataSet data_set = new BarDataSet(entries, "commit times");

        //String for x-axis
        ArrayList<String> weeks = new ArrayList<>();
        weeks.add("Week Oct 15");
        weeks.add("Week Oct 22");
        weeks.add("Week Oct 29");
        weeks.add("Week Nov 5");

        //Show the bar graph in the layout
        BarData data = new BarData(weeks, data_set);
        repo_graph.setData(data);
        repo_graph.setScaleEnabled(true);

        return myView;
    }
}

