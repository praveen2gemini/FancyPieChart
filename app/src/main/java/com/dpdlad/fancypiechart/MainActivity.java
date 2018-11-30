package com.dpdlad.fancypiechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    FancyPieChartView fancyPieChartViewOriginal, fancyPieChartViewOriginal2
            , fancyPieChartViewOriginal3
            , fancyPieChartViewOriginal4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fancyPieChartViewOriginal = findViewById(R.id.symmetricStarViewOriginal);
        fancyPieChartViewOriginal2 = findViewById(R.id.symmetricStarViewOriginal2);
        fancyPieChartViewOriginal3 = findViewById(R.id.symmetricStarViewOriginal3);
        fancyPieChartViewOriginal4 = findViewById(R.id.symmetricStarViewOriginal4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadChartData(fancyPieChartViewOriginal, fancyPieChartViewOriginal2
                , fancyPieChartViewOriginal3, fancyPieChartViewOriginal4);


    }

    private void loadChartData(FancyPieChartView... fancyPieChartViewOriginals) {
        for (FancyPieChartView pieChartView : fancyPieChartViewOriginals) {
            if (null == pieChartView) continue;
            pieChartView
                    .dataBuilder()
                    .setMaximumDataRange(100)
                    .addDataValue("Android", 100, Color.DKGRAY)
                    .addDataValue("iOS", 80, Color.RED)
                    .addDataValue("Java", 50, Color.GREEN)
                    .addDataValue("DotNet", 96, Color.BLUE)
                    .addDataValue("PHP", 30, Color.MAGENTA)
                    .addDataValue("test1", 70, Color.YELLOW)
//                .addDataValue("test2", 27, Color.CYAN)
//                .addDataValue("test3", 46, Color.LTGRAY)
//                .addDataValue("test4", 46, Color.BLACK)
                    .create();
        }

    }
}
