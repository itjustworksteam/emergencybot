package it.itjustworks.emergencybot.commands;

public class InvalidCommand implements Command {

	@Override
	public String execute(String message) {
		String output = "";
		output += "Invalid command. Please see /help for more details.";
		return output;
	}

}
