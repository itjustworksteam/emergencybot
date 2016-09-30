package it.itjustworks.emergencybot.utilities;

import it.itjustworks.emergencybot.JSONObject;
import it.itjustworks.emergencybot.JSONParser;

public class Country {
	
	private static final String LONGITUDE = "longitude";
	private static final String LATITUDE = "latitude";
	private static final String CITY = "city";
	private static final String COUNTRYCODE = "countrycode";
	private String jsonString;
	private String countryCode;
	private String city;
	private Double latitude;
	private Double longitude;
	
	public Country(String string) {
		this.jsonString = string;
		createCountry();
	}
	
	private void createCountry(){
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(this.jsonString);
			JSONObject jsonCountry = (JSONObject)obj;
			this.countryCode = (String)jsonCountry.get(COUNTRYCODE);
			this.city = (String)jsonCountry.get(CITY);
			this.latitude = (Double)jsonCountry.get(LATITUDE);
			this.longitude = (Double)jsonCountry.get(LONGITUDE);
		} catch (Exception e){
			this.countryCode = null;
		}
	}

	@Override
	public String toString() {
		String output = "";
		output += "Country: countrycode = "
				+ this.countryCode
				+ " city = "
				+ this.city
				+ " latitude = "
				+ this.latitude
				+ " longitude = "
				+ this.longitude
				+ " ";
		return output;
	}

	public String getCode() {
		return this.countryCode;
	}

	public String getClosestCity() {
		return this.city;
	}

}
