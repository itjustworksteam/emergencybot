package it.itjustworks.emergencybot.commands;

public class FeedbackCommand implements Command {

	@Override
	public String execute(String message) {
		String output = "";
		output += "If you have a question or you need an help. Please use our support bot: @itjustworksbot.\n"
				+ "We will reply you as soon as possible!";
		return output;
	}

}
