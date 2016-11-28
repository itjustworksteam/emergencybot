package it.itjustworks.emergencybot;

import static org.junit.Assert.*;
import org.junit.Test;
import it.itjustworks.emergencybot.utilities.Emoji;

public class EmojiTest {
	
	@Test
	public void testPoliceCar() {
		assertEquals("🚓", Emoji.policeCar());
	}
	
	@Test
	public void testAmbulanceCar() {
		assertEquals("🚑", Emoji.ambulanceCar());
	}
	
	@Test
	public void testFireCar() {
		assertEquals("🚒", Emoji.fireCar());
	}
	
	@Test
	public void testHello() {
		assertEquals("👋", Emoji.hello());
	}
	
	@Test
	public void testSOS() {
		assertEquals("🆘", Emoji.sos());
	}
	
	@Test
	public void testLocation() {
		assertEquals("📍", Emoji.location());
	}
	
	@Test
	public void testFlagOutput() {
		String country = "it";
		assertEquals("🇮🇹", Emoji.withCountry(country));
	}
	
	@Test
	public void testFlagOutputWithUppercaseInput() {
		String country = "IT";
		assertEquals("🇮🇹", Emoji.withCountry(country));
	}
	
	@Test
	public void testAllFlags() {
		// removed states because they do not have a flag in emoji-java: td, gl, mu, mc, tw, va
		String[] countrycodes = {"AF","AL","DZ","AR","AM","AT","AU","BH",
				"BD","BB","BY","BE","BO","BA","BW","BR","BN","BG","CA","KY",
				"CL","CN","CO","CR","HR","CY","CZ","DK","DJ","DO","EC",
				"EG","EE","FO","FJ","FI","FR","GE","DE","GH","GI","GR","GT",
				"GY","HT","HN","HK","HU","IS","IN","ID","IR","IQ","IE","IL","IT",
				"JM","JP","JO","KZ","KW","LV","LB","LT","LU","MO","MK","MY","MV",
				"ML","MT","MX","MD","MN","ME","MA","MM","NP","NL","NZ","NI",
				"NG","KP","CY","NO","OM","PK","PA","PY","PE","PH","PL","PT","QA","RO","RU",
				"RW","SM","SA","RS","SL","SG","SK","SI","SB","ZA","KR","ES","LK","SD","SR",
				"SE","CH","SY","TJ","TH","TT","TN","TR","UG","UA","AE","GB","US","UY","VU",
				"VE","VN","ZM","ZW"};
		for(int i = 0; i < countrycodes.length; i++) {
			assertNotEquals(":" + countrycodes[i].toLowerCase() + ":", Emoji.withCountry(countrycodes[i]));
		}
	}
	
	@Test
	public void testFlagsOutputError() {
		String notACountryCode = "MI";
		assertEquals(":" + notACountryCode.toLowerCase() + ":", Emoji.withCountry(notACountryCode));
	}
	
	@Test
	public void missingFlags() {
		// td --> CHAD
		assertEquals(":td:", Emoji.withCountry("td"));
		// gl --> GREENLAND
		assertEquals(":gl:", Emoji.withCountry("gl"));
		// mu --> MAURITIUS
		assertEquals(":mu:", Emoji.withCountry("mu"));
		// mc --> MONACO
		assertEquals(":mc:", Emoji.withCountry("mc"));
		// tw --> TAIWAN
		assertEquals(":tw:", Emoji.withCountry("tw"));
		// va --> VATICAN CITY
		assertEquals(":va:", Emoji.withCountry("va"));
	}

}
