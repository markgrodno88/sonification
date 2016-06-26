package com.sonification.filters;

import org.opencv.core.Mat;

public class Original implements ImageFilter{

	@Override
	public Mat createFiltr(Mat originalImage) {
		
		return originalImage;
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
