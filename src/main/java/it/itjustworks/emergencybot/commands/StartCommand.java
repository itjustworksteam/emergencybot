package it.itjustworks.emergencybot.commands;

import it.itjustworks.emergencybot.utilities.Emoji;

public class StartCommand implements Command {

	@Override
	public String execute(String message, String language) {
		String output = "";
		output += "Welcome! " + Emoji.hello() + " With this bot you can retrieve the "+ Emoji.sos() +" phone numbers of the country "
				+ "only by sending your location " + Emoji.location() + ".\nFor more please send /help or simply press the "
						+ "\"SEND YOUR LOCATION\" button " + Emoji.below() + "\n";
		return output;
	}

}
