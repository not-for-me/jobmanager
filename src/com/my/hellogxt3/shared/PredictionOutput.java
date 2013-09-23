package com.my.hellogxt3.shared;

import java.io.Serializable;

public class PredictionOutput implements Serializable {

	private static final long serialVersionUID = -547098860085917106L;
	private String timestamp;
	private double p_val;
	private double r_val;
	public PredictionOutput() {
		this.timestamp = "";
		this.p_val = 0;
		this.r_val = 0;
	}
	public PredictionOutput(String timestamp, double p_val, double r_val) {
		super();
		this.timestamp = timestamp;
		this.p_val = p_val;
		this.r_val = r_val;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public double getP_val() {
		return p_val;
	}
	public void setP_val(double p_val) {
		this.p_val = p_val;
	}
	public double getR_val() {
		return r_val;
	}
	public void setR_val(double r_val) {
		this.r_val = r_val;
	}

	public void printPredictionOutput() {
		System.out.println("\n----------------------------------------");
		System.out.println("Time: " + this.getTimestamp());
		System.out.println("R value: " + this.getR_val());
		System.out.println("P value: " + this.getP_val());	
		System.out.println("----------------------------------------\n");
	}
}
