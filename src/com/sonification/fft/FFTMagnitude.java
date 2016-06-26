package com.sonification.fft;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.ImageFilter;

public class FFTMagnitude/* implements ImageFilter*/{



	public Mat createFiltr(Mat originalImage) {
		Mat gray = new Mat();
		Imgproc.cvtColor(originalImage, gray, Imgproc.COLOR_RGB2GRAY);
		Mat floatGray = new Mat();
		gray.convertTo(floatGray, CvType.CV_32FC1);
		List<Mat> matList = new ArrayList<Mat>();
		matList.add(floatGray);
		Mat zeroMat = Mat.zeros(floatGray.size(), CvType.CV_32F);
		matList.add(zeroMat);
		Mat complexImage = new Mat();
		Core.merge(matList, complexImage);
		Core.dft(complexImage,complexImage);
		
		List<Mat> splitted = new ArrayList<Mat>();
		Core.split(complexImage,splitted);
		Mat magnitude = new Mat();
		Core.magnitude(splitted.get(0), splitted.get(1), magnitude);
		Core.add(Mat.ones(magnitude.size(), CvType.CV_32F), magnitude, magnitude);
		Core.log(magnitude, magnitude);
		Mat phase = new Mat();
		Core.phase(splitted.get(0), splitted.get(1), phase);
		Core.add(Mat.ones(phase.size(), CvType.CV_32F), phase, phase);
		Core.log(phase, phase);
		int cx = magnitude.cols()/2;
		int cy = magnitude.rows()/2;
		Mat q0M = new Mat(magnitude,new Rect(0, 0, cx, cy));
		Mat q0P = new Mat(phase,new Rect(0, 0, cx, cy));
		/*Mat q1 = new Mat(magnitude,new Rect(cx, 0, cx, cy));
		Mat q2 = new Mat(magnitude,new Rect(0, cy, cx, cy));
		Mat q3 = new Mat(magnitude ,new Rect(cx, cy, cx, cy));
		Mat tmp = new Mat();
		q0.copyTo(tmp);
		q3.copyTo(q0);
		tmp.copyTo(q3);
		q1.copyTo(tmp);
		q2.copyTo(q1);
		tmp.copyTo(q2);*/
		
		//magnitude.convertTo(magnitude, CvType.CV_8UC1);
		//Core.normalize(magnitude, magnitude,0,255, Core.NORM_MINMAX, CvType.CV_8UC1);
		q0M.convertTo(q0M, CvType.CV_8UC1);
		Core.normalize(q0M, q0M,0,255, Core.NORM_MINMAX, CvType.CV_8UC1);
		Mat q0MFull = new Mat();
		Size size = new Size(q0M.width()*2, q0M.height()*2);
		Imgproc.resize(q0M, q0MFull, size);
		return q0MFull;
	}
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
}
