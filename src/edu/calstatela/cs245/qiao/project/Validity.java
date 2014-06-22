package edu.calstatela.cs245.qiao.project;

public class Validity {
	
	public static boolean isInt(String s){
		try {
			Integer.valueOf(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isDouble(String s){
		try {
			Double.valueOf(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
