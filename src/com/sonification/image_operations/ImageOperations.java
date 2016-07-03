package com.sonification.image_operations;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.sonification.exceptions.ExceptionOfProject;
import com.sonification.filters.EnumsFilters.FilterName;
import com.sonification.filters.ImageFilter;
import com.sonification.parameters.Parameter;
import com.sonification.test.ConstantValue;
import com.sonification.test.Fragmentation;
import com.sonification.test.Type;
import com.sonification.test.OriginalMatSingleton;
import android.graphics.Bitmap;

public class ImageOperations {

	private int image_x, image_y, x_width, y_height;
	private OriginalMatSingleton originalMatSingleton = OriginalMatSingleton.getInstance();
	private Mat originalMat;

	private ImageFilter currentlyFilter; 
	private Map <FilterName,ImageFilter> currentlyFilters;
	private Mat currentMat, currentMultiMat;
	private Mat currentOriginalMatSingleton;
	
	//to RGB mode
	private RGB rgbChannels;

	//to HSV mode
	private HSV hsv;

	//Metoda do konwertacji Map na Bitmap
	public Bitmap convertToBitmapImage(Mat image){
		Bitmap bitMap = Bitmap.createBitmap(image.cols(),image.rows(),Bitmap.Config.RGB_565);
		Utils.matToBitmap(image, bitMap);
		return bitMap;
	}
	//Metoda do wyciecia obszaru zainteresowania z podaniem rozmiaru obszaru
	public Mat getRoiOfOriginalMat(int xStart, int width, int yStart, int height){
		originalMat = originalMatSingleton.getOriginalMat();
	    return originalMat.submat(yStart, height , xStart, width);	
	}
	/*
	//Metoda do wyciecia obszaru zainteresowania ze stalym obszarem
	public Mat getRoiOfOriginalMat(float norm_x, float norm_y){
		currentOriginalMatSingleton = originalMatSingleton.getCurrentlyMat();
		int w = currentOriginalMatSingleton.cols();
		int h = currentOriginalMatSingleton.rows();
		int x = (int)Math.round(norm_x*w);
		int y = (int)Math.round(norm_y*h);
		int scalValue = 10;
		
		if(x >= scalValue && (x <= w - scalValue)){
			image_x = x-scalValue;
			x_width = image_x+2*scalValue;
		}else if ((x >= 0) && (x < scalValue)){
			image_x = 0;
			x_width = image_x+scalValue;
		}else if ((x > w - scalValue) && (x <= w)){
			image_x = x-scalValue;
			x_width = w;		
		}
		
		if((y >= scalValue) && (y <= h - scalValue)){
			image_y = y-scalValue;
			y_height = image_y+2*scalValue;
		}else if ((y >= 0) && (y < scalValue)){
			image_y = 0;
			y_height = image_y+y+scalValue;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		}else if ((y > h - scalValue) && (y <= h)){
			image_y = y-scalValue;
			y_height = h;		
		}	
		return currentOriginalMatSingleton.submat(image_y, y_height , image_x, x_width);
	}
	*/
	//Metoda do wyciecia obszaru zainteresowania ze stalym obszarem
	public Mat getRoiOfOriginalMat(float norm_x, float norm_y, int dim){
		if(originalMatSingleton.getTypeOfSonification().equals(Type.HSV)){
			currentOriginalMatSingleton = originalMatSingleton.getCurrentlyHSVMat();
		}else if(originalMatSingleton.getTypeOfSonification().equals(Type.SPLIT)){
			currentOriginalMatSingleton = originalMatSingleton.getCurrentlyGrayMat();
		}else{
			currentOriginalMatSingleton = originalMatSingleton.getCurrentlyRGBMat();
		}
		int w = currentOriginalMatSingleton.cols();
		int h = currentOriginalMatSingleton.rows();
		int x = (int)Math.round(norm_x*w);
		int y = (int)Math.round(norm_y*h);
		int scalValue = 20+dim;
		
		if(x >= scalValue && (x <= w - scalValue)){
			image_x = x-scalValue;
			x_width = image_x+2*scalValue;
		}else if ((x >= 0) && (x < scalValue)){
			image_x = 0;
			x_width = image_x+scalValue;
		}else if ((x > w - scalValue) && (x <= w)){
			image_x = x-scalValue;
			x_width = w;		
		}
		
		if((y >= scalValue) && (y <= h - scalValue)){
			image_y = y-scalValue;
			y_height = image_y+2*scalValue;
		}else if ((y >= 0) && (y < scalValue)){
			image_y = 0;
			y_height = image_y+y+scalValue;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		}else if ((y > h - scalValue) && (y <= h)){
			image_y = y-scalValue;
			y_height = h;		
		}	
		return currentOriginalMatSingleton.submat(image_y, y_height , image_x, x_width);
	}
	
	//Metoda do pobrania listy parametrow z obszaru po zadaniu x i y
	public List<Parameter> getListOfParameters(float x, float y){
		//currentlyFilter = originalMatSingleton.getCurrentlyFilter();
		currentlyFilters = originalMatSingleton.getCurrentlyFilters();
		//check count of filters and use needed method
		if(currentlyFilters.size() == 0){
			currentMultiMat = getRoiOfOriginalMat(x, y, 0);
		}else{
			currentMultiMat = getMultiFilteredMat(x, y);
		}
		//create list of parameters
		List<Parameter> listOfParameters = new ArrayList<>(12);
		//create list of dominant frequencies
		List<Float> dominantFrequencies = originalMatSingleton.getListOfDominantFrequences();
		//check type of sonification
		if(originalMatSingleton.getTypeOfSonification().equals(Type.RGB)){
			listOfParameters.clear();
			//RGB channels:
			rgbChannels = new RGB();
			List<Float> listOfRGBAmplitudes = null;
			List<Float> listOfRGBFrequencies = null;
			try {
				listOfRGBAmplitudes = rgbChannels.calculateMeanFromRGBChannels(currentMultiMat,true);
			} catch (ExceptionOfProject e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listOfRGBFrequencies = rgbChannels.calculateFrequncyFromRGBChannels(dominantFrequencies, true);
			//Red:
			Parameter paremeterRedChannel = new Parameter(listOfRGBAmplitudes.get(ConstantValue.RED_INDEX),
														  listOfRGBFrequencies.get(0));
			
			//Green:
			Parameter paremeterGreenChannel = new Parameter(listOfRGBAmplitudes.get(ConstantValue.GREEN_INDEX),
															listOfRGBFrequencies.get(1));
			//Blue:
					Parameter paremeterBlueChannel = new Parameter(listOfRGBAmplitudes.get(ConstantValue.BLUE_INDEX),
																   listOfRGBFrequencies.get(2));
			
			listOfParameters.add(paremeterRedChannel);
			listOfParameters.add(paremeterGreenChannel);
			listOfParameters.add(paremeterBlueChannel);
			
		}else if(originalMatSingleton.getTypeOfSonification().equals(Type.HSV)){		
			listOfParameters.clear();
			//HSV channels:
			hsv = new HSV();
			List<Float> listMeanValuesFromHSV = null;
			try {
				listMeanValuesFromHSV = hsv.getListParametersFromHSV(currentMultiMat);
			} catch (ExceptionOfProject e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//List<Float> hsvList = hsv.calculateSumOfHSVParameters(currentMultiMat);
			float meanOfValueHSV = listMeanValuesFromHSV.get(0);//hsvList.get(ConstantValue.VALUE_INDEX);
			float meanOfSaturationHSV = listMeanValuesFromHSV.get(1);//hsvList.get(ConstantValue.SATURATION_INDEX);
			float meanOfHueHSV = listMeanValuesFromHSV.get(2);//hsvList.get(ConstantValue.HUE_INDEX);
			float f2 = hsv.getFrequencyF2(meanOfHueHSV);
			float f1 = hsv.getFrequencyF1(f2, meanOfSaturationHSV);
			float f3 = hsv.getFrequencyF3(f2, meanOfSaturationHSV);
			Parameter parameterHSV_1 = new Parameter(meanOfValueHSV, 1);
			Parameter parameterHSV_2 = new Parameter(meanOfValueHSV, 1);
			Parameter parameterHSV_3 = new Parameter(meanOfValueHSV, 1);
			listOfParameters.add(parameterHSV_1);
			listOfParameters.add(parameterHSV_2);
			listOfParameters.add(parameterHSV_3);				 
			
		}else if(originalMatSingleton.getTypeOfSonification().equals(Type.OTHER)){
			listOfParameters.clear();
			float meanValue = 0;
			try {
				meanValue = getMedianValueFromMat(currentMultiMat,false);
			}catch (ExceptionOfProject e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Luminosity:
			Parameter parameterMean = new Parameter(meanValue, 1);
			listOfParameters.add(parameterMean);
		}else if(originalMatSingleton.getTypeOfSonification().equals(Type.SPLIT)){
			SonificationWithFragmentation sWF = new SonificationWithFragmentation();
			List <Mat> listOfMat = sWF.splitMat(currentMultiMat, Fragmentation.FOUR);
			List <Float> listOfNormalisedFrewuencies = sWF.calculateFrequenciesOfFragments(dominantFrequencies,  Fragmentation.FOUR);
			for(int i = 0; i <listOfMat.size(); i++){
				
				try {
					Parameter parameterFragmentation = new Parameter(getMedianValueFromMat(listOfMat.get(i), true), listOfNormalisedFrewuencies.get(i));
					listOfParameters.add(parameterFragmentation);
				} catch (ExceptionOfProject e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listOfParameters;
	}
	//Metoda do przyblizenia z obszaru zainteresowania z zadaniem macierzy do analizy i krotnosci powiekszenia
	public Mat zoomInMat(Mat originalImage, int value) throws InputMismatchException, IllegalArgumentException{
		Mat doubleRoi = new Mat();
		Size doubleSize = new Size(originalImage.width()*value,originalImage.height()*value);
	    Imgproc.resize(originalImage,doubleRoi,doubleSize);
	    return doubleRoi;
	}
	//Metoda do przyblizenia z obszaru zainteresowania z zadaniem macierzy do analizy i krotnosci pomniejszenia
	public Mat zoomOutMat (Mat originalImage, int value)throws InputMismatchException,IllegalArgumentException{
		Mat doubleRoi = new Mat();
		Size doubleSize = new Size(originalImage.width()/value,originalImage.height()/value);
	    Imgproc.resize(originalImage,doubleRoi,doubleSize);
	    return doubleRoi;
	}
	/*
	//Metoda do obliczania wartosci sredniej i odchylenia
	public TwoFloatValues getMedianAndStdFromMat(Mat originalImage) throws ExceptionOfProject{
		MatOfDouble mean = new MatOfDouble();
		MatOfDouble stddev = new MatOfDouble();
		Core.meanStdDev(originalImage, mean , stddev);
		float d, std;
		if(originalImage.channels()==3){
			d = (float)((ConstantValue.BLUE_COEFFICIENT*mean.get(0,0)[0] 
						+ ConstantValue.GREEN_COEFFICIENT*mean.get(1,0)[0]
						+ ConstantValue.RED_COEFFICIENT*mean.get(2,0)[0])/ConstantValue.COUNT_OF_LEVEL);
			
			std = (float)((ConstantValue.BLUE_COEFFICIENT*stddev.get(0,0)[0]
							+ ConstantValue.GREEN_COEFFICIENT*stddev.get(1,0)[0]
							+ ConstantValue.RED_COEFFICIENT*stddev.get(2,0)[0])/ConstantValue.COUNT_OF_LEVEL);
			
		}else if(originalImage.channels()==1){
			d = (float)((mean.get(0,0)[0])/ConstantValue.COUNT_OF_LEVEL);
			std = (float)((stddev.get(0,0)[0])/ConstantValue.COUNT_OF_LEVEL);
		}else{
			throw new ExceptionOfProject("Invalid number of channels.");
		}
		return new TwoFloatValues(roundValue(d), roundValue(std));
	}
	*/
	//Metoda do obliczania wartosci sredniej z zadanych wspolrzednych centrum roi x,y

	public float getMedianValueFromMat(Mat originalImage, boolean normalisationValue) throws ExceptionOfProject {
		
		Scalar scalar = Core.mean(originalImage);
		float mean = 0;
		if(originalImage.channels() == 3){
			Log.d("meanR", ""+scalar.val[ConstantValue.RED_INDEX]);
			Log.d("meanG", ""+scalar.val[ConstantValue.GREEN_INDEX]);
			Log.d("meanB", ""+scalar.val[ConstantValue.BLUE_INDEX]);
			mean = roundValue((float)(ConstantValue.BLUE_COEFFICIENT*scalar.val[ConstantValue.BLUE_INDEX]
								+ ConstantValue.GREEN_COEFFICIENT*scalar.val[ConstantValue.GREEN_INDEX]
								+ ConstantValue.RED_COEFFICIENT*scalar.val[ConstantValue.RED_INDEX]));
		}else if(originalImage.channels() == 1){
			Log.d("meanGRAY", ""+scalar.val[ConstantValue.GRAY_INDEX]);
			mean = roundValue((float)scalar.val[ConstantValue.GRAY_INDEX]);
		}else{
			throw new ExceptionOfProject("Invalid number of channels.");
		}
		if(normalisationValue == true){
			return mean/ConstantValue.COUNT_OF_LEVEL;
		}else{
			return mean;
		}
	}
	//Metoda zaokraglajaca float do 6 liczb po przecinku
	public float roundValue(float in){
		in *= ConstantValue.COUNT_ROUND;
		in = Math.round(in);
		in /= ConstantValue.COUNT_ROUND;
		return in;
	}

	//Metoda do filtrowania, argument 1 - obszar analizowany
	public Mat getFilteredMat(Mat originalImage){
		currentlyFilter = originalMatSingleton.getCurrentlyFilter();
		return currentlyFilter.filterMat(originalImage);
	}
	
	public Mat getMultiFilteredMat(Mat originalImage){
		currentlyFilters = originalMatSingleton.getCurrentlyFilters();
		Iterator<Map.Entry<FilterName, ImageFilter>> entries = currentlyFilters.entrySet().iterator();
		
		while (entries.hasNext()){
		    Entry<FilterName, ImageFilter> entry = entries.next();
		    originalImage = entry.getValue().filterMat(originalImage);
		}
		return originalImage;
	}
	
	public Mat getMultiFilteredMat(float norm_x, float norm_y){
		currentlyFilters = originalMatSingleton.getCurrentlyFilters();
		Log.d("!!!Filtry", ""+currentlyFilters);
		int finalDimension = 0;
		Iterator<Map.Entry<FilterName, ImageFilter>> entries = currentlyFilters.entrySet().iterator();
		while (entries.hasNext()){
		    Entry<FilterName, ImageFilter> entry = entries.next();
		    int dim =((int)Math.round(entry.getValue().getDimension())-1)/2;
		    if(dim>finalDimension){
		    	finalDimension = dim;	
		    }  
		}		
		Mat currentlyMat = getRoiOfOriginalMat(norm_x, norm_y, finalDimension);
		while (entries.hasNext()){
		    Entry<FilterName, ImageFilter> entry = entries.next();
		    currentlyMat = entry.getValue().filterMat(currentlyMat);
		}

		return currentlyMat.submat(finalDimension, finalDimension+20 , finalDimension, finalDimension+20);
	}
	
}
