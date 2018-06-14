package it.itjustworks.emergencybot.utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Emergency {

	private static final String MESSAGE = "message";
	private static final String MEDICAL = "medical";
	private static final String POLICE = "police";
	private static final String FIRE = "fire";
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
	private String prettyToString;

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
				+ "\"" + this.prettyToString + " and the closest city is " + this.city  +  "\", "
				+ "\"police\":\"/contact_"+this.police+"\", "
				+ "\"fire\":\"/contact_"+this.fire+"\", "
				+ "\"medical\":\"/contact_"+this.medical+"\"}";
		return output;
	}

	public void addCity(String city){
		this.city = city;
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
			return new Emergency(output, fire, police, medical, city);
		} catch (Exception e){
			output = null;
		}
		return null;
	}
	
	public Emergency(String prettyToString, String police, String fire, String medical){
		this.prettyToString = prettyToString;
		this.police = police;
		this.fire = fire;
		this.medical = medical;
	}
	
	public Emergency(String country, String code, String city, String police, String fire, String medical){
		this.country = country;
		this.code = code;
		this.city = city;
		this.police = police;
		this.fire = fire;
		this.medical = medical;
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
