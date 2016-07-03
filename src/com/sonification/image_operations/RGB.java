package com.sonification.image_operations;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.sonification.exceptions.ExceptionOfProject;
import com.sonification.test.ConstantValue;
import com.sonification.test.OriginalMatSingleton;

import android.util.Log;


public class RGB {
	
	private ImageOperations imageOperations = new ImageOperations();
	private ConstantValue cVALUE =  new ConstantValue();
	public List<Float> calculateFrequncyFromRGBChannels(List<Float> dominantFrequncies, boolean normalisationValue){
		List<Float> frequnciesFromRGBChannels = new ArrayList<>(3);
		float coefficient = ConstantValue.FREQUENCY_A_SOUND_HZ * ConstantValue.FREQUENCY_NORMALISATION_HZ;
		if(normalisationValue == true){
			frequnciesFromRGBChannels.add(dominantFrequncies.get(ConstantValue.RED_INDEX) * ConstantValue.FREQUENCY_C_SOUND_HZ/coefficient);
			frequnciesFromRGBChannels.add(dominantFrequncies.get(ConstantValue.GREEN_INDEX) * ConstantValue.FREQUENCY_E_SOUND_HZ/coefficient);
			frequnciesFromRGBChannels.add(dominantFrequncies.get(ConstantValue.BLUE_INDEX) * ConstantValue.FREQUENCY_G_SOUND_HZ/coefficient);
		}else{
			frequnciesFromRGBChannels.add(dominantFrequncies.get(ConstantValue.RED_INDEX) *
															   ConstantValue.FREQUENCY_C_SOUND_HZ/ConstantValue.FREQUENCY_A_SOUND_HZ);
			frequnciesFromRGBChannels.add(dominantFrequncies.get(ConstantValue.GREEN_INDEX) *
															   ConstantValue.FREQUENCY_E_SOUND_HZ/ConstantValue.FREQUENCY_A_SOUND_HZ);
			frequnciesFromRGBChannels.add(dominantFrequncies.get(ConstantValue.BLUE_INDEX) *
															   ConstantValue.FREQUENCY_G_SOUND_HZ/ConstantValue.FREQUENCY_A_SOUND_HZ);
		}
		return frequnciesFromRGBChannels;

	}
	public List<Float> calculateMeanFromRGBChannels(Mat originalMat, boolean normalisationValue) throws ExceptionOfProject {
		Scalar scalar = Core.mean(originalMat);
		float meanR, meanG, meanB;
		if(originalMat.channels() == 3){
			meanR = imageOperations.roundValue((float)(scalar.val[ConstantValue.RED_INDEX]));
			meanG = imageOperations.roundValue((float)(scalar.val[ConstantValue.GREEN_INDEX]));
			meanB =	imageOperations.roundValue((float)(scalar.val[ConstantValue.BLUE_INDEX]));	
		}else if(originalMat.channels() == 1){	
			throw new ExceptionOfProject("Can't calculate RGB channel's parameter because its grayscale mat.");
		}else{
			throw new ExceptionOfProject("Incorrect number of color channels.");
		}
		List <Float> listOfChannels = new ArrayList<>(3);
		if(normalisationValue == true){
			meanR = meanR/ConstantValue.COUNT_OF_LEVEL;
			meanG = meanG/ConstantValue.COUNT_OF_LEVEL;
			meanB = meanB/ConstantValue.COUNT_OF_LEVEL;
		}	
		listOfChannels.add(meanR);
		listOfChannels.add(meanG);
		listOfChannels.add(meanB);		
		return listOfChannels;
	}
	/*
	public float getMeanFromRedChannel(List<Float> list, boolean normalisationValue) {	
		if(normalisationValue == true){
			return list.get(ConstantValue.RED_INDEX)/ConstantValue.COUNT_OF_LEVEL;
		}else{
			return list.get(ConstantValue.RED_INDEX);
		}	
	}
	public float getMeanFromGreenChannel(List<Float> list,boolean normalisationValue){		
		if(normalisationValue == true){
			Log.d("green", ""+list.get(1)/ConstantValue.COUNT_OF_LEVEL);
			return list.get(1)/ConstantValue.COUNT_OF_LEVEL;
		}else{
			Log.d("green", ""+list.get(1));
			return list.get(1);
		}
	}
	public float getMeanFromBlueChannel(List<Float> list,boolean normalisationValue){		
		if(normalisationValue == true){
			Log.d("blue", ""+list.get(2)/ConstantValue.COUNT_OF_LEVEL);
			return list.get(2)/ConstantValue.COUNT_OF_LEVEL;
		}else{
			Log.d("blue", ""+list.get(2));
			return list.get(2);
		}	
	} 
	
	public float getFrequencyRedChannel(boolean normalisationValue){		
		if(normalisationValue == true){
			return FREQUENCY_RED_HZ/FREQUENCY_BASE_HZ;
		}else{
			return FREQUENCY_RED_HZ;
		}
	} 
	
	public float getFrequencyGreenChannel(boolean normalisationValue){		
		if(normalisationValue == true){
			return FREQUENCY_GREEN_HZ/FREQUENCY_BASE_HZ;
		}else{
			return FREQUENCY_GREEN_HZ;
		}
	}
	
	public float getFrequencyBlueChannel(boolean normalisationValue){		
		if(normalisationValue == true){
			return FREQUENCY_BLUE_HZ/FREQUENCY_BASE_HZ;
		}else{
			return FREQUENCY_BLUE_HZ;
		}
	}
	*/
}
