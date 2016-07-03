package com.sonification.image_operations;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import android.util.Log;

public class SonificationWithFragmentation {
	
	public List<Mat> splitMat(Mat mat, int countOfMat){
		List<Mat> listOfMat = new ArrayList<>(countOfMat);
		int cols = mat.cols();
		int rows = mat.rows();
		int sqrtOfCount = (int)Math.sqrt(countOfMat);//obcina, ale w projekcie sa wartosci typu int(4, 9)
		int width = cols/sqrtOfCount;
		int height = rows/sqrtOfCount;
		int widthMod = cols%sqrtOfCount;
		int heightMod = rows%sqrtOfCount;
 		
		int wStart = 0, wEnd = -1, countW = 0;		
		for(int i = 0; i < sqrtOfCount; i++){	
			int hStart = 0, hEnd = -1, countH = 0, countCurrentlyH = 0;
			if(widthMod > 0){
				countW = 1;
			}else{
				countW=0;
			}
			wStart = wEnd+1;
			wEnd = wStart + (width-1) + countW;
			countCurrentlyH = heightMod;
			for(int j = 0; j < sqrtOfCount; j++){
				if(countCurrentlyH > 0){
					countH = 1;
				}else{
					countH=0;
				}
				hStart = hEnd+1;
				hEnd = hStart + (height-1) + countH;
				countCurrentlyH--;
				listOfMat.add(mat.submat(hStart, hEnd, wStart, wEnd));
			}
			widthMod--;		
		}		
		return listOfMat;		
	}
}
