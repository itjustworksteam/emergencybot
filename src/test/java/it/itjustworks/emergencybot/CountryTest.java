package it.itjustworks.emergencybot;

import static org.junit.Assert.*;
import org.junit.Test;

import it.itjustworks.emergencybot.utilities.Country;

public class CountryTest {
	
	@Test
	public void testConstructor() {
		new Country("");
	}
	
	@Test
	public void testImportantMethods() {
		Country country = new Country(locationResponse());
		assertEquals("IT", country.getCode());
		assertEquals("Seregno", country.getClosestCity());
	}
	
	@Test
	public void testErrorInParsing() {
		Country country = new Country(null);
		assertEquals(null, country.getCode());
		country = new Country("");
		assertEquals(null, country.getCode());
	}
	
	@Test
	public void testToString() {
		Country country = new Country(locationResponse());
		assertEquals(toStringResponse(), country.toString());
	}
	
	// use this service https://github.com/turgos/Location to retrive the information from latitude and longitude
	private String locationResponse() {
		String output = "";
		output += "{\"countrycode\":\"IT\",\"city\":\"Seregno\","
				+ "\"asciiname\":\"Seregno\",\"latitude\":45.65002,\"longitude\":9.20548,"
				+ "\"population\":42760,\"elevation\":222,"
				+ "\"timezone\":\"Europe/Rome\",\"modified\":\"2014-04-13\"}";
		return output;
	}
	
	private String toStringResponse() {
		String output = "";
		output += "Country: ";
		output += "countrycode = IT ";
		output += "city = Seregno ";
		output += "latitude = 45.65002 ";
		output += "longitude = 9.20548 ";
		return output;
	}
	
}
