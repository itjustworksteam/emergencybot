package it.itjustworks.emergencybot.utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Emergency {

	private static final String CODE = "code";
	private static final String MESSAGE = "message";
	private static final String COUNTRY_NAME = "name";
	private static final String MEDICAL = "medical";
	private static final String POLICE = "police";
	private static final String FIRE = "fire";
	private String json;
	private String fire;
	private String police;
	private String medical;
	private String city;
	private String country;
	private String code;
	private String fireContact;
	private String policeContact;
	private String medicalContact;
	private String countryContact;
	
	public Emergency(String json) {
		this.json = json;
		JsonParser parser = new JsonParser();
		try {
			JsonObject object = parser.parse(this.json).getAsJsonObject();
			this.country = object.get(COUNTRY_NAME).getAsString();
			this.countryContact = "You are in " + this.country;
			this.fire = object.get(FIRE).getAsString();
			this.fireContact = "/contact_" + this.fire;
			this.police = object.get(POLICE).getAsString();
			this.policeContact = "/contact_" + this.police;
			this.medical = object.get(MEDICAL).getAsString();
			this.medicalContact = "/contact_" + this.medical;
			this.code = object.get(CODE).getAsString();	
			this.city = object.get("closestcity").getAsString();
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
				+ " "
				+ "city = " + this.city + " ";
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
				+ " and the closest city is " + this.city + ".\n\n"
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
				+ "\"You are in " + this.country +" "+ Emoji.withCountry(this.code) +" and the closest city is "+this.city+"\", "
				+ "\"police\":\"/contact_"+this.police+"\", "
				+ "\"fire\":\"/contact_"+this.fire+"\", "
				+ "\"medical\":\"/contact_"+this.medical+"\"}";
		return output;
	}


	public static Emergency fromJSON(String emergencyToJSONResponse) {
		JsonParser parser = new JsonParser();
		String fire;
		String medical;
		String police;
		String output;
		String city = "";
		try {
			JsonObject object = parser.parse(emergencyToJSONResponse).getAsJsonObject();
			output = object.get(MESSAGE).getAsString();
			fire = object.get(FIRE).getAsString();
			police = object.get(POLICE).getAsString();
			medical = object.get(MEDICAL).getAsString();
			//city = object.get("closestcity").getAsString();
			return new Emergency(output, fire, police, medical, city);
		} catch (Exception e){
			output = null;
		}
		return null;
	}
	

	private Emergency(String country, String fire, String police, String medical, String city) {
		this.countryContact = country;
		this.medicalContact = medical;
		this.policeContact = police;
		this.fireContact = fire;
		this.city = city;
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


	public String getCity() {
		return this.city;
	}

}
