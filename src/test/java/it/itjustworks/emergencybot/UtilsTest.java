package it.itjustworks.emergencybot;

import static org.junit.Assert.*;

import org.junit.Test;

import it.itjustworks.emergencybot.utilities.Utils;

public class UtilsTest {

	@Test
	public void testIsInteger() {
		assertTrue(Utils.isInteger("123"));
		assertFalse(Utils.isInteger("hello"));
	}
	
}
