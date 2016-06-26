package com.sonification.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.opencv.core.Mat;
import com.sonification.filters.ImageFilter;
import com.sonification.image_operations.HistogramRGB;
import com.sonification.image_operations.ImageOperations;
import com.sonification.test.OriginalMatSingleton;



public class OutputInformations {
	ImageOperations imageOperations = new ImageOperations();
	public OriginalMatSingleton originalMatSingleton = OriginalMatSingleton.getInstance();
	//HistogramRGB histogram = new HistogramRGB();
	//MedianResult mResult;
	//STDResult stdResult;
	/*public OutputDate getMatAndParams(Mat originalImage, ImageFilter filter){
		Mat sampleImage = filter.createFiltr(originalImage);
		float meanValue = imageOperations.getMedianValueFromMat(originalImage);
		List<Parameter> listOfParameters = new ArrayList<>(11);
		Parameter parameter = new Parameter(meanValue, 1);
		listOfParameters.add(parameter);
		for(int i = 1; i<12;i++){
			listOfParameters.add(new Parameter(1, 1));
		}
		OutputDate outputDate = new OutputDate(sampleImage, listOfParameters);
		return outputDate;
	}*/
	/*public List<Parameter> getListOfParameters(int x, int y){
		Mat sampleMat = imageOperations.getRoiOfOriginalMat(x, y);
		Log.d("!!!!!!!!!!!!!!!!!!!!", "mat"+sampleMat);
		//float meanValue = imageOperations.getMedianAndStdFromMat(originalMatSingleton.getOriginalMat()).getX();
		//float stdValue = imageOperations.getMedianAndStdFromMat(originalMatSingleton.getOriginalMat()).getY();
		List<Parameter> listOfParameters = new ArrayList<>(11);
		//Parameter parameterMean = new Parameter(meanValue, 1);
		//Parameter parameterStd = new Parameter(stdValue, 1);
		//listOfParameters.add(parameterMean);
		//listOfParameters.add(parameterStd);
		for(int i = 0; i<12;i++){
			listOfParameters.add(new Parameter(1, 1));
		}

		return listOfParameters;
	}*/
	public Mat getFilteredMat(Mat originalImage, ImageFilter filter){
		return filter.createFiltr(originalImage);
	}
	}
	/*bezposrednio z histogramu
	public List<Parameter> getListOfParameters(Mat originalImage){
	    float meanValue = 0;
	    float std = 0;
	    mResult = new MedianResult(histogram, originalImage);
	    stdResult = new STDResult(histogram, originalImage);
		try {
			meanValue = mResult.call();
			std = stdResult.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Parameter> listOfParameters = new ArrayList<>(11);
		listOfParameters.add(new Parameter(meanValue, 1));
		listOfParameters.add(new Parameter(std, 1));
		
		for(int i = 2; i<12;i++){
			listOfParameters.add(new Parameter(1, 1));
		}

		return listOfParameters;
	}
}
class MedianResult implements Callable<Float>{
	HistogramRGB histogram;
	Mat originalImage;
	public MedianResult(HistogramRGB histogram, Mat originalImage) {
		this.histogram = histogram;
		this.originalImage = originalImage;
	}
	@Override
	public Float call() throws Exception {	
		return histogram.calculateMedian(originalImage);
	}
	
}
class STDResult implements Callable<Float>{
	HistogramRGB histogram;
	Mat originalImage;
	public STDResult(HistogramRGB histogram, Mat originalImage) {
		this.histogram = histogram;
		this.originalImage = originalImage;
	}
	@Override
	public Float call() throws Exception {	
		return histogram.calculateSTD(originalImage);
	}
	
}*/