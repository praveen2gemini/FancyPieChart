package com.dpdlad.fancypiechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    FancyPieChartView fancyPieChartViewOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fancyPieChartViewOriginal = findViewById(R.id.symmetricStarViewOriginal);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        fancyPieChartViewOriginal.loadPieChartView();
        fancyPieChartViewOriginal
                .dataBuilder()
                .setMaximumDataRange(100)
                .addDataValue("Android", 50, Color.DKGRAY)
                .addDataValue("iOS", 50, Color.RED)
//                .addDataValue("Java", 50, Color.GREEN)
//                .addDataValue("DotNet", 25, Color.BLUE)
//                .addDataValue("PHP", 50, Color.MAGENTA)


//                .addDataValue("Android", 10, Color.DKGRAY)
//                .addDataValue("iOS", 20, Color.RED)
//                .addDataValue("Java", 40, Color.GREEN)
//                .addDataValue("DotNet", 70, Color.BLUE)
//                .addDataValue("PHP", 80, Color.MAGENTA)
                .create();

    }
}
