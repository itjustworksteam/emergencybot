package it.itjustworks.emergencybot.commands;

import it.itjustworks.emergency.Utils;

public class FeedbackCommand implements Command {

	@Override
	public String execute(String message, String language) {
		return Utils.botLanguage(language).feedback();
	}

}
