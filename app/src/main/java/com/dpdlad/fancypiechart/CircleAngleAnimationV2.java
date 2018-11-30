package com.dpdlad.fancypiechart;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * This class used to apply animation on splitted arc drawing as progress bar
 *
 * @author Praveen Kumar on 30/11/18.
 */
public class CircleAngleAnimationV2 extends Animation {

    private FancyPieChartView starView;
    private int oldAngle;
    private int newAngle;

    private CircleAngleAnimationV2(FancyPieChartView starView) {
        this.starView = starView;
        this.oldAngle = starView.getSweepAngle();
        this.newAngle = starView.getMaxAngle();
    }

    static void startArcProgressAnimation(FancyPieChartView starView) {
        CircleAngleAnimationV2 animation = new CircleAngleAnimationV2(starView);
        animation.setDuration(3000);
        starView.startAnimation(animation);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        int angle = (int) (oldAngle + ((newAngle - oldAngle) * interpolatedTime));
        if (starView.getSweepAngle() < starView.getMaxAngle()) {
            starView.setSweepAngle(angle);
            starView.requestLayout();
        } else {
            cancel();
            Log.e("$$$$$$$$$", "applyTransformation is Cancelled");
        }
    }
}