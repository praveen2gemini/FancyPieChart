package com.dpdlad.fancypiechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * @author Praveen Kumar on 16/10/17.
 */

public class FancyPieChartView extends View {

    //log tag
    private static final String TAG = FancyPieChartView.class.getSimpleName();
    private static final int DEFAULT_MAX_SWEEP_ANGLE = 75;
    private final Path path = new Path();
    private final RectF rect = new RectF();
    // Data for view
    private final ArrayList<ChartInfo> chartDataValues = new ArrayList<>();
    @IntRange(from = 0, to = DEFAULT_MAX_SWEEP_ANGLE)
    private int mRedSweepAngle = 0;
    private int maximumDataValue = 0;
    private Paint defaultArcPaint;
    private DataBuilder dataBuilder;

    public FancyPieChartView(Context context) {
        this(context, null);
    }

    public FancyPieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FancyPieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dataBuilder = new DataBuilder();
//        if (!isInEditMode()) {
//            CircleAngleAnimationV2.startArcProgressAnimation(this);
//        }
    }

    public int getMaximumDataValue() {
        return maximumDataValue;
    }

    public void setMaximumDataValue(int maximumDataValue) {
        this.maximumDataValue = maximumDataValue;
    }

    public DataBuilder dataBuilder() {
        return dataBuilder;
    }

    public void loadPieChartView() {
        setSweepAngle(0);
        requestLayout();
    }


    /**
     * It provides Default {@link Paint} object.
     *
     * @param style the style to be set as STROKE or FILL_AND_STROKE
     * @return loaded {@link Paint} instance.
     */
    @NonNull
    private Paint getDefaultPaint(Paint.Style style) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(style);
        return paint;
    }


    /**
     * It provides {@link Paint} instance for draw an arc with progressive feature.
     *
     * @param color - optional to set according to progressing level.
     * @return - It returns the {@link Paint} instance to indicating progressing level to user by given value.
     */
    private Paint outerCirclePaint(@ColorInt int color) {
        int mWidth = getWidth();
        int mHeight = getHeight();
        float min = Math.min(mWidth, mHeight);
        float fat = min / 10;
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(fat);
        paint.setFilterBitmap(true);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.TRANSPARENT); // Background color

        float minSize = Math.min(getWidth(), getHeight());
        float fat = minSize / 7;
        float half = minSize / 2;
//        drawCrossOnView(canvas);


        drawSplittedArc(half, fat, canvas); // It draws the multiple arcs

        super.onDraw(canvas);

    }

    private void drawCrossOnView(Canvas canvas) {
        Paint paint = getDefaultPaint(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        canvas.drawLine(getWidth() * 0.25f, 0, (getWidth() * 0.25f), getHeight(), paint);
        canvas.drawLine(0, getWidth() * 0.25f, getHeight(), (getWidth() * 0.25f), paint);

        canvas.drawLine(getWidth() * 0.5f, 0, (getWidth() * 0.5f), getHeight(), paint);
        canvas.drawLine(0, getWidth() * 0.5f, getHeight(), (getWidth() * 0.5f), paint);

        canvas.drawLine(getWidth() * 0.75f, 0, (getWidth() * 0.75f), getHeight(), paint);
        canvas.drawLine(0, getWidth() * 0.75f, getHeight(), (getWidth() * 0.75f), paint);
    }

    /**
     * It will draw ultiple arc makes a splitted Circle shape also gives look like Progress bar
     *
     * @param half
     * @param fat
     * @param canvas
     */
    private void drawSplittedArc(float half, float fat, @NonNull Canvas canvas) {
        if (null == defaultArcPaint) {
            int mWidth = getWidth();
            int mHeight = getHeight();
            float mRadius = half - fat + 10;
            rect.set(mWidth / 2 - mRadius,
                    mHeight / 2 - mRadius,
                    mWidth / 2 + mRadius,
                    mHeight / 2 + mRadius);
            defaultArcPaint = outerCirclePaint(Color.DKGRAY);
            Log.e("$$$$$$$$$", "outerCirclePaint is NULL");
        }

        if (!chartDataValues.isEmpty()) {
//            Log.e("@@@@", "totalDataValue: " + getMaximumDataValue());
            float startAngle = 360 / chartDataValues.size();
            float incrementValue = 0;
            for (int dataIndex = 0; dataIndex < chartDataValues.size(); dataIndex++) {
                ChartInfo chartInfo = chartDataValues.get(dataIndex);
                defaultArcPaint.setColor(chartInfo.chartColor);
//                float singleValue = chartInfo.chartValue;
//                Log.e("@@@@", "singleValue: " + singleValue);
//                float drawAngle = chartInfo.currentAngle;
//                Log.e("@@@@", "drawAngle: " + drawAngle);
                Log.e("@@@@", chartInfo.chartName + " Previous Angle: " + chartInfo.previousAngle);
                Log.e("@@@@", chartInfo.chartName + " CurrentAngle Angle: " + chartInfo.currentAngle);
                if (dataIndex != 0) {
                    incrementValue = incrementValue + startAngle;
//                    canvas.drawArc(rect, chartInfo.previousAngle, chartInfo.currentAngle, false, defaultArcPaint);
                }
                Log.e("@@@@", "IncrementValue : " + incrementValue);
                canvas.drawArc(rect, chartInfo.previousAngle, chartInfo.currentAngle, false, defaultArcPaint);
            }
        }


//        canvas.drawArc(rect, 0, mRedSweepAngle, false, defaultArcPaint);
//        canvas.drawArc(rect, 90, mRedSweepAngle, false, arcPaintGreen);
//        canvas.drawArc(rect, 180, mRedSweepAngle, false, arcPaintRed);
//        canvas.drawArc(rect, 270, mRedSweepAngle, false, arcPaintBlue);
    }

    @IntRange(from = 0, to = DEFAULT_MAX_SWEEP_ANGLE)
    public int getSweepAngle() {
        return mRedSweepAngle;
    }

    public void setSweepAngle(int mRedSweepAngle) {
        this.mRedSweepAngle = mRedSweepAngle;
    }

    public int getMaxAngle() {
        return DEFAULT_MAX_SWEEP_ANGLE;
    }

    static class ChartInfo {

        private String chartName;
        private int chartValue;
        private float previousAngle, currentAngle;
        private @ColorInt
        int chartColor;

        static ChartInfo createChartInfo(String chartName, int data, @ColorInt int arcColor) {
            ChartInfo chartInfo = new ChartInfo();
            chartInfo.chartName = chartName;
            chartInfo.chartValue = data;
            chartInfo.chartColor = arcColor;
            return chartInfo;
        }
    }

    public class DataBuilder {

        private DataBuilder() {

        }

        public DataBuilder addDataValue(String chartName, int data, @ColorInt int arcColor) {
            chartDataValues.add(ChartInfo.createChartInfo(chartName, data, arcColor));
            return this;
        }

        public DataBuilder setMaximumDataRange(int maximumDataValue) {
            setMaximumDataValue(maximumDataValue);
            return this;
        }

        public void create() {
            if (getMaximumDataValue() <= 0) {
                throw new InvalidParameterException("Invalid maximumDataValue!, It can not be Zero or Negative!");
            }
            int size = chartDataValues.size();
            setMaximumDataValue(maximumDataValue * size);

            float logAngle = 0.0f;
//            float maxValue = (getMaximumDataValue() / size);
            for (ChartInfo chartInfo : chartDataValues) {
                float singleValue = chartInfo.chartValue;
//                Log.e("@@@@", "maxValue: " + getMaximumDataValue());
                float drawAngle = (singleValue / getMaximumDataValue()) * 360;
                Log.e("@@@@", "drawAngle: " + drawAngle);
                chartInfo.currentAngle = drawAngle;
                logAngle = logAngle + drawAngle;
            }
//            logAngle = (logAngle / getMaximumDataValue()) * 360;
            Log.e("@@@@", "getMaximumDataValue: " + getMaximumDataValue());
            Log.e("@@@@", "logAngle Angle: " + logAngle);
            float diffAngle = (360 - logAngle) / size;
            Log.e("@@@@", "diffAngle Angle: " + diffAngle);
            for (int dataIndex = 0; dataIndex < chartDataValues.size(); dataIndex++) {
                ChartInfo singleChartInfo = chartDataValues.get(dataIndex);
                singleChartInfo.currentAngle = singleChartInfo.currentAngle + diffAngle;
                if (dataIndex > 0) {
                    singleChartInfo.previousAngle = chartDataValues.get(dataIndex - 1).currentAngle;
                }
            }
            loadPieChartView();
        }
    }
}
