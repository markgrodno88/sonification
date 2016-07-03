package com.sonification.image_operations;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

import com.sonification.test.ConstantValue;
import com.sonification.test.Fragmentation;
import com.sonification.test.OriginalMatSingleton;

public class SonificationWithFragmentation {
	
	public List<Mat> splitMat(Mat mat, Fragmentation fragmentation){
		int countOfMat = 0;
		if(fragmentation.equals(Fragmentation.FOUR)){
			countOfMat = 4;
		}else if(fragmentation.equals(Fragmentation.NINE)){
			countOfMat = 9;
		}
		List<Mat> listOfMat = new ArrayList<>(countOfMat);
		int cols = mat.cols();
		int rows = mat.rows();
		int sqrtOfCount = (int)Math.sqrt(countOfMat);
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
	
	public List<Float> calculateFrequenciesOfFragments(List<Float> dominantFrequncies, Fragmentation fragmentation){
		List <Float> listOfFragmentFrequencies = null; 
		if(fragmentation.equals(Fragmentation.FOUR)){
			listOfFragmentFrequencies = new ArrayList<>(4);
			listOfFragmentFrequencies.add((dominantFrequncies.get(0) * ConstantValue.FREQUENCY_C_PRIM_SOUND_HZ) / 
										  (ConstantValue.FREQUENCY_NORMALISATION_HZ * ConstantValue.FREQUENCY_A_SOUND_HZ));
			listOfFragmentFrequencies.add((dominantFrequncies.get(1) * ConstantValue.FREQUENCY_C_SOUND_HZ) / 
										  (ConstantValue.FREQUENCY_NORMALISATION_HZ * ConstantValue.FREQUENCY_A_SOUND_HZ));
			listOfFragmentFrequencies.add((dominantFrequncies.get(2) * ConstantValue.FREQUENCY_G_PRIM_SOUND_HZ) / 
					  					  (ConstantValue.FREQUENCY_NORMALISATION_HZ * ConstantValue.FREQUENCY_A_SOUND_HZ));
			listOfFragmentFrequencies.add((dominantFrequncies.get(3) * ConstantValue.FREQUENCY_G_SOUND_HZ) / 
					  					  (ConstantValue.FREQUENCY_NORMALISATION_HZ * ConstantValue.FREQUENCY_A_SOUND_HZ));
		}else if(fragmentation.equals(Fragmentation.NINE)){
			listOfFragmentFrequencies = new ArrayList<>(9);
		}		 
		return  listOfFragmentFrequencies;
	}
}
