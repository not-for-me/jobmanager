package com.my.hellogxt3.shared;

import java.io.Serializable;

public class HMMInput implements Serializable {
	private static final long serialVersionUID = 3975830862158783477L;
	private String TimeStamp;
	private double avg_30s; // 30s 
	private double max;
	private double min;

	public HMMInput(String timeStamp, double avg, double max, double min) {
		super();
		TimeStamp = timeStamp;
		this.avg_30s = avg;
		this.max = max;
		this.min = min;
	}
	
	public String getTimeStamp() {
		return TimeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	
	public double getAvg() {
		return avg_30s;
	}
	
	public void setAvg(double avg) {
		this.avg_30s = avg;
	}
	
	public double getMax() {
		return max;
	}
	
	public void setMax(double max) {
		this.max = max;
	}
	
	public double getMin() {
		return min;
	}
	
	public void setMin(double min) {
		this.min = min;
	}

	public void printHMMInput() {
		System.out.println(this.getTimeStamp() + ", " + 
							this.getAvg() +  ", " + 
							this.getMax() +  ", " +  
							this.getMin());
	}
}
