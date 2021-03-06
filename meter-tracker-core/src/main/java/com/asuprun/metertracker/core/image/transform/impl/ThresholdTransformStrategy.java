package com.asuprun.metertracker.core.image.transform.impl;

import com.asuprun.metertracker.core.image.transform.AbstractTransformStrategy;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by asuprun on 1/17/15.
 */
public class ThresholdTransformStrategy extends AbstractTransformStrategy {

    private double maxValue;

    public ThresholdTransformStrategy(double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public Mat execute(Mat source) {
        Mat target = source.clone();
        Imgproc.threshold(source, target, 0, maxValue, Imgproc.THRESH_OTSU);
        return target;
    }
}
