package com.sonification.filters;

import org.opencv.core.Mat;

import com.sonification.filters.EnumsFilters.FilterName;

public interface ImageFilter{
	public Mat filterMat(Mat originalImage);
	public String getName();
	public double getDimension();
	public FilterName getFilterName();
}
