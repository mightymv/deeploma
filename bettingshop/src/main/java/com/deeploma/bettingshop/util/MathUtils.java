package com.deeploma.bettingshop.util;

public class MathUtils {
	
	
	/**
	 * Proverava da li je prosledjeni broj ima odredjeni broj decimala.
	 */
	public static boolean checkDecimals(double number, int noOfDecimals) {
		if (round(number, noOfDecimals) == number) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Zaokruzuje broj na proizovljan broj decimala.
	 */
	public static double round(double number, int noOfDecimals) {
		double multiplier = Math.pow(10, noOfDecimals);
		return Math.round(number * multiplier) / multiplier;
	}
	
	
	

}
