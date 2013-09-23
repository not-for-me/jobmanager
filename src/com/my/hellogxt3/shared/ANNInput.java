package com.my.hellogxt3.shared;

import java.io.Serializable;

public class ANNInput implements Serializable {
	private static final long serialVersionUID = -6618748560326027147L;
	private String TimeStamp;
	private double avg_30s; // 30s 
	private double max;
	private double min;
	private double avg;

	public ANNInput(String timeStamp, double avg_30s, double max, double min,
			double avg) {
		super();
		TimeStamp = timeStamp;
		this.avg_30s = avg_30s;
		this.max = max;
		this.min = min;
		this.avg = avg;
	}

	public String getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}

	public double getAvg_30s() {
		return avg_30s;
	}

	public void setAvg_30s(double avg_30s) {
		this.avg_30s = avg_30s;
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

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public void printANNInput() {
		System.out.println(this.getTimeStamp() +  ", " +  
							this.getAvg_30s() +  ", " + 
							this.getMax() +  ", " + 
							this.getMin() + ", " +  
						this.getAvg());
	}

}
