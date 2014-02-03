package unice.dam.koubi.lebourblanc;

import java.text.DecimalFormat;

public final class Utils {
	
	public static String getDistance(double distance){
		
		if(distance > 999){
			distance = distance / 1000;
			return String.format("%.1f", distance) + " km";
		}
		else{
			return String.format("%3f", distance) + " m";
		}
		
	}

}
