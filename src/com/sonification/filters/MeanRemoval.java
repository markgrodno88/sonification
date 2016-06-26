package com.sonification.filters;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

public class MeanRemoval implements ImageFilter{
	Mat kernel;
	@Override
	public Mat createFiltr(Mat originalImage) {
		kernel = new Mat(3, 3, CvType.CV_32FC1);
		//create
		float[] data =
			{-8,-8,-8,
			 -8,64,-8,
			 -8,-8,-8}/*
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,
						-1,-1,-1,-1,-1,-1,-1,-1,-1,
						-1,-1,-1,-1,-1,-1,-1,-1,-1,
						-1,-1,-1,-1,-1,-1,-1,-1,-1,
						-1,-1,-1,-1,80,-1,-1,-1,-1, 
						-1,-1,-1,-1,-1,-1,-1,-1,-1,
						-1,-1,-1,-1,-1,-1,-1,-1,-1,
						-1,-1,-1,-1,-1,-1,-1,-1,-1,
						-1,-1,-1,-1,-1,-1,-1,-1,-1}*/;
		kernel.put(0, 0, data);
		Mat meenRemoval = new Mat();
		Imgproc.filter2D(originalImage, meenRemoval, -1, kernel);
		return meenRemoval;	
	}
	public String getName() {
		return this.getClass().getSimpleName();
	}
	@Override
	public double getDimension() {
		return kernel.rows();
	}

}
