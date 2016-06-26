package com.sonification.filters;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Size;

public interface ImageFilter{
	public Mat createFiltr(Mat originalImage);
	public String getName();
	public double getDimension();
}
