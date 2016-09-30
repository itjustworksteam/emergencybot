package it.itjustworks.emergencybot.commands;

public class StartCommand implements Command {

	@Override
	public String execute(String message) {
		String output = "";
		output += "Welcome! With this bot you can retrieve the emergency phone numbers of the country "
				+ "only by sending your location.\nFor more please send /help\n";
		return output;
	}

}
