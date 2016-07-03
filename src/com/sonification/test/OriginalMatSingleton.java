package com.sonification.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.EnumsFilters.FilterName;
import com.sonification.filters.ImageFilter;
import com.sonification.image_operations.HSV;

import android.graphics.Color;
import android.util.Log;

public class OriginalMatSingleton {
	private Mat originalMat, currentlyRGBMat, currentlyHSVMat, currentlyGrayMat;
	private ImageFilter imageFilter1;
	private Map <FilterName,ImageFilter> mapOfCurrentFilters;
	private float dominantFrequency;
	private List<Float> listOfDominantFrequences;
	private Type typeOfSonification;
	private HSV hsv;
	//private int splitValue;
	static {
	    if (!OpenCVLoader.initDebug()) {
	        System.out.println("Cannot load OpenCV");
	    }
	}
	private OriginalMatSingleton() {
		originalMat = new Mat();
		mapOfCurrentFilters = new LinkedHashMap<>(3);
		currentlyRGBMat = new Mat();
		currentlyHSVMat = new Mat();
		currentlyGrayMat = new Mat();
		listOfDominantFrequences = new ArrayList<>(12);
		hsv = new HSV();
	}
	private static class SingletonHolder {
		private static final OriginalMatSingleton INSTANCE = new OriginalMatSingleton();
	}
	public static OriginalMatSingleton getInstance() {			
		return SingletonHolder.INSTANCE;
	}	

	public void setOriginalMat(Mat mat){
		this.originalMat = mat;

	}
	public Mat getOriginalMat(){
		return originalMat;
	}
	public void setCurrentlyViewParameters(int width, int height){
		Mat dst = new Mat();
		Imgproc.resize(originalMat, dst, new Size(width, height));
		currentlyRGBMat = dst;
		currentlyHSVMat = hsv.convertRGBToHSVMat(currentlyRGBMat);
		Imgproc.cvtColor(currentlyRGBMat, currentlyGrayMat, Imgproc.COLOR_RGB2GRAY);
	}
	public Mat getCurrentlyRGBMat(){
		return currentlyRGBMat;
	}
	
	public Mat getCurrentlyHSVMat(){
		return currentlyHSVMat;
	}
	public Mat getCurrentlyGrayMat(){
		return currentlyGrayMat;
	}
/*	
	public void setSplitValue (int splitValue){
		this.splitValue = splitValue;
	}
	public int getSplitValue(){
		return splitValue;
	}
	*/
	public void setCurretlyFilters(ImageFilter imageFilter){
		if(mapOfCurrentFilters.size() < 3){
			mapOfCurrentFilters.put(imageFilter.getFilterName(), imageFilter);
		}
	} 
	
	public Map<FilterName,ImageFilter> getCurrentlyFilters(){
		return mapOfCurrentFilters;
	}
	public void clearCurrentlyFilters(){
		mapOfCurrentFilters.clear();
	}
	
	public void deleteFilterFromMap(FilterName name){
		mapOfCurrentFilters.remove(name);
	}
	
	public int getHeightOfOriginalMat(){
		return originalMat.height();
	}
	
	public int getWidthOfOriginalMat(){
		return originalMat.width();
	}
	//Single filter
	public ImageFilter getCurrentlyFilter(){
		return imageFilter1;
	}
	public void setCurretlyFilter(ImageFilter imageFilter){
		this.imageFilter1 = imageFilter;
	} 
	//Dominant Frequnces (List)
	public void setListOfDominantFrequences(List<Float> listOfDominantFrequences){
		this.listOfDominantFrequences = listOfDominantFrequences;
	}
	public List<Float> getListOfDominantFrequences(){
		return listOfDominantFrequences;
	}
	//Dominant Frequncy
	public void setSingleDominantFrequency(float dominantFrequency){
		this.dominantFrequency = dominantFrequency;
	}
	public float getDominantFrequency(){
		return dominantFrequency;
	}
	//Type of sonification
	public void setTypeOfSonification(Type typeOfSonification){
		this.typeOfSonification = typeOfSonification;
	}
	public Type getTypeOfSonification(){
		return typeOfSonification;
	}

}
