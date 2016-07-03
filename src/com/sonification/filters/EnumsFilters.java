package com.sonification.filters;

public class EnumsFilters {
	public enum FilterName{
		AVERAGE, GAUSSIAN, CANNY, GRAY, HP1, LAPLACIAN, MEANREMOVAL, MEDIAN, SOBEL, ORIGINAL;
	}
	FilterName filterName;
	
	public  EnumsFilters(FilterName filterName){
		this.filterName = filterName;
	}
}
