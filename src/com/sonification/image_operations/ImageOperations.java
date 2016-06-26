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
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.sonification.exceptions.ExceptionOfProject;
import com.sonification.filters.ImageFilter;
import com.sonification.parameters.Parameter;
import com.sonification.test.ConstantValue;
import com.sonification.test.OriginalMatSingleton;
import android.graphics.Bitmap;

public class ImageOperations {

	private int image_x, image_y, x_width, y_height;
	OriginalMatSingleton originalMatSingleton = OriginalMatSingleton.getInstance();
	Mat originalMat = originalMatSingleton.getOriginalMat();
	ImageFilter currentlyFilter; 
	Map <String,ImageFilter> currentlyFilters;
	Mat currentMat, currentMultiMat;
	
	//Dla trybu sonifikacji RGB
	private RGB rgbChannels;

	//Dla trybu sonifikacji HSV
	private HSV hsv;
	
	//Metoda do konwertacji Map na Bitmap
	public Bitmap convertToBitmapImage(Mat image){
		Bitmap bitMap = Bitmap.createBitmap(image.cols(),image.rows(),Bitmap.Config.RGB_565);
		Utils.matToBitmap(image, bitMap);
		return bitMap;
	}
	//Metoda do wyciecia obszaru zainteresowania z podaniem rozmiaru obszaru
	public Mat getRoiOfOriginalMat(int xStart, int width, int yStart, int height){
	      return originalMat.submat(yStart, height , xStart, width);	
	}
	//Metoda do wyciecia obszaru zainteresowania ze stalym obszarem
	public Mat getRoiOfOriginalMat(float norm_x, float norm_y){
		int matHeight = originalMatSingleton.getHeightOfOriginalMat();
	    int matWidth = originalMatSingleton.getWidthOfOriginalMat();
	   // if ((norm_x>=0 && norm_x <=1) && (norm_y>=0 && norm_y<=1)){
		    int x = (int)Math.round(norm_x*matWidth);
		    int y = (int)Math.round(norm_y*matHeight);
		    
			int scalValue = 10/*matWidth/25*/;//+(kernelsize-1)/2
			if(x >= scalValue && (x <= matWidth - scalValue)){
				image_x = x-scalValue;
				x_width = image_x+2*scalValue;
			}else if ((x >= 0) && (x < scalValue)){
				image_x = 0;
				x_width = image_x+scalValue;
			}else if ((x > matWidth - scalValue) && (x <= matWidth)){
				image_x = x-scalValue;
				x_width = matWidth;		
			}
			
			if((y >= scalValue) && (y <= matHeight - scalValue)){
				image_y = y-scalValue;
				y_height = image_y+2*scalValue;
			}else if ((y >= 0) && (y < scalValue)){
				image_y = 0;
				y_height = image_y+y+scalValue;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
			}else if ((y > matHeight - scalValue) && (y <= matHeight)){
				image_y = y-scalValue;
				y_height = matHeight;		
			}
		//}
	    Log.d("!!!RozmiarMacierzy", ""+matHeight+"x"+matWidth);
		Mat m = originalMat.submat(image_y, y_height , image_x, x_width);
		Log.d("!!!RozmiarMacierzy", ""+m.rows()+"x"+m.cols());
		return m;
	}
	//Metoda do wyciecia obszaru zainteresowania ze stalym obszarem
		public Mat getRoiOfOriginalMat(float norm_x, float norm_y, int dim){
			int matHeight = originalMatSingleton.getHeightOfOriginalMat();
		    int matWidth = originalMatSingleton.getWidthOfOriginalMat();
		    if ((norm_x>=0 && norm_x <=1) && (norm_y>=0 && norm_y<=1)){
			    int x = (int)Math.round(norm_x*matWidth);
			    int y = (int)Math.round(norm_y*matHeight);
			    
				int scalValue = 10+dim/*matWidth/25*/;//
				if(x >= scalValue && (x <= matWidth - scalValue)){
					image_x = x-scalValue;
					x_width = image_x+2*scalValue;
				}else if ((x >= 0) && (x < scalValue)){
					image_x = 0;
					x_width = image_x+scalValue;
				}else if ((x > matWidth - scalValue) && (x <= matWidth)){
					image_x = x-scalValue;
					x_width = matWidth;		
				}
				
				if((y >= scalValue) && (y <= matHeight - scalValue)){
					image_y = y-scalValue;
					y_height = image_y+2*scalValue;
				}else if ((y >= 0) && (y < scalValue)){
					image_y = 0;
					y_height = image_y+y+scalValue;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
				}else if ((y > matHeight - scalValue) && (y <= matHeight)){
					image_y = y-scalValue;
					y_height = matHeight;		
				}
			}
		    Log.d("!!!RozmiarMacierzy", ""+matHeight+"x"+matWidth);
			Mat m = originalMat.submat(image_y, y_height , image_x, x_width);
			Log.d("!!!RozmiarMacierzy", ""+m.rows()+"x"+m.cols());
			return m;
		}
	//Metoda do pobrania listy parametrow z obszaru po zadaniu x i y
	public List<Parameter> getListOfParameters(float x, float y){
		//currentlyFilter = originalMatSingleton.getCurrentlyFilter();
		currentlyFilters = originalMatSingleton.getCurrentlyFilters();
		/*warunek sprawdzający*/
		if(currentlyFilters.size() == 0){
			currentMultiMat = getRoiOfOriginalMat(x, y);
		}else{
			currentMultiMat = getMultiFilteredMat(x, y);
		}
		Log.d("Wymiary koncowe", ""+currentMultiMat.rows()+"x"+currentMultiMat.cols());
		float meanValue = 0;
		try {
			meanValue = getMedianValueFromMat(currentMultiMat,true);
		} catch (ExceptionOfProject e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Parameter> listOfParameters = new ArrayList<>(11);
		//Luminosity:
		Parameter parameterMean = new Parameter(meanValue, 1);
		listOfParameters.add(parameterMean);
		
		//RGB channels:
		rgbChannels = new RGB();
		//Red:
		Parameter paremeterRedChannel = new Parameter(rgbChannels.getMeanFromRedChannel(currentMultiMat,true),
														rgbChannels.getFrequencyRedChannel(true));
		
		//Green:
		Parameter paremeterGreenChannel = new Parameter(rgbChannels.getMeanFromGreenChannel(currentMultiMat,true),
				rgbChannels.getFrequencyGreenChannel(true));
		//Blue:
				Parameter paremeterBlueChannel = new Parameter(rgbChannels.getMeanFromBlueChannel(currentMultiMat,true),
						rgbChannels.getFrequencyBlueChannel(true));
		
		listOfParameters.add(paremeterRedChannel);
		listOfParameters.add(paremeterGreenChannel);
		listOfParameters.add(paremeterBlueChannel);
		
		//HSV channels:
		hsv = new HSV();
		List<Float> hsvList = hsv.calculateSumOfHSVParameters(currentMultiMat, true);
		float meanOfValueHSV = hsvList.get(ConstantValue.VALUE_INDEX);
		float meanOfSaturationHSV = hsvList.get(ConstantValue.SATURATION_INDEX);
		float meanOfHueHSV = hsvList.get(ConstantValue.HUE_INDEX);
		float f2 = hsv.getFrequencyF2(meanOfHueHSV);
		float f1 = hsv.getFrequencyF1(f2, meanOfSaturationHSV);
		float f3 = hsv.getFrequencyF3(f2, meanOfSaturationHSV);
		Parameter parameterHSV_1 = new Parameter(meanOfValueHSV, f1);
		Parameter parameterHSV_2 = new Parameter(meanOfValueHSV, f2);
		Parameter parameterHSV_3 = new Parameter(meanOfValueHSV, f3);
		listOfParameters.add(parameterHSV_1);
		listOfParameters.add(parameterHSV_2);
		listOfParameters.add(parameterHSV_3);
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
	//Metoda do obliczania wartosci sredniej z zadanych wspolrzednych centrum roi x,y

	public float getMedianValueFromMat(Mat originalImage, boolean normalisationValue) throws ExceptionOfProject {
		
		Scalar scalar = Core.mean(originalImage);
		float mean = 0;
		Log.d("TAGnew", ""+originalImage.channels());
		if(originalImage.channels()== 4){
			mean = roundValue((float)(ConstantValue.BLUE_COEFFICIENT*scalar.val[0]
								+ ConstantValue.GREEN_COEFFICIENT*scalar.val[1]
								+ ConstantValue.RED_COEFFICIENT*scalar.val[2]));
		}else if(originalImage.channels()==1){
			mean = roundValue((float)scalar.val[0]);
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
		//Log.d("!!!Image", ""+originalImage);
		return currentlyFilter.createFiltr(originalImage);
	}
	
	public Mat getMultiFilteredMat(Mat originalImage){
		currentlyFilters = originalMatSingleton.getCurrentlyFilters();

		Log.d("!!!Filtry", ""+currentlyFilters);
		
		Iterator<Map.Entry<String, ImageFilter>> entries = currentlyFilters.entrySet().iterator();
		
		while (entries.hasNext()){
		    Entry<String, ImageFilter> entry = entries.next();
		    originalImage = entry.getValue().createFiltr(originalImage);
		}
		return originalImage;
	}
	
	public Mat getMultiFilteredMat(float norm_x, float norm_y){
		currentlyFilters = originalMatSingleton.getCurrentlyFilters();
		Log.d("!!!Filtry", ""+currentlyFilters);
		int finalDimension = 0;
		Iterator<Map.Entry<String, ImageFilter>> entries = currentlyFilters.entrySet().iterator();
		while (entries.hasNext()){
		    Entry<String, ImageFilter> entry = entries.next();
		    int dim =((int)Math.round(entry.getValue().getDimension())-1)/2;
		    if(dim>finalDimension){
		    	finalDimension = dim;	
		    }  
		    Log.d("DIMENSION", ""+finalDimension);
		}
		
		Mat currentlyMat = getRoiOfOriginalMat(norm_x, norm_y, finalDimension);
		Iterator<Map.Entry<String, ImageFilter>> entries2 = currentlyFilters.entrySet().iterator();
		while (entries2.hasNext()){
		    Entry<String, ImageFilter> entry = entries2.next();
		    currentlyMat = entry.getValue().createFiltr(currentlyMat);
		}
	    Mat finishMat = currentlyMat.submat(finalDimension, finalDimension+20 , finalDimension, finalDimension+20);
		return finishMat;
	}
}
