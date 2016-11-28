package it.itjustworks.emergencybot.utilities;

import it.itjustworks.emergencybot.JSONObject;
import it.itjustworks.emergencybot.JSONParser;

public class Emergency {

	private static final String MESSAGE = "message";
	private static final String COUNTRY_NAME = "name";
	private static final String MEDICAL = "medical";
	private static final String POLICE = "police";
	private static final String FIRE = "fire";
	private String json;
	private String fire;
	private String police;
	private String medical;
	private String country;
	private String code;
	private String fireContact;
	private String policeContact;
	private String medicalContact;
	private String countryContact;
	
	public Emergency(String json) {
		this.json = json;
		JSONParser parser = new JSONParser();
		try {
			Object o = parser.parse(this.json);
			JSONObject jsonResponse = (JSONObject)o;
			this.fire = (String)jsonResponse.get(FIRE);
			this.fireContact = "/contact_" + this.fire;
			this.police = (String)jsonResponse.get(POLICE);
			this.policeContact = "/contact_" + this.police;
			this.medical = (String)jsonResponse.get(MEDICAL);
			this.medicalContact = "/contact_" + this.medical;
			this.country = (String)jsonResponse.get(COUNTRY_NAME);
			this.countryContact = "You are in " + this.country;
			this.code = (String)jsonResponse.get("code");
		} catch (Exception e){
			this.country = null;
		}
	}
	

	@Override
	public String toString() {
		String output = "";
		output += "Emergency: ";
		output += "name = "
				+ this.country + " " + Emoji.withCountry(this.code)
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
				+ this.country + " " + Emoji.withCountry(this.code)
				+ ".\n\n"
				+ "Fire: "
				+ "/contact_" + this.fire
				+ ".\n\n"
				+ "Police: "
				+ "/contact_" + this.police
				+ ".\n\n"
				+ "Medical: "
				+ "/contact_" + this.medical
				+ ".\n";
		return output;
	}


	public String toJSON() {
		String output = "";
		output += "{\"message\": "
				+ "\"You are in " + this.country +" "+ Emoji.withCountry(this.code) +"\", "
				+ "\"police\":\"/contact_"+this.police+"\", "
				+ "\"fire\":\"/contact_"+this.fire+"\", "
				+ "\"medical\":\"/contact_"+this.medical+"\"}";
		return output;
	}


	public static Emergency fromJSON(String emergencyToJSONResponse) {
		JSONParser parser = new JSONParser();
		String fire;
		String medical;
		String police;
		String output;
		try {
			Object o = parser.parse(emergencyToJSONResponse);
			JSONObject jsonResponse = (JSONObject)o;
			output = (String)jsonResponse.get(MESSAGE);
			fire = (String)jsonResponse.get(FIRE);
			police = (String)jsonResponse.get(POLICE);
			medical = (String)jsonResponse.get(MEDICAL);
			return new Emergency(output, fire, police, medical);
		} catch (Exception e){
			output = null;
		}
		return null;
	}
	

	private Emergency(String country, String fire, String police, String medical) {
		this.countryContact = country;
		this.medicalContact = medical;
		this.policeContact = police;
		this.fireContact = fire;
	}


	public String getFireContact() {
		return this.fireContact;
	}


	public String getPoliceContact() {
		return this.policeContact;
	}


	public String getMedicalContact() {
		return this.medicalContact;
	}


	public String getCountry() {
		return this.countryContact;
	}


	public String getCode() {
		return this.code;
	}

}
