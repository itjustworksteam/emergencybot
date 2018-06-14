package it.itjustworks.emergencybot.utilities;

public class Country {

	private String police;
	private String name;
	private String code;
	private String medical;
	private String fire;
	private String city;
	
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMedical() {
		return medical;
	}
	public void setMedical(String medical) {
		this.medical = medical;
	}
	public String getFire() {
		return fire;
	}
	public void setFire(String fire) {
		this.fire = fire;
	}
	public String prettyToString() {
		String output = "";
		output += "You are in " + this.name + " " + Emoji.withCountry(this.code);
		return output;
	}
	
}
