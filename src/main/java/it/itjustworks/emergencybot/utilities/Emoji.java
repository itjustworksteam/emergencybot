package it.itjustworks.emergencybot.utilities;

import com.vdurmont.emoji.EmojiParser;

public class Emoji {

	public static String policeCar() {
		return EmojiParser.parseToUnicode(":police_car:");
	}

	public static String ambulanceCar() {
		return EmojiParser.parseToUnicode(":ambulance:");
	}

	public static String fireCar() {
		return EmojiParser.parseToUnicode(":fire_engine:");
	}

}
