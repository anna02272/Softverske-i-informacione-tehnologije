package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	public static String clean(String str) {
		if(str==null)
			return "";
		return str;
	}
	
	public static boolean isInteger(String s){
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isDouble(String s){
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isBoolean(String s){
		try {
			Boolean.parseBoolean(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static String clean(Date d) {
		if (d == null)
			return "";
		return formatDate(d);
	}
	
	public static Date parseDate(String s) {
		if(s.equals(""))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		try {
			return formatter.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatDate(Date d) {
		if(d==null)
			return "";
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		return formatter.format(d);
	}
}
