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
	
	@Test
	public void testJSONValid() {
		assertFalse(Utils.isJSONValid("hello"));
		assertFalse(Utils.isJSONValid("115"));
		String validJsonString = "{ \"developers\": [{ \"firstName\":\"Linus\" , \"lastName\":\"Torvalds\" }, " +
		        "{ \"firstName\":\"John\" , \"lastName\":\"von Neumann\" } ]}";
		String invalidJsonString = "\"developers\": [ \"firstName\":\"Linus\" , \"lastName\":\"Torvalds\" }, " +
		        "{ \"firstName\":\"John\" , \"lastName\":\"von Neumann\" } ]}";
		assertTrue(Utils.isJSONValid(validJsonString));
		assertFalse(Utils.isJSONValid(invalidJsonString));

	}
	
}
