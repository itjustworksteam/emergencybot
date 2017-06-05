package it.itjustworks.emergencybot.commands;

import java.util.HashMap;

import com.pengrad.telegrambot.model.Message;

import it.itjustworks.emergencybot.server.BotConstants;
import it.itjustworks.emergencybot.utilities.ServiceRequest;

public class BotResponse {

private HashMap<String, Command> commands = new HashMap<String, Command>();
	
	public static class Builder {
		protected HashMap<String, Command> commands = new HashMap<String, Command>();
		
		public Builder() {
			commands.put("/start", new StartCommand());
			commands.put("/help", new HelpCommand());
			commands.put("/credits", new CreditsCommand());
			commands.put("/contribute", new ContributeCommand());
			commands.put("/feedback", new FeedbackCommand());
			commands.put("/contact", new ContactCommand());
			commands.put("HELP", new HelpCommand());
			commands.put("FEEDBACK", new FeedbackCommand());
		}
		
		public BotResponse build(){
			return new BotResponse(this);
		}

		public Builder withCommands(HashMap<String,Command> commands) {
			this.commands = commands;
			return this;
		}
	}
	
	private BotResponse(Builder build) {
		this.commands = build.commands;
	}
	
	public String reply(Message message) {
		if(message.location() != null) {
			try {
				return new ServiceRequest().executeWithMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
				return BotConstants.CONNECTION_FAILED;
			}
		} else {
			CommandParser c = new CommandParser(message.text());
			String command = c.getCommand();
			String argument = c.getArgument();
			String response = "";
			if(commands.containsKey(command)){
				response = commands.get(command).execute(argument, message.from().languageCode());
			} else {
				response = new InvalidCommand().execute(argument, message.from().languageCode());
			}
			return response;
		}
	}
	
}
