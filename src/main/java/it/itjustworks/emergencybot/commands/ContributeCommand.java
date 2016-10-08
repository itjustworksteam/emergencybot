package it.itjustworks.emergencybot.commands;

public class ContributeCommand implements Command {

	@Override
	public String execute(String message) {
		String output = "";
		output += "If you are a developer please follow this link: https://github.com/itjustworksteam/emergencybot/blob/master/README.md\n\n"
				+ "if you have any suggestion please use the /feedback command.\nThanks so much for your help!\n";
		return output;
	}

}
