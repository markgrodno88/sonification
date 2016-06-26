package com.sonification.filters;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Sobel implements ImageFilter{
	@Override
	public Mat createFiltr(Mat originalImage) {
		Mat blurredImage=new Mat();
		Size size=new Size(9,9);
		Imgproc.GaussianBlur(originalImage, blurredImage, size, 0,0);
		Mat gray = new Mat();
		Imgproc.cvtColor(blurredImage, gray, Imgproc.COLOR_RGB2GRAY);
		Mat xFirstDervative =new Mat(),yFirstDervative =new Mat();
		int ddepth=CvType.CV_16S;
		Imgproc.Sobel(gray, xFirstDervative,ddepth , 1,0);
		Imgproc.Sobel(gray, yFirstDervative,ddepth , 0,1);
		Mat absXD=new Mat(),absYD=new Mat();
		Core.convertScaleAbs(xFirstDervative, absXD);
		Core.convertScaleAbs(yFirstDervative, absYD);
		Mat edgeImage=new Mat();
		Core.addWeighted(absXD, 0.5, absYD, 0.5, 0, edgeImage);
		return edgeImage;	
	}
	public String getName() {
		return this.getClass().getSimpleName();
	}
	@Override
	public double getDimension() {
		return 0;
	}
 }