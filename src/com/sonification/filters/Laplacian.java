package com.sonification.filters;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Laplacian implements ImageFilter{

	@Override
	public Mat createFiltr(Mat originalImage) {
		Mat laplacian = new Mat();
		Imgproc.Laplacian(originalImage, laplacian, CvType.CV_8U, 3, 1, 0);
		return laplacian;
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
