package it.itjustworks.emergencybot.commands;

public class SuggestionCommand implements Command {

	@Override
	public String execute(String message) {
		String output = "";
		output += "If you have any suggestion or see a message error. Please contact me as soon as possible.\n"
				+ "Send me an email to: therickys93@gmail.com.\n"
				+ "Thanks so much for your help.";
		return output;
	}

}
