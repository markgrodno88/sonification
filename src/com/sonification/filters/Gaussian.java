package com.sonification.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.EnumsFilters.FilterName;

public class Gaussian implements ImageFilter{
	private int stdX, stdY;
	private Size size = new Size(9,9);
	private FilterName filterName;
	public Gaussian() {
		this.filterName = FilterName.GAUSSIAN;
	}
	@Override
	public Mat filterMat(Mat originalImage) {
		Mat gaussian=new Mat();
		Imgproc.GaussianBlur(originalImage, gaussian, size, stdX, stdY);
		return gaussian;
	}	
	
	public double getDimension() throws NullPointerException{
		return size.height;
	}
	public void setParametersOfFilter(int stdX, int stdY){
		this.stdX = stdX;
		this.stdY = stdY;
	}
	public String getName() {
		return this.getClass().getSimpleName();
	}
	@Override
	public FilterName getFilterName() {
		return filterName;
	}
}
