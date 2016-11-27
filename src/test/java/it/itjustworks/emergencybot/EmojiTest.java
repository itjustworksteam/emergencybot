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

}
