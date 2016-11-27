package it.itjustworks.emergencybot;

import static org.junit.Assert.assertEquals;
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
	
	// TODO: da fare da qui in poi	
	@Test
	public void testHello() {
		assertEquals("👋", "");
	}
	
	@Test
	public void testSOS() {
		assertEquals("🆘", "");
	}
	
	@Test
	public void testLocation() {
		assertEquals("📍", "");
	}
	
	@Test
	public void testFlagOutput() {
		assertEquals("🇮🇹", "");
	}

}
