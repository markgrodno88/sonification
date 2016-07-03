package com.sonification.filters;

import org.opencv.core.Mat;

import com.sonification.filters.EnumsFilters.FilterName;

public class Original implements ImageFilter{
	private FilterName filterName;
	public Original() {
		this.filterName = FilterName.ORIGINAL;
	}
	@Override
	public Mat filterMat(Mat originalImage) {
		
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
	@Override
	public FilterName getFilterName() {
		return filterName;
	}
}
