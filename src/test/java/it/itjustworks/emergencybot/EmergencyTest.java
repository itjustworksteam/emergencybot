package it.itjustworks.emergencybot;

import static org.junit.Assert.*;
import org.junit.Test;

import it.itjustworks.emergencybot.utilities.Emergency;

public class EmergencyTest {
	
			
	@Test
	public void testFromJson() {
		Emergency emergency = Emergency.fromJSON(emergencyToJSONResponse());
		assertEquals("/contact_115", emergency.getFireContact());
		assertEquals("/contact_113", emergency.getPoliceContact());
		assertEquals("/contact_118", emergency.getMedicalContact());
		assertEquals("You are in Italy 🇮🇹 and the closest city is Voghera", emergency.getCountry());
		assertNull(emergency.getCountryName());
		assertNull(emergency.getFireNumber());
		assertNull(emergency.getMedicalNumber());
		assertNull(emergency.getPoliceNumber());
		
	}
	
	private String emergencyToJSONResponse() {
		String output = "";
		output += "{\"message\": \"You are in Italy 🇮🇹 and the closest city is Voghera\", "
				+ "\"police\":\"/contact_113\", \"fire\":\"/contact_115\", \"medical\":\"/contact_118\"}";
		return output;
	}
}
