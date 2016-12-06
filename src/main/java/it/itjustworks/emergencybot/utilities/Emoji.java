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

	public static String hello() {
		return EmojiParser.parseToUnicode(":wave:");
	}

	public static String sos() {
		return EmojiParser.parseToUnicode(":sos:");
	}

	public static String location() {
		return EmojiParser.parseToUnicode(":round_pushpin:");
	}

	public static String withCountry(String country) {
		return EmojiParser.parseToUnicode(":"+ country.toLowerCase() +":" );
	}

	public static String below() {
		return EmojiParser.parseToUnicode(":point_down:");
	}

}
