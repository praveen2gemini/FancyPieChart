package com.dpdlad.fancypiechartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dpdlad.fancypiechart.FancyPieChartView;

public class MainActivity extends AppCompatActivity {

    FancyPieChartView fancyPieChartViewOriginal, fancyPieChartViewOriginal2, fancyPieChartViewOriginal3, fancyPieChartViewOriginal4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fancyPieChartViewOriginal = findViewById(R.id.symmetricStarViewOriginal1);
        fancyPieChartViewOriginal2 = findViewById(R.id.symmetricStarViewOriginal2);
        fancyPieChartViewOriginal3 = findViewById(R.id.symmetricStarViewOriginal3);
        fancyPieChartViewOriginal4 = findViewById(R.id.symmetricStarViewOriginal4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadChart1();
        loadChart2();
        loadChart3();
        loadChart4();


    }

    private void loadChart1() {
        fancyPieChartViewOriginal
                .dataBuilder()
                .setMaximumDataRange(100)
                .addDataValue("Android", 100, Color.DKGRAY)
                .addDataValue("iOS", 80, Color.RED)
                .addDataValue("Java", 50, Color.GREEN)
                .addDataValue("DotNet", 96, Color.BLUE)
                .addDataValue("PHP", 30, Color.MAGENTA)
                .addDataValue("test1", 70, Color.YELLOW)
                .create();
    }

    private void loadChart2() {
        fancyPieChartViewOriginal2
                .dataBuilder()
                .setMaximumDataRange(100)
                .addDataValue("test1", 20, Color.YELLOW)
                .addDataValue("DotNet", 96, Color.BLUE)
                .addDataValue("Java", 50, Color.GREEN)
                .addDataValue("PHP", 86, Color.MAGENTA)
                .addDataValue("Android", 10, Color.DKGRAY)
                .addDataValue("iOS", 60, Color.RED)
                .create();
    }

    private void loadChart3() {
        fancyPieChartViewOriginal3
                .dataBuilder()
                .setMaximumDataRange(500)
                .addDataValue("Android", 100, Color.MAGENTA)
                .addDataValue("iOS", 495, Color.GREEN)
                .addDataValue("Java", 250, Color.RED)
                .addDataValue("Java", 150, Color.BLUE)
                .create();
    }

    private void loadChart4() {
        fancyPieChartViewOriginal4
                .dataBuilder()
                .setMaximumDataRange(1000)
                .addDataValue("Android", 100, Color.DKGRAY)
                .addDataValue("iOS", 800, Color.RED)
                .addDataValue("Java", 50, Color.GREEN)
                .addDataValue("DotNet", 250, Color.BLUE)
                .addDataValue("PHP", 900, Color.MAGENTA)
                .addDataValue("test1", 600, Color.YELLOW)
                .create();
    }
}
