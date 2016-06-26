package com.sonification.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Gray implements ImageFilter{

	@Override
	public Mat createFiltr(Mat originalImage) {
		Mat destination = new Mat();
		Imgproc.cvtColor(originalImage, destination, Imgproc.COLOR_RGB2GRAY);
		return destination;
	}
	public String getName() {
		return this.getClass().getSimpleName();
	}
	@Override
	public double getDimension() {
		// TODO Auto-generated method stub
		return 0;
	}
}
