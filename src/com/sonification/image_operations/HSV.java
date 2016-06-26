package com.sonification.image_operations;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;

import com.sonification.test.ConstantValue;

import android.graphics.Color;

public class HSV {

	private final static float BASE_FREQUENCY_NORMALISED = 0.3f, C1_NORMALISED = 0.4f, C2_NORMALISED = 0.3f;

	private List<List<Float>> getListHSVParametersFromHSV(Mat mat){
		List<Float> listOfHue = new ArrayList<>();
		List<Float> listOfSaturation = new ArrayList<>();
		List<Float> listOfValue = new ArrayList<>();
		float [] hsv;
		for(int i = 0; i < mat.cols(); i++){
			for(int j = 0; j < mat.rows(); j++){
				hsv = convertRGBpixelToHSVpixel((int)mat.get(i, j)[ConstantValue.RED_INDEX],
											    (int)mat.get(i, j)[ConstantValue.GREEN_INDEX],
											    (int)mat.get(i, j)[ConstantValue.BLUE_INDEX]);
				listOfHue.add(hsv[ConstantValue.HUE_INDEX]);
				listOfSaturation.add(hsv[ConstantValue.SATURATION_INDEX]);
				listOfValue.add(hsv[ConstantValue.VALUE_INDEX]);
			}
		}	
		List<List<Float>> listParametersOfHSV = new ArrayList<>();
		listParametersOfHSV.add(listOfHue);
		listParametersOfHSV.add(listOfSaturation);
		listParametersOfHSV.add(listOfValue);
		return listParametersOfHSV;	
	}

	public List<Float> calculateSumOfHSVParameters(Mat mat, boolean normalisationValue){
		List<List<Float>> listParametersOfHSV = getListHSVParametersFromHSV(mat);
		float sumH = 0, sumS = 0, sumV = 0;
		for(int i = 0; i < listParametersOfHSV.get(ConstantValue.HUE_INDEX).size(); i++){
			sumH += listParametersOfHSV.get(ConstantValue.HUE_INDEX).get(i);
			sumS += listParametersOfHSV.get(ConstantValue.SATURATION_INDEX).get(i);
			sumV += listParametersOfHSV.get(ConstantValue.VALUE_INDEX).get(i);
		}
		List<Float> list0fSum = new ArrayList<>(3);
		if(normalisationValue == true){
			list0fSum.add(sumH/calculateRangeToNormalisation(mat));
			list0fSum.add(sumS/calculateRangeToNormalisation(mat));
			list0fSum.add(sumV/calculateRangeToNormalisation(mat));
		}else{
			list0fSum.add(sumH/calculateCoutOfPixels(mat));
			list0fSum.add(sumS/calculateCoutOfPixels(mat));
			list0fSum.add(sumV/calculateCoutOfPixels(mat));
		}
		return list0fSum;
	}

	private int calculateRangeToNormalisation(Mat mat){	
		return calculateCoutOfPixels(mat)*ConstantValue.HSV_HUE;
	}
	private int calculateCoutOfPixels(Mat mat){
		return mat.cols()*mat.rows();
	}

	private float[] convertRGBpixelToHSVpixel(int r, int g, int b){
		float[] hsv = new float[3];
		Color.RGBToHSV(r, g, b, hsv);		
		return hsv;
	}
	public List<Float> getListFrequencyHSV(){
		List<Float> listOfFrequency = new ArrayList<>(3);
		
		return listOfFrequency;
	}
	public float getFrequencyF1(float f2, float meanOfSaturation){
		return f2 - meanOfSaturation*C2_NORMALISED;
	}
	public float getFrequencyF2(float meanOfHue){
		return BASE_FREQUENCY_NORMALISED + meanOfHue*C1_NORMALISED;
	}
	public float getFrequencyF3(float f2, float meanOfSaturation){
		return f2 + meanOfSaturation*C2_NORMALISED;
	}
	
}
