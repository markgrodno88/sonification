package com.sonification.image_operations;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import com.sonification.exceptions.ExceptionOfProject;
import com.sonification.test.ConstantValue;


public class RGB {
	private final static float FREQUENCY_RED_kHz= 11.0f,
		       FREQUENCY_GREEN_kHz = 22.0f,
		       FREQUENCY_BLUE_kHz = 33.0f,
		       FREQUENCY_NORMALISED_kHz = 44.0f;
	ImageOperations imeageOperation = new ImageOperations();
	
	private List<Float> calculateMeanFromRGB(Mat originalMat) throws ExceptionOfProject {
		Scalar scalar = Core.mean(originalMat);
		float meanR, meanG, meanB;
		if(originalMat.channels()== 4){
			meanR = imeageOperation.roundValue((float)(scalar.val[ConstantValue.RED_INDEX]));
			meanG = imeageOperation.roundValue((float)(scalar.val[ConstantValue.GREEN_INDEX]));
			meanB =	imeageOperation.roundValue((float)(scalar.val[ConstantValue.BLUE_INDEX]));										
		}else if(originalMat.channels()==1){	
			throw new ExceptionOfProject("Can't calculate RGB channel's parameter because its grayscale mat.");
		}else{
			throw new ExceptionOfProject("Incorrect number of color channels.");
		}
		List <Float> listOfChannels = new ArrayList<>();
		listOfChannels.add(meanR);
		listOfChannels.add(meanG);
		listOfChannels.add(meanB);
		
		return listOfChannels;
	}
	public float getMeanFromRedChannel(Mat originalMat, boolean normalisationValue) {	
		try{
			if(normalisationValue == true){
				return calculateMeanFromRGB(originalMat).get(0)/ConstantValue.COUNT_OF_LEVEL;
			}else{
				return calculateMeanFromRGB(originalMat).get(0);
			}
		}catch(ExceptionOfProject e){
			e.printStackTrace();
			return 0;
		}	
	}
	public float getMeanFromGreenChannel(Mat originalMat,boolean normalisationValue){		
		try{
			if(normalisationValue == true){
				return calculateMeanFromRGB(originalMat).get(1)/ConstantValue.COUNT_OF_LEVEL;
			}else{
				return calculateMeanFromRGB(originalMat).get(1);
			}
		}catch(ExceptionOfProject e){
			e.printStackTrace();
			return 0;
		}	
	}
	public float getMeanFromBlueChannel(Mat originalMat,boolean normalisationValue){		
		try{
			if(normalisationValue == true){
				return calculateMeanFromRGB(originalMat).get(2)/ConstantValue.COUNT_OF_LEVEL;
			}else{
				return calculateMeanFromRGB(originalMat).get(2);
			}
		}catch(ExceptionOfProject e){
			e.printStackTrace();
			return 0;
		}	
	} 
	
	public float getFrequencyRedChannel(boolean normalisationValue){		
		if(normalisationValue == true){
			return FREQUENCY_RED_kHz/FREQUENCY_NORMALISED_kHz;
		}else{
			return FREQUENCY_RED_kHz;
		}
	} 
	
	public float getFrequencyGreenChannel(boolean normalisationValue){		
		if(normalisationValue == true){
			return FREQUENCY_GREEN_kHz/FREQUENCY_NORMALISED_kHz;
		}else{
			return FREQUENCY_GREEN_kHz;
		}
	}
	
	public float getFrequencyBlueChannel(boolean normalisationValue){		
		if(normalisationValue == true){
			return FREQUENCY_BLUE_kHz/FREQUENCY_NORMALISED_kHz;
		}else{
			return FREQUENCY_BLUE_kHz;
		}
	}
}
