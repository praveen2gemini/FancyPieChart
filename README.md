
This is simple application used to how to render Pie Chart using Canvas and Paint. It would render the chart according data has been given. Each values are compared with maximum value. The It will be render as degree in PieChart.
setMaximumValue is mandatory to provide without fail.

This is open source. If anyone wants to customise it. You’re welcome!!!!



#### maven metadata.xml
```
<metadata>
<groupId>com.github.praveen2gemini</groupId>
<artifactId>FancyPieChart</artifactId>
<version>0.0.1-SNAPSHOT</version>
<versioning>
<snapshot>
<timestamp>20181130.162844</timestamp>
<buildNumber>1</buildNumber>
</snapshot>
<lastUpdated>20181130162844</lastUpdated>
</versioning>
</metadata>
```



#### Add following line to app/build.gradle
```
implementation 'com.github.praveen2gemini:FancyPieChart:0.0.1-SNAPSHOT@aar'
```

```
  <com.dpdlad.fancypiechart.FancyPieChartView
             android:id="@+id/fancy_pie_chart_id"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content" />
```


#### MainActivity.java
```
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FancyPieChartView fancyPieChartView= findViewById(R.id.fancy_pie_chart_id);
        fancyPieChartView
                        .dataBuilder()
                        .setMaximumDataRange(100) // MUST SET max value of each data
                        .addDataValue("Android", 100, Color.DKGRAY)
                        .addDataValue("iOS", 80, Color.RED)
                        .addDataValue("Java", 50, Color.GREEN)
                        .addDataValue("DotNet", 96, Color.BLUE)
                        .addDataValue("PHP", 30, Color.MAGENTA)
                        .addDataValue("test1", 70, Color.YELLOW)
                        .create();
    }
```

# Sample Screenshots:

![Overview](screenshots/Screenshot_2018.png)![Overview](screenshots/Screenshot_multiples.png)

