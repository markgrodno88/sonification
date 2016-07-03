package com.sonification.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.EnumsFilters.FilterName;


public class Average implements ImageFilter{
	private Size size;
	private FilterName filterName;
	
	public Average() {
		this.filterName = FilterName.AVERAGE;
	}
	@Override
	public Mat filterMat(Mat originalImage) {
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
	@Override
	public FilterName getFilterName() {
		return filterName;
	}
}
