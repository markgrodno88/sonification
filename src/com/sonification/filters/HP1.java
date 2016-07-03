package com.sonification.filters;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.EnumsFilters.FilterName;

public class HP1 implements ImageFilter{
	private FilterName filterName;
	public HP1() {
		this.filterName = FilterName.HP1;
	}
	@Override
	public Mat filterMat(Mat originalImage) {
		Mat hp1 = new Mat();
		Mat kernel = new Mat(3, 3, CvType.CV_32FC1);
		float[] data = {0, -1,0, -1, 5, -1, 0, -1, 0};
		kernel.put(0, 0, data);
		Imgproc.filter2D(originalImage, hp1, -1, kernel);
		return hp1;
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
