package it.itjustworks.emergencybot.commands;

public class RateCommand implements Command {

	@Override
	public String execute(String message) {
		String output = "";
		output += "If you like this bot please give it 5 stars.\n"
				+ "Follow this link: https://telegram.me/storebot?start=emergencynumbersbot\n"
				+ "Thank a lot!";
		return output;
	}

}
