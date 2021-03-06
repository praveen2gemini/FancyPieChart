package com.dpdlad.fancypiechart;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
 * This view used to render the data in pie chart. each values are depends on maximum value of its target. It should be set without fail.
 *
 * @author Praveen Kumar on 30/11/18.
 */

public class FancyPieChartView extends View {

    //log tag
    private static final String TAG = FancyPieChartView.class.getSimpleName();
    private static final int DEFAULT_MAX_SWEEP_ANGLE = 75;
    private static final int DEFAULT_ARC_SPACE = 3; // Default spacious between arc is 3 degree
    private final RectF rect = new RectF();
    // Data for view
    private ArrayList<ChartInfo> chartDataValues;
    @IntRange(from = 0, to = DEFAULT_MAX_SWEEP_ANGLE)
    private int mRedSweepAngle = 0;
    private int maximumDataValue = 0;
    private Paint defaultArcPaint;

    public FancyPieChartView(Context context) {
        this(context, null);
    }

    public FancyPieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FancyPieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        Todo: Animation yet to be implemented. Coming soon
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
        return new DataBuilder();
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
        float fat = min / 7;
        Paint paint = getDefaultPaint(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeWidth(fat);
        paint.setFilterBitmap(true);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minimumSize = Math.min(widthMeasureSpec, heightMeasureSpec);
        if (minimumSize <= 0) {
            Resources res = getResources();
            minimumSize = res.getDimensionPixelSize(R.dimen.default_chart_height_width);
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(minimumSize, widthSize);
        } else {
            //Be whatever you want
            width = minimumSize;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(minimumSize, heightSize);
        } else {
            //Be whatever you want
            height = minimumSize;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.TRANSPARENT); // Background color

        float minSize = Math.min(getWidth(), getHeight());
        float fat = minSize / 7;
        float half = minSize / 2;
//        drawCrossOnView(canvas); // Just to inspect the view coordinates x,y

        drawSplitterArc(half, fat, canvas); // It draws the multiple arcs

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
    private void drawSplitterArc(float half, float fat, @NonNull Canvas canvas) {
        if (null == defaultArcPaint) {
            int mWidth = getWidth();
            int mHeight = getHeight();
            float mRadius = half - fat + 10;
            rect.set(mWidth / 2 - mRadius,
                    mHeight / 2 - mRadius,
                    mWidth / 2 + mRadius,
                    mHeight / 2 + mRadius);
            defaultArcPaint = outerCirclePaint(Color.DKGRAY);
            Log.e(TAG, "outerCirclePaint is NULL");
        }

        if (!chartDataValues.isEmpty()) {
            for (int dataIndex = 0; dataIndex < chartDataValues.size(); dataIndex++) {
                ChartInfo chartInfo = chartDataValues.get(dataIndex);
                defaultArcPaint.setColor(chartInfo.chartColor);
                Log.e(TAG, chartInfo.chartName + " Previous Angle: " + chartInfo.startAngle);
                Log.e(TAG, chartInfo.chartName + " CurrentAngle Angle: " + chartInfo.currentAngle);
                canvas.drawArc(rect, chartInfo.startAngle, chartInfo.endAngle, false, defaultArcPaint);
            }
        }
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
        private float startAngle, currentAngle, endAngle;
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
            chartDataValues = new ArrayList<>();
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

            float totalScore = 0.0f;
            for (ChartInfo chartInfo : chartDataValues) {
                totalScore += chartInfo.chartValue;
            }
            for (int dataIndex = 0; dataIndex < chartDataValues.size(); dataIndex++) {
                ChartInfo chartInfo = chartDataValues.get(dataIndex);
                float singleValue = chartInfo.chartValue;
                chartInfo.currentAngle = (singleValue / totalScore) * 360;
                if (dataIndex > 0) {
                    chartInfo.startAngle = chartDataValues.get(dataIndex - 1).startAngle + chartDataValues.get(dataIndex - 1).currentAngle;
                }
                chartInfo.endAngle = chartInfo.currentAngle - DEFAULT_ARC_SPACE;
            }

            loadPieChartView();
        }
    }
}
