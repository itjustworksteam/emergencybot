package it.itjustworks.emergencybot.commands;

public class HelpCommand implements Command {

	@Override
	public String execute(String message, String language) {
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

}
