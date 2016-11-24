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
		Message message = createMessageWithText("/hello");
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
		assertEquals(invalidCommandResponse(), answer);
	}
		
	@Test
	public void testContributeCommand() {
		Message message = createMessageWithText("/contribute");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(contributeResponse(), answer);
	}
	
	@Test
	public void testFeedbackCommand() {
		Message message = createMessageWithText("/feedback");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(feedbackResponse(), answer);
	}
	
	@Test
	public void testContactCommand() {
		Message message = createMessageWithText("/contact_115");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals("115", answer);
	}
	
	@Test
	public void testHelpButtonPressed() {
		Message message = createMessageWithText("HELP");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(helpResponse(), answer);
	}
	
	@Test
	public void testFeedbackButtonPressed() {
		Message message = createMessageWithText("FEEDBACK");
		String answer = new BotResponse.Builder().build().reply(message);
		assertEquals(feedbackResponse(), answer);
	}
	
	private String feedbackResponse() {
		String output = "";
		output += "If you have a question or you need an help. Please use our support bot: @itjustworksbot.\n"
				+ "We will reply you as soon as possible!";
		return output;
	}
		
	private String contributeResponse() {
		String output = "";
		output += "If you are a developer please follow this link: https://github.com/itjustworksteam/emergencybot/blob/master/README.md\n\n"
				+ "if you have any suggestion please use the /feedback command.\nThanks so much for your help!\n";
		return output;
	}
	
	private String creditsCommandResponse() {
		String output = "";
		output += "Developed by @therickys93, co-founder of 'It Just Works'. More info at www.itjustworks.it.";
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
				+ "/contribute - see what you can do to improve the bot quality.\n"
				+ "/feedback - say us what you think.\n";
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
		
	private Update createUpdateWithResponse(String response){
		Update update = BotUtils.parseUpdate(response);
		if (update.updateId() != null) {
			return update;
		} else {
			return null;
		}
	}
			
	private String createTelegramResponseWithMessage(String message) {
		String output = "";
		if(message.contains("/")) {
			output += "{\"update_id\":567305119,\"message\":{\"message_id\":599,"
					+ "\"from\":{\"id\":12345678,\"first_name\":\"Riccardo\",\"last_name\":\"Crippa\","
					+ "\"username\":\"therickys93\"},\"chat\":{\"id\":12345678,"
					+ "\"first_name\":\"Riccardo\",\"last_name\":\"Crippa\",\"username\":\"therickys93\"},"
					+ "\"date\":1439275732,\"text\":\"\\" + message + "\"}}";
		} else {
			output += "{\"update_id\":567305119,\"message\":{\"message_id\":599,"
					+ "\"from\":{\"id\":12345678,\"first_name\":\"Riccardo\",\"last_name\":\"Crippa\","
					+ "\"username\":\"therickys93\"},\"chat\":{\"id\":12345678,"
					+ "\"first_name\":\"Riccardo\",\"last_name\":\"Crippa\",\"username\":\"therickys93\"},"
					+ "\"date\":1439275732,\"text\":\"" + message + "\"}}";

		}
		return output;
	}
}
