package it.itjustworks.emergencybot;

import static org.junit.Assert.*;
import org.junit.Test;

import it.itjustworks.emergencybot.utilities.Emergency;

public class EmergencyTest {

	@Test
	public void testContructor() {
		new Emergency(null);
	}
	
	@Test
	public void testImportantMethods() {
		Emergency emergency = new Emergency(emergencyResponse());
		assertEquals("115", emergency.getFireNumber());
		assertEquals("113", emergency.getPoliceNumber());
		assertEquals("118", emergency.getMedicalNumber());
		assertEquals("Italy", emergency.getCountryName());
	}
	
	@Test
	public void testToString() {
		Emergency emergency = new Emergency(emergencyResponse());
		assertEquals(emergencyToString(), emergency.toString());
	}
	
	@Test
	public void testBeautifulToString() {
		Emergency emergency = new Emergency(emergencyResponse());
		assertEquals(beautifulToString(), emergency.prettyToString());
	}
	
	private String beautifulToString() {
		String output = "";
		output += "You are in Italy.\n\n"
				+ "Fire: /contact_115.\n\n"
				+ "Police: /contact_113.\n\n"
				+ "Medical: /contact_118.\n";
		return output;
	}
	
	private String emergencyToString() {
		String output = "";
		output += "Emergency: ";
		output += "name = Italy ";
		output += "fire = 115 ";
		output += "police = 113 ";
		output += "medical = 118 ";
		return output;
	}
	
	// use this service https://github.com/therickys93/EmergencyAPI to retrieve the emergency numbers
	private String emergencyResponse() {
		String output = "";
		output += "{\"code\":\"IT\",\"fire\":\"115\","
				+ "\"police\":\"113\",\"name\":\"Italy\",\"medical\":\"118\"}";
		return output;
	}
}
