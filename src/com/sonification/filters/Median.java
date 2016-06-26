package com.sonification.filters;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Median implements ImageFilter {

	@Override
	public Mat createFiltr(Mat originalImage) {
		Mat median=new Mat();
		int kernelDim=9;
		Imgproc.medianBlur(originalImage,median , kernelDim);
		return median;
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
