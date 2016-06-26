package com.sonification.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Average implements ImageFilter{
	Size size;
	@Override
	public Mat createFiltr(Mat originalImage) {
		Mat average=new Mat();
		size=new Size(9,9);
		Imgproc.blur(originalImage, average, size);
		return average;
		
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

	public double getDimension() {
		return size.height;
	}	
}
