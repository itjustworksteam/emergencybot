package it.itjustworks.emergencybot.commands;

public class CommandParser {

	private String argument;
	private String command;
	
	public CommandParser(String message) {
		if(message != null) {
            if(message.contains(" ")) {
                command = message.split(" ",2)[0];
                argument = message.split(" ", 2)[1];
            } else if(message.contains("_")) {
            	command = message.split("_", 2)[0];
            	argument = message.split("_", 2)[1];
            } else {
                command = message;
                argument = "";
            }
        } else {
            command = "";
            argument = "";
        }
	}

	public String getCommand() {
		return this.command;
	}

	public String getArgument() {
		// TODO Auto-generated method stub
		return this.argument;
	}

}
