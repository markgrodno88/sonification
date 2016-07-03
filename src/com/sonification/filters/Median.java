package com.sonification.filters;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.EnumsFilters.FilterName;

public class Median implements ImageFilter {
	private FilterName filterName;
	public Median() {
		this.filterName = FilterName.MEDIAN;
	}
	@Override
	public Mat filterMat(Mat originalImage) {
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
	@Override
	public FilterName getFilterName() {
		return filterName;
	}
}
