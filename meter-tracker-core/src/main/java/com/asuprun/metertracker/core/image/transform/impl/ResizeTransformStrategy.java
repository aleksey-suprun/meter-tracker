package com.asuprun.metertracker.core.image.transform.impl;

import com.asuprun.metertracker.core.image.transform.AbstractTransformStrategy;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ResizeTransformStrategy extends AbstractTransformStrategy {

    private final int width;
    private final int height;

    public ResizeTransformStrategy(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Mat execute(Mat source) {
        if (source.width() != width || source.height() != height) {
            Mat target = source.clone();
            Imgproc.resize(source, target, new Size(width, height));
            return target;
        }
        return source;
    }
}
