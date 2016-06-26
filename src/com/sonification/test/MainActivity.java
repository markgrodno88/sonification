package com.sonification.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import com.example.proj.R;
import com.sonification.filters.*;
import com.sonification.image_operations.HistogramRGB;
import com.sonification.image_operations.ImageOperations;
import com.sonification.parameters.OutputDate;
import com.sonification.parameters.OutputInformations;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.media.session.IMediaSession;
import android.util.Log;
import android.util.TimeUtils;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{
	private String s = "hoh";
	private static final int SELECT_PICTURE = 1;
	private static final String  TAG   = "Load_and_manipulation";
	//iView i textView do wyswietlania wynikow
	private ImageView imageView, iv, histogramRGBview, luminosityView;
	private TextView tv1, tv2, tv3;
	//Bitmapy do ladowania obrazu
	private Bitmap bMap, bitMap, bitMapHistogram, bitMapHistogramLuminosity;
	//Obiekt globalny
	private OriginalMatSingleton originalMatSingleton;	
	/*Operacje na rysunkach, tu jest wszystko, Ze względu na przeniesienie Mat całkowicie na stronę aplikacji,
	dzielenie operacji na dwie klasy zrobiło sie trochę uciązliwe - połączyłęm wstępnie wszystko w ImageOperation.
	OutputInformations istnieje..na zapas..gdyby się coś pozmieniało.
	*/
	private ImageOperations imageOperations = new ImageOperations();
	//Obiekt do ładowania oraz ściezki
	private LoadImage li = new LoadImage(this);	
	private Uri selectedImageUri; 
	private String selectedImagePath;
	private HistogramRGB histogramRGB;
	//obiekt do wygenerowania tej listy
	private CalculateAllFilters calculateAllFilters = new CalculateAllFilters();
	//sama lista
	List <ImageFilter> listOfFilters;
	Gaussian g = new Gaussian();
	int stdX, stdY;
	NumberPicker np1, np2;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.originalImage);
		iv.setOnTouchListener(this);
		imageView = (ImageView) findViewById(R.id.roiImage);
		histogramRGB = new HistogramRGB();
		//histogramRGBview = (ImageView) findViewById(R.id.rgbHistogramImage);
		//luminosityView = (ImageView) findViewById(R.id.luminosityImage);
		tv1 = (TextView) findViewById(R.id.textviewFilteringTime);
		//tv2 = (TextView) findViewById(R.id.textviewRGBTime);
		//tv3 = (TextView) findViewById(R.id.textviewLuminosityTime);
		//uzupełniamy listę filtrów
		listOfFilters = calculateAllFilters.getListOfFilters();
		
        np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        
        np1.setMinValue(0);
        np1.setMaxValue(5);
        np1.setWrapSelectorWheel(false); 
        np2.setMinValue(0);
        np2.setMaxValue(5);
        np2.setWrapSelectorWheel(false); 
        
        np1.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				stdX = newVal;
			}
        });
        np2.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				stdY = newVal;
			}
        });
	 }
	//Zaladowanie obrazu z dysku
	public void onActivityResult(int requestCode, int resultCode, Intent data){
	    if (resultCode == RESULT_OK) {
		    if (requestCode == SELECT_PICTURE) {
		    	//create path
		    	selectedImageUri = data.getData();
		    	selectedImagePath = li.getPath(selectedImageUri);
		    	bMap = li.loadImage(selectedImagePath);
				iv.setImageBitmap(bMap);	
				originalMatSingleton = OriginalMatSingleton.getInstance();
				originalMatSingleton.setOriginalMat(bMap);
		    }
	    }
	}  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_openGallary) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent,"Select Picture"),SELECT_PICTURE);
			return true;
		/*Wybór filtrów	
		 * W wersji z obszarem podepne te wywołąnia pod gesty
		 */
		}else if(id==R.id.average_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(1).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(1).getName(),listOfFilters.get(1));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(1));
				item.setChecked(true);
			}
			return true;
		}else if(id==R.id.median_filter){	
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(2).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{

				originalMatSingleton.setCurretlyFilters(listOfFilters.get(2).getName(),listOfFilters.get(2));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(2));
				item.setChecked(true);
			}
			
			return true;
		}else if(id==R.id.gaussian_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(3).getName());
				item.setChecked(false);
			}else{
				//originalMatSingleton.setCurretlyFilters(listOfFilters.get(3).getName(),listOfFilters.get(3));
				g.setParametersOfFilter(stdX, stdY);
				originalMatSingleton.setCurretlyFilters(g.getName(),g);
				item.setChecked(true);
			}
			
			return true;
		}else if(id==R.id.mean_removal_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(4).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(4).getName(),listOfFilters.get(4));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(4));
				item.setChecked(true);
			}
			
			return true;
		}else if(id==R.id.hp1_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(5).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(5).getName(),listOfFilters.get(5));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(5));
				item.setChecked(true);
			}
			return true;
		}else if(id==R.id.canny_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(6).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(6).getName(),listOfFilters.get(6));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(6));
				item.setChecked(true);
			}
			return true;
		}else if(id==R.id.laplace_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(7).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(7).getName(),listOfFilters.get(7));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(7));
				item.setChecked(true);
			}
			return true;
		}else if(id==R.id.sobel_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(8).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(8));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(8).getName(),listOfFilters.get(8));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(8));
				item.setChecked(true);
			}
			return true;
		}else if(id==R.id.magnitude){
			//currentFilteredImage = listOfFilteredImage.get(8);
			return true;
		}else if(id==R.id.phase){
			//currentFilteredImage = listOfFilteredImage.get(9);
			return true;
		}else if(id==R.id.gray_filter){
			if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(9).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(9).getName(),listOfFilters.get(9));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(9));
				item.setChecked(true);
			}
			return true;
		}else if(id==R.id.histogram){
			if(item.isChecked()){
				item.setChecked(false);
			}else{
				item.setChecked(true);
				
			}
			
			return true;
		}else if(id==R.id.original){
			/*if(item.isChecked()){
				originalMatSingleton.deleteFilterFromMap(listOfFilters.get(0).getName());
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(0));
				item.setChecked(false);
			}else{
				originalMatSingleton.setCurretlyFilters(listOfFilters.get(0).getName(),listOfFilters.get(0));
				//originalMatSingleton.setCurretlyFilter(listOfFilters.get(9));
				item.setChecked(true);
			}*/
			return true;
		}else if(id==R.id.clearFilters){
			originalMatSingleton.clearCurrentlyFilters();
			return true;
		}/*else if(id==R.id.discreet_activity){
			Intent intent = new Intent(MainActivity.this, DiscreetHistograms.class);
		    startActivity(intent);
		}*/
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		/*tu nie ma skalowania z prawdziwego zdarzenia, wewnatrz metody wycinania roi sa warunki
		 *na sposób wycinania obszaru z oryginalnego rysunku w postaci Mat, aczkolwiek to zadziała
		 *jesli podasz poprawne/wyskalowane x i y, czyli x i y to będą współrzędne obrazu*/
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
	    //wspołrzędne dotknięcia
		float x = event.getX();
	    float y = event.getY();
	    //znormalizowanie
	    int matHeight = originalMatSingleton.getHeightOfOriginalMat();
	    int matWidth = originalMatSingleton.getWidthOfOriginalMat();
	    
	    float real_y = (y * matHeight) /iv.getHeight() ;
	    
	  //  Log.d("!!!***", ""+originalMatSingleton.getCurrentlyFilters().get(listOfFilters.get(3).getName()).getDimension());
	    
	    float norm_x = imageOperations.roundValue(x/matWidth);
	    float norm_y = imageOperations.roundValue(real_y/matHeight);
	    switch (event.getAction()) {
	    	//Przykładowo obsłużyłem tylko przeciąganie po ekranie
	    	case MotionEvent.ACTION_MOVE: 
	  	    	//wycinanie obszaru zainteresowania do wyswietlenia w malym oknie
	    		//nie zmieniam podwójnego odwoływania się do imageOperations, bo w końcowej aplikacji nie będzie tego fragmentu z wyswietlaniem
	    		long start=System.currentTimeMillis();
	  		    long stopFiltering;
	  		    Mat roiMat = imageOperations.getMultiFilteredMat(imageOperations.getRoiOfOriginalMat(norm_x, norm_y));
	    		bitMap = imageOperations.convertToBitmapImage(roiMat);
	    														
	  	    	//ustawienie obrazu wycietego w danym oknie
	  	    	imageView.setImageBitmap(bitMap);

	  	    	/*Przykłąd pobrania 12 parametrów: 
	  	    		0 - wartość średnia: amplituda 0-1, częstotliwość narazie 1
	  	    		1 - odchylenie: amplituda 0-1, częstotliwość jak powyżej
	  	    		2-11 - dodałem jako wartość średnią
	  	    		Zakładając, że czas obliczenia częstotliwośći będzie porównywalny obliczenia amplitudy, to wymagane będzie obliczanie w oddzielnych wątkach
	  	    	*/	 
	  	    	Log.d(TAG, ""+imageOperations.getListOfParameters(norm_x, norm_y));
	  	    	/*Powyżej przykład wywołania listy parametrów i to jest jedyna metoda z klasy ImageOperations z której w wersji końcowej aplikacji będziesz korzystała
	  	    	Poprawiłem tak abys podawała tylko x i y bez filtru*/
	  	    	
	  	    	stopFiltering=System.currentTimeMillis();
	  	    	//Czas operacji pokazywany w textView
	  	    	tv1.setText("Filtering:"+(stopFiltering-start)+"ms");
	  	    	/*
	  	    	long startRGB=System.currentTimeMillis();
	    		bitMapHistogram = imageOperations.convertToBitmapImage(histogramRGB.drawHistogramRGB(roiMat, 256, 256));
	  	    	histogramRGBview.setImageBitmap(bitMapHistogram);
	    		long stopRGB=System.currentTimeMillis();
	  	    	tv2.setText("RGB:"+(stopRGB-startRGB)+"+ms");
	  	    
	  	    	long startLuminosity=System.currentTimeMillis();
	  	    	bitMapHistogramLuminosity = imageOperations.convertToBitmapImage(histogramRGB.drawHistogramLuminosity(roiMat, 256, 256 ));
	  	    	luminosityView.setImageBitmap(bitMapHistogramLuminosity);
	  	    	long stopLuminosity=System.currentTimeMillis();
	  	    	tv3.setText("Luminosity:"+(stopLuminosity-startLuminosity)+"+ms");
	  	    	*/
	  	    break;
	    }	    	    
	    return true;
	}
}
