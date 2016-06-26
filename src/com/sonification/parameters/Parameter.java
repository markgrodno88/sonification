package com.sonification.parameters;

public class Parameter {
	private float amplitude, frequency;
	public Parameter(float amplitude, float frequency) {
		this.amplitude = amplitude;
		this.frequency = frequency;
	}
	public void setAmplitude(float amplitude){
		this.amplitude = amplitude;
	}
	public void setFrequency(float frequency){
		this.frequency = frequency;
	}
	public float getAmplitude(){
		return amplitude;
	}
	public float getFrequency(){
		return frequency;
	}
	public String toString(){
		return "Amplitude:"+amplitude+", Freguency:"+frequency;
	}
}
