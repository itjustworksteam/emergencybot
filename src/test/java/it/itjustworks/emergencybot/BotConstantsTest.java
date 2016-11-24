package it.itjustworks.emergencybot;

import static org.junit.Assert.*;

import org.junit.Test;

import it.itjustworks.emergencybot.server.BotConstants;

public class BotConstantsTest {

	@Test
	public void testWrongTelegramServerToken() {
		assertEquals("Wrong Telegram Server token", BotConstants.TOKEN_ERROR);
	}
	
	@Test
	public void testGetResponseError() {
		assertEquals("See the chat on Telegram for more details", BotConstants.GET_RESPONSE_ERROR);
	}
	
	@Test
	public void testParseUpdateError() {
		assertEquals("I cannot parse the update", BotConstants.PARSE_UPDATE_ERROR);
	}
	
	@Test
	public void testErrorAction() {
		assertEquals("If you see this message please see /credits and inform the developer about it. Thanks", BotConstants.ACTION_ERROR);
	}
	
	@Test
	public void testConnectionFailed() {
		assertEquals("Error 1: Connection Failed.\nIf you see this message"
				+ " please see /credits and inform the developer about it. Thanks", BotConstants.CONNECTION_FAILED);
	}
	
	@Test
	public void testGettingCountryError() {
		assertEquals("Error 2: Getting Country Error.\nIf you see this message"
				+ " please see /credits and inform the developer about it. Thanks", BotConstants.GETTING_COUNTRY_ERROR);
	}
	
	@Test
	public void testGettingEmergencyError() {
		assertEquals("Error 3: Getting Emergency Error.\nIf you see this message"
				+ " please see /credits and inform the developer about it. Thanks", BotConstants.GETTING_EMERGENCY_ERROR);
	}
	
	@Test
	public void testMaintainance() {
		assertNotNull(BotConstants.UPGRADE);
	}
	
	@Test
	public void testMaintainanceResponse() {
		assertEquals("The bot is in maintainance!\nPlease retry later.\nWe apologize for the inconvenience.\n", BotConstants.MAINTAINANCE_MESSAGE);
	}
	
	@Test
	public void testSendYourLocationButtonText() {
		assertEquals("SEND YOUR LOCATION", BotConstants.LOCATION_BUTTON);
	}
	
	@Test
	public void testHelpButtonText() {
		assertEquals("HELP", BotConstants.HELP_BUTTON);
	}
	
	@Test
	public void testFeedbackButtonText() {
		assertEquals("FEEDBACK", BotConstants.FEEDBACK_BUTTON);
	}
}
