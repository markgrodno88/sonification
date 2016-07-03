package com.sonification.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.EnumsFilters.FilterName;

public class Gray implements ImageFilter{
	private FilterName filterName;
	public Gray() {
		this.filterName = FilterName.GRAY;
	}
	@Override
	public Mat filterMat(Mat originalImage) {
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
	@Override
	public FilterName getFilterName() {
		return filterName;
	}
}
