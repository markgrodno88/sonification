package com.sonification.test;

public final class ConstantValue {
	public final static float RED_COEFFICIENT = 0.3f,
							  GREEN_COEFFICIENT = 0.59f,
							  BLUE_COEFFICIENT = 0.11f;
	public final static int HUE_INDEX = 0,
			 				SATURATION_INDEX = 1,
			 				VALUE_INDEX = 2,
			 				HSV_HUE = 360;
	public final static int COUNT_OF_LEVEL = 255;
	public final static int COUNT_ROUND = 100000;
	public final static int RED_INDEX = 0,
							GREEN_INDEX = 1,
							BLUE_INDEX = 2,
							GRAY_INDEX = 0;
	public final static float FREQUENCY_A_SOUND_HZ = 440.0f,
							  FREQUENCY_NORMALISATION_HZ = 44100.0f,
							  
							  FREQUENCY_C_SOUND_HZ = 261.6f,
			   				  FREQUENCY_E_SOUND_HZ = 329.6f,
			   				  FREQUENCY_G_SOUND_HZ = 392.0f,
			   				  
			   				  FREQUENCY_C_PRIM_SOUND_HZ = 523.3f,
			   				  FREQUENCY_E_PRIM_SOUND_HZ = 659.3f,
			   				  FREQUENCY_G_PRIM_SOUND_HZ = 784.0f,
					   				  
			   				  FREQUENCY_C_BIS_SOUND_HZ = 1046.5f,
							  FREQUENCY_E_BIS_SOUND_HZ = 1318.5f,
							  FREQUENCY_G_BIS_SOUND_HZ = 1568.0f;
}
