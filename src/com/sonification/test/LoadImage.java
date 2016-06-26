package com.sonification.test;

import java.io.IOException;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Display;


public class LoadImage{

	Activity activity;
	public LoadImage(Activity activity) {
		this.activity = activity;
	}
	//load image from gallery
	public Bitmap loadImage(String path){
		
		Mat sampledImage;
		Mat originalImage = Highgui.imread(path);
		Mat rgbImage=new Mat();
		Imgproc.cvtColor(originalImage, rgbImage, Imgproc.COLOR_BGR2RGB);

		Display display  = activity.getWindowManager().getDefaultDisplay();
		
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		sampledImage = new Mat();
		double downSampleRatio= calculateSubSampleSize(rgbImage,width,height);
		Imgproc.resize(rgbImage, sampledImage, new Size(),downSampleRatio,downSampleRatio,Imgproc.INTER_AREA);
		try {
			ExifInterface exif = new ExifInterface(path);
			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,1);
			switch (orientation)
			{
			case ExifInterface.ORIENTATION_ROTATE_90:
			//get the mirrored image
			sampledImage=sampledImage.t();
			//flip on the y-axis
			Core.flip(sampledImage, sampledImage, 1);
			break;
			case ExifInterface.ORIENTATION_ROTATE_270:
			//get up side down image
			sampledImage=sampledImage.t();
			//Flip on the x-axis
			Core.flip(sampledImage, sampledImage, 0);
			break;
			}
			} catch (IOException e) {
			e.printStackTrace();
			}
		return convertToBitmapImage(sampledImage);
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
