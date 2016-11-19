package it.itjustworks.emergencybot.utilities;

public class Utils {

	public static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch(Exception e){
			return false;
		}
		return true;
	}

}
