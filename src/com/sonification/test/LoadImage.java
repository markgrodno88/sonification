package com.sonification.test;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;


public class LoadImage{
	Activity activity;
	public LoadImage(Activity activity) {
		this.activity = activity;
	}
	public Mat load(String path){
		Mat originalImage = Highgui.imread(path);
		Mat rgbImage = new Mat();
		Log.d("LoadChannels1",""+originalImage.channels());
		Imgproc.cvtColor(originalImage, rgbImage, Imgproc.COLOR_BGR2RGB);
		Log.d("LoadChannels2",""+rgbImage.channels());
		return rgbImage;
	}
	//load image from gallery
	public Bitmap loadImage(Mat rgbImage){
		return convertToBitmapImage(rgbImage);
	}
	//calc size of Mat
	private static double calculateSubSampleSize(Mat srcImage, int reqWidth,int reqHeight) {
		// Raw height and width of image
		final int height = srcImage.height();
		final int width = srcImage.width();
		double inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			// Calculate ratios of requested height and width to the raw
			//height and width
			final double heightRatio = (double) reqHeight / (double) height;
			final double widthRatio = (double) reqWidth / (double) width;
			// Choose the smallest ratio as inSampleSize value, this will
			//guarantee final image with both dimensions larger than or
			//equal to the requested height and width.
			inSampleSize = heightRatio<widthRatio ? heightRatio :widthRatio;
		}
		return inSampleSize;
	}
	//convert Mat to BitMat 
	public Bitmap convertToBitmapImage(Mat image){
		Bitmap bitMap = Bitmap.createBitmap(image.cols(),image.rows(),Bitmap.Config.RGB_565);
		Utils.matToBitmap(image, bitMap);
		return bitMap;
	}
	//calulate path to image in gallery
	public String getPath(Uri uri) {
		if(uri == null ) {
			return null;
		}
		// try to retrieve the image from the media store first
		// this will only work for images selected from gallery
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = activity.getContentResolver().query(uri, projection, null, null,null);
		if(cursor != null ){
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		return uri.getPath();
	}
}
