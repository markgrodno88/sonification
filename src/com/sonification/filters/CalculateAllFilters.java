package com.sonification.filters;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;

import com.sonification.parameters.OutputInformations;

public class CalculateAllFilters {
	OutputInformations outputInformations;
	public List<ImageFilter> getListOfFilters(){
		List<ImageFilter> listOfFilters = new ArrayList<>(10);
		//low pass filters
		listOfFilters.add(new Original());
		listOfFilters.add(new Average());
		listOfFilters.add(new Median());
		listOfFilters.add(new Gaussian());
		//height pass filters
		listOfFilters.add(new MeanRemoval());
		listOfFilters.add(new HP1());
		//band pass filters
		listOfFilters.add(new Canny());
		listOfFilters.add(new Laplacian());
		listOfFilters.add(new Sobel());

		//other
		listOfFilters.add(new Gray());
		return listOfFilters;
	}
	public List<Mat> generateListOfFilteredImages(Mat originalImage){
		
		outputInformations = new OutputInformations();
		List<ImageFilter> listOfFilters = getListOfFilters();
		List<Mat> listOfFilteredImages = new ArrayList<>(11);
		for(ImageFilter filter : listOfFilters){
			listOfFilteredImages.add(outputInformations.getFilteredMat(originalImage, filter));
		}
		
		return listOfFilteredImages;
	}
}
