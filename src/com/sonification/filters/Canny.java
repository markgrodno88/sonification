package com.sonification.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.EnumsFilters.FilterName;

public class Canny implements ImageFilter{
	private FilterName filterName;
	public Canny() {
		this.filterName = FilterName.CANNY;
	}
	@Override
	public Mat filterMat(Mat originalImage) {
		Mat gray = new Mat();
		int countOfChannel = originalImage.channels();
		if(countOfChannel == 3){
			Imgproc.cvtColor(originalImage, gray, Imgproc.COLOR_RGB2GRAY);
		}else if(countOfChannel == 1){
			gray = originalImage;
		}
		Mat edgeImage=new Mat();
		Imgproc.Canny(gray, edgeImage, 100, 200);
		return edgeImage;
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
