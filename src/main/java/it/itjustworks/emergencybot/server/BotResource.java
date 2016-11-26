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
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendContact;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import it.itjustworks.emergencybot.commands.BotResponse;
import it.itjustworks.emergencybot.utilities.Emergency;
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
		
		final TelegramBot bot = TelegramBotAdapter.build(Config.INSTANCE.BOT_TOKEN);
		Message message;
		Chat chat;
		if(update.message() != null){
			message = update.message();
			chat = update.message().chat();
		} else {
			bot.execute(new AnswerCallbackQuery(update.callbackQuery().id()));
			message = Utils.createMessageWithText(update.callbackQuery().data());
			chat = update.callbackQuery().message().chat();
		}
		bot.execute(new SendChatAction(chat.id(), ChatAction.typing.toString()));
		String answer = "";
		if(BotConstants.UPGRADE) {
			answer = BotConstants.MAINTAINANCE_MESSAGE;
		} else {
			answer = new BotResponse.Builder().build().reply(message);
		}
				
		final SendResponse response;
		final Keyboard keyboard = new ReplyKeyboardMarkup(
		        new KeyboardButton[]{
		                new KeyboardButton(BotConstants.LOCATION_BUTTON).requestLocation(true)
		        },
		        new KeyboardButton[] {
		                new KeyboardButton(BotConstants.HELP_BUTTON),
		                new KeyboardButton(BotConstants.FEEDBACK_BUTTON)
		        }
		).oneTimeKeyboard(false);
		if(Utils.isInteger(answer)) {
			// send contact
			response = bot.execute(new SendContact(chat.id(), answer, BotConstants.CONTACT_NAME).replyMarkup(keyboard));
		} else if(Utils.isJSONValid(answer)) {
			// send inline keyboard
			Emergency emergency = Emergency.fromJSON(answer);
			InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
			        new InlineKeyboardButton[]{
			                new InlineKeyboardButton(BotConstants.CONTACT_POLICE).callbackData(emergency.getPoliceContact()),
			        },
			        new InlineKeyboardButton[]{
			                new InlineKeyboardButton(BotConstants.CONTACT_FIRE).callbackData(emergency.getFireContact()),
			        },
			        new InlineKeyboardButton[]{
			                new InlineKeyboardButton(BotConstants.CONTACT_MEDICAL).callbackData(emergency.getMedicalContact())
			        }
			        );
			response = bot.execute(new SendMessage(chat.id(), emergency.getCountry()).replyMarkup(inlineKeyboard));
		} else {
			// send message
			response = bot.execute(new SendMessage(chat.id(), answer).replyMarkup(keyboard));
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
