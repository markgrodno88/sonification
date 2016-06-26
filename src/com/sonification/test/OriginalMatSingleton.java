package com.sonification.test;


import java.util.LinkedHashMap;
import java.util.Map;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import com.sonification.filters.ImageFilter;
import android.graphics.Bitmap;

public class OriginalMatSingleton {
	private Mat originalMat;
	private ImageFilter imageFilter1;
	private Map <String,ImageFilter> mapOfList;
	static {
	    if (!OpenCVLoader.initDebug()) {
	        System.out.println("COS");
	    }
	}
	private OriginalMatSingleton() {
		 originalMat = new Mat();
		 mapOfList = new LinkedHashMap<>(3);
	}
	private static class SingletonHolder {
		private static final OriginalMatSingleton INSTANCE = new OriginalMatSingleton();
	}
	public static OriginalMatSingleton getInstance() {	
		
		return SingletonHolder.INSTANCE;
	}	
	public void setOriginalMat(Bitmap bitmapOriginal){
		Utils.bitmapToMat(bitmapOriginal, originalMat);
	}
	public Mat getOriginalMat(){
		return originalMat;
	}
	public void setCurretlyFilters(String name,ImageFilter imageFilter){
		if(mapOfList.size() < 3){
			mapOfList.put(name, imageFilter);
		}
	} 
	public Map<String,ImageFilter> getCurrentlyFilters(){
		return mapOfList;
	}
	public void clearCurrentlyFilters(){
		mapOfList.clear();
	}
	public void deleteFilterFromMap(String string){
		mapOfList.remove(string);
	}
	public int getHeightOfOriginalMat(){
		return originalMat.height();
	}
	public int getWidthOfOriginalMat(){
		return originalMat.width();
	}
	//pojedynczy filtr
	public ImageFilter getCurrentlyFilter(){
		return imageFilter1;
	}
	public void setCurretlyFilter(ImageFilter imageFilter){
		this.imageFilter1 = imageFilter;
	} 



}
