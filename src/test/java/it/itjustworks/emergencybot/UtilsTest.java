package it.itjustworks.emergencybot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import com.pengrad.telegrambot.model.Message;

import it.itjustworks.emergencybot.utilities.ServiceRequest;
import it.itjustworks.emergencybot.utilities.Utils;

public class UtilsTest {

	@Rule
	public EnvironmentVariables variable = new EnvironmentVariables();
	
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
	
	@Test
	public void testCreateTelegramMessageWithoutCommand() {
		Message message = Utils.createMessageWithText("my message");
		assertEquals(Integer.valueOf(123), message.messageId());
		assertEquals(Integer.valueOf(1439275732), message.date());
		assertEquals("my message", message.text());
		assertEquals(Integer.valueOf(12345678), message.from().id());
		assertEquals("John", message.from().firstName());
		assertEquals("Doe", message.from().lastName());
		assertEquals("johndoe", message.from().username());
		assertEquals(Long.valueOf(12345678), message.chat().id());
		assertEquals("John", message.chat().firstName());
		assertEquals("Doe", message.chat().lastName());
		assertEquals("johndoe", message.chat().username());
	}
	
	@Test
	public void testCreateTelegramMessageWithCommand() {
		Message message = Utils.createMessageWithText("/command");
		assertEquals(Integer.valueOf(123), message.messageId());
		assertEquals(Integer.valueOf(1439275732), message.date());
		assertEquals("/command", message.text());
		assertEquals(Integer.valueOf(12345678), message.from().id());
		assertEquals("John", message.from().firstName());
		assertEquals("Doe", message.from().lastName());
		assertEquals("johndoe", message.from().username());
		assertEquals(Long.valueOf(12345678), message.chat().id());
		assertEquals("John", message.chat().firstName());
		assertEquals("Doe", message.chat().lastName());
		assertEquals("johndoe", message.chat().username());
	}
	
	@Test
	public void testEmergencyServerUrl() {
		// EMERGENCY_URL
		assertEquals("https://emergency-server.herokuapp.com/api/v2/numbers/", ServiceRequest.emergencyServerUrl());
		variable.set("EMERGENCY_URL", "http://emergencyserver.com");
		assertEquals("http://emergencyserver.com", ServiceRequest.emergencyServerUrl());
	}
	
}
