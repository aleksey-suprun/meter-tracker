package com.asuprun.metertracker.core.image.transform.impl;

import com.asuprun.metertracker.core.image.transform.TransformStrategy;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by asuprun on 1/9/15.
 */
public class HoughTransformStrategy implements TransformStrategy {

    private int threshold;

    public HoughTransformStrategy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public Mat transform(Mat source) {
        Imgproc.HoughLines(source, source, 1, Math.PI / 180, threshold);
        return source;
    }
}
