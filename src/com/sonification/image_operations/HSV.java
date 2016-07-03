package com.sonification.image_operations;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.sonification.exceptions.ExceptionOfProject;
import com.sonification.test.ConstantValue;

import android.graphics.Color;
import android.util.Log;

public class HSV {

	private final static float BASE_FREQUENCY_NORMALISED = 0.3f, C1_NORMALISED = 0.4f, C2_NORMALISED = 0.3f;
	private ImageOperations imageOperations = new ImageOperations();

	
	public Mat convertRGBToHSVMat (Mat mat){
		Mat hsvMat = new Mat();
		Imgproc.cvtColor(mat, hsvMat, Imgproc.COLOR_RGB2HSV);
		return hsvMat;
	}
	public List<Float> getListParametersFromHSV(Mat mat) throws ExceptionOfProject{
		Scalar scalar = Core.mean(mat);
		float meanH, meanS, meanV;
		if(mat.channels() == 3){
			meanH = imageOperations.roundValue((float)(scalar.val[ConstantValue.RED_INDEX]));
			meanS = imageOperations.roundValue((float)(scalar.val[ConstantValue.GREEN_INDEX]));
			meanV =	imageOperations.roundValue((float)(scalar.val[ConstantValue.BLUE_INDEX]));	
		}else{
			throw new ExceptionOfProject("Incorrect number of color channels.");
		}
		List <Float> listMeanOfHSVParameters = new ArrayList<>(3);
	
		listMeanOfHSVParameters.add(meanH);
		listMeanOfHSVParameters.add(meanS);
		listMeanOfHSVParameters.add(meanV);		
		return listMeanOfHSVParameters;	
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
