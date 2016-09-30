package it.itjustworks.emergencybot.utilities;

import it.itjustworks.emergencybot.JSONObject;
import it.itjustworks.emergencybot.JSONParser;

public class Emergency {

	private static final String COUNTRY_NAME = "name";
	private static final String MEDICAL = "medical";
	private static final String POLICE = "police";
	private static final String FIRE = "fire";
	private String json;
	private String fire;
	private String police;
	private String medical;
	private String country;
	
	public Emergency(String json) {
		this.json = json;
		JSONParser parser = new JSONParser();
		try {
			Object o = parser.parse(this.json);
			JSONObject jsonResponse = (JSONObject)o;
			this.fire = (String)jsonResponse.get(FIRE);
			this.police = (String)jsonResponse.get(POLICE);
			this.medical = (String)jsonResponse.get(MEDICAL);
			this.country = (String)jsonResponse.get(COUNTRY_NAME);
		} catch (Exception e){
			this.country = null;
		}
	}
	

	@Override
	public String toString() {
		String output = "";
		output += "Emergency: ";
		output += "name = "
				+ this.country
				+ " ";
		output += "fire = "
				+ this.fire
				+ " ";
		output += "police = "
				+ this.police
				+ " ";
		output += "medical = "
				+ this.medical
				+ " ";
		return output;
	}


	public String getFireNumber() {
		return this.fire;
	}

	public String getPoliceNumber() {
		return this.police;
	}

	public String getMedicalNumber() {
		return this.medical;
	}

	public String getCountryName() {
		return this.country;
	}


	public String prettyToString() {
		String output = "";
		output += "You are in "
				+ this.country
				+ ".\n"
				+ "Fire: "
				+ this.fire
				+ ".\n"
				+ "Police: "
				+ this.police
				+ ".\n"
				+ "Medical: "
				+ this.medical
				+ ".\n";
		return output;
	}

}
