package it.itjustworks.emergencybot.commands;

public class CreditsCommand implements Command {

	@Override
	public String execute(String message) {
		String output = "";
		output += "Developed by @therickys93, co-founder of 'It Just Works'. More info at www.itjustworks.it.";
		return output;
	}

}
