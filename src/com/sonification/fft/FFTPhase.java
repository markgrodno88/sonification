package com.sonification.fft;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import com.sonification.filters.ImageFilter;

public class FFTPhase /*implements ImageFilter*/{


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
		Mat phase = new Mat();
		Core.phase(splitted.get(0), splitted.get(1), phase);
		Core.add(Mat.ones(phase.size(), CvType.CV_32F), phase, phase);
		Core.log(phase, phase);
		int cx = phase.cols()/2;
		int cy = phase.rows()/2;
		Mat q0 = new Mat(phase,new Rect(0, 0, cx, cy));
		Mat q1 = new Mat(phase,new Rect(cx, 0, cx, cy));
		Mat q2 = new Mat(phase,new Rect(0, cy, cx, cy));
		Mat q3 = new Mat(phase,new Rect(cx, cy, cx, cy));
		Mat tmp = new Mat();
		q0.copyTo(tmp);
		q3.copyTo(q0);
		tmp.copyTo(q3);
		q1.copyTo(tmp);
		q2.copyTo(q1);
		tmp.copyTo(q2);
		phase.convertTo(phase, CvType.CV_8UC1);
		Core.normalize(phase, phase,0,255, Core.NORM_MINMAX, CvType.CV_8UC1);
		return phase;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

}
