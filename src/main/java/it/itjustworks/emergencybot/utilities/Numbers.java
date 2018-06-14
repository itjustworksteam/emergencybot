package it.itjustworks.emergencybot.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Numbers {

	private List<Country> countries;
	
	public Numbers() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("src/main/resources/numbers.json"));
		String content = scanner.useDelimiter("\\Z").next();
		scanner.close();
		this.countries = new ArrayList<Country>();
		JsonParser parser = new JsonParser();
		JsonArray numbers = parser.parse(content).getAsJsonArray();
		Country country = null;
		for(int i = 0; i < numbers.size(); i++){
			JsonObject number = numbers.get(i).getAsJsonObject();
			String name = number.get("name").getAsString();
			String code = number.get("code").getAsString();
			String police = number.get("police").getAsString();
			String medical = number.get("medical").getAsString();
			String fire = number.get("fire").getAsString();
			country = new Country();
			country.setCode(code);
			country.setFire(fire);
			country.setMedical(medical);
			country.setName(name);
			country.setPolice(police);
			this.countries.add(country);
		}
	}
	
	public Country getCountry(String countryCode){
		for(int i = 0; i < this.countries.size(); i++){
			if(countryCode.equals(this.countries.get(i).getCode())){
				return this.countries.get(i);
			}
		}
		return null;
	}
	
}
