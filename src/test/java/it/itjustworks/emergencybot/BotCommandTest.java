package it.itjustworks.emergencybot;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import it.itjustworks.emergencybot.commands.BotResponse;

public class BotCommandTest {

	@Test
	public void testStartCommand() {
		Message message = createMessageWithText("/start");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(startResponse(), answer);
	}
	
	@Test
	public void testStartOnStartup() {
		Message message = createMessageWithText("/start start");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(startResponse(), answer);
	}
	
	@Test
	public void testHelpCommand() {
		Message message = createMessageWithText("/help");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(helpResponse(), answer);
	}
	
	@Test
	public void testInvalidCommand() {
		Message message = createMessageWithText("hello");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(invalidCommandResponse(), answer);
	}
	
	@Test
	public void testCreditsCommand() {
		Message message = createMessageWithText("/credits");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(creditsCommandResponse(), answer);
	}
	
	@Test
	public void testRateCommand() {
		Message message = createMessageWithText("/rate");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(rateResponse(), answer);
	}
	
	@Test
	public void testSuggestionCommand() {
		Message message = createMessageWithText("/suggestion");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(suggestionResponse(), answer);
	}
	
	@Test
	public void testContributeCommand() {
		Message message = createMessageWithText("/contribute");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(contributeResponse(), answer);
	}
	
//	@Test
//	public void testLocation() {
//		Message message = createMessageWithLocation();
//		String answer = new BotResponse.Builder().build().reply(message);
//		assertNotEquals("", answer);
//	}
	
	private String contributeResponse() {
		String output = "";
		output += "If you are a developer please follow this link: https://github.com/itjustworksteam/emergencybot/blob/master/README.md\n\n"
				+ "if you have any suggestion please contact us using the /credits command.\nThanks so much for your help!\n";
		return output;
	}
	
	private String suggestionResponse() {
		String output = "";
		output += "If you have any suggestion or see a message error. Please contact me as soon as possible.\n"
				+ "Send me an email to: therickys93@gmail.com.\n"
				+ "Thanks so much for your help.";
		return output;
	}
	
	private String rateResponse() {
		String output = "";
		output += "If you like this bot please give it 5 stars.\n"
				+ "Follow this link: https://telegram.me/storebot?start=emergencynumbersbot\n"
				+ "Thank a lot!";
		return output;
	}
	
	private String creditsCommandResponse() {
		String output = "";
		output += "Developed by therickys93, co-founder of 'It Just Works'. More info at www.itjustworks.it.";
		return output;
	}
	
	private String invalidCommandResponse() {
		String output = "";
		output += "Invalid command. Please see /help for more details.";
		return output;
	}
	
	private String helpResponse() {
		String output = "";
		output += "This bot needs only your location to work.\n"
				+ "Click the 'clip' and then click on 'Location' to send your current location.\n"
				+ "The response is a list of emergency numbers of the country where you are at the moment.\n\n"
				+ "All the commands are:\n"
				+ "/start - start the chat.\n"
				+ "/help - show this message.\n"
				+ "/credits - find out who is the developer.\n"
				+ "/rate - rate this bot if you like it.\n"
				+ "/suggestion - suggest something that you want to be implemented.\n"
				+ "/contribute - see what you can do to improve the bot quality.\n";
		return output;
	}
	
	private String startResponse() {
		String output = "";
		output += "Welcome! With this bot you can retrieve the emergency phone numbers of the country "
				+ "only by sending your location.\nFor more please send /help\n";
		return output;
	}
	
	private Message createMessageWithText(String text){
		Message message = createUpdateWithResponse(createTelegramResponseWithMessage(text)).message();
		return message;
	}
	
//	private Message createMessageWithLocation() {
//		Message message = createUpdateWithResponse(telegramLocationResponse()).message();
//		return message;
//	}
	
	private Update createUpdateWithResponse(String response){
		Update update = BotUtils.parseUpdate(response);
		if (update.updateId() != null) {
			return update;
		} else {
			return null;
		}
	}
	
//	private String telegramLocationResponse() {
//		String output = "";
//		output += "{\"update_id\":273881691,\"message\":{\"message_id\":2,"
//				+ "\"from\":{\"id\":33409686,\"first_name\":\"Riccardo\",\"last_name\":\"Crippa\","
//				+ "\"username\":\"therickys93\"},\"chat\":{\"id\":33409686,\"first_name\":\"Riccardo\","
//				+ "\"last_name\":\"Crippa\",\"username\":\"therickys93\",\"type\":\"private\"},"
//				+ "\"date\":1473255603,\"location\":{\"latitude\":45.650327,\"longitude\":9.197960}}}";
//		return output;
//	}
		
	private String createTelegramResponseWithMessage(String message) {
		String output = "";
		output += "{\"update_id\":567305119,\"message\":{\"message_id\":599,"
				+ "\"from\":{\"id\":12345678,\"first_name\":\"Riccardo\",\"last_name\":\"Crippa\","
				+ "\"username\":\"therickys93\"},\"chat\":{\"id\":12345678,"
				+ "\"first_name\":\"Riccardo\",\"last_name\":\"Crippa\",\"username\":\"therickys93\"},"
				+ "\"date\":1439275732,\"text\":\"\\" + message + "\"}}";
		return output;
	}
}
