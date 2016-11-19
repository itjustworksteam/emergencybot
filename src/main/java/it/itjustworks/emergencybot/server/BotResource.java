package it.itjustworks.emergencybot.server;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendContact;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import it.itjustworks.emergencybot.commands.BotResponse;
import it.itjustworks.emergencybot.utilities.Utils;

public class BotResource extends ServerResource {
	
	@Post
	public Representation update(Representation data) throws IOException {
		
		final String token = getAttribute("token");
		if(!Config.INSTANCE.SERVER_TOKEN.equals(token)) {
			getLogger().warning(BotConstants.TOKEN_ERROR);
			setStatus(Status.CLIENT_ERROR_FORBIDDEN, BotConstants.TOKEN_ERROR);
			return null;
		}
		
		final Update update = BotUtils.parseUpdate(data.getText());
		if(update.updateId() == null) {
			getLogger().warning(BotConstants.PARSE_UPDATE_ERROR);
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST, BotConstants.PARSE_UPDATE_ERROR);
			return null;
		}
		
		getLogger().info(update.toString());
		
		Message message = update.message();
		Chat chat = update.message().chat();
		
		String answer = "";
		if(BotConstants.UPGRADE) {
			answer = BotConstants.MAINTAINANCE_MESSAGE;
		} else {
			answer = new BotResponse.Builder().build().reply(message);
		}
				
		final TelegramBot bot = TelegramBotAdapter.build(Config.INSTANCE.BOT_TOKEN);
		final SendResponse response;
		if(Utils.isInteger(answer)) {
			// send contact
			 response = bot.execute(new SendContact(chat.id(), answer, "contact"));
		} else {
			// send message
			response = bot.execute(new SendMessage(chat.id(), answer));
		}
		getLogger().info(response.toString());
		
		return null;
	}
		
	@Get
	public String ciao() {
		if(BotConstants.UPGRADE) {
			setStatus(Status.SERVER_ERROR_NOT_IMPLEMENTED);
			return null;
		}
		getLogger().warning(BotConstants.GET_RESPONSE_ERROR);
		setStatus(Status.CLIENT_ERROR_BAD_REQUEST, BotConstants.GET_RESPONSE_ERROR);
		return null;
	}
}
