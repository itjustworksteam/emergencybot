package it.itjustworks.emergencybot.utilities;

import java.io.IOException;

import com.pengrad.telegrambot.model.Message;

import it.itjustworks.emergency.Country;
import it.itjustworks.emergency.Numbers;

public class ServiceRequest {
		
	public static String emergencyServerUrl(){
		if(System.getenv("EMERGENCY_URL") != null) {
			return System.getenv("EMERGENCY_URL");
		}
		return "https://emergency-server.herokuapp.com";
	}
	
	public String executeWithMessage(Message message) throws IOException{
		String latitude = message.location().latitude().toString();
		String longitude = message.location().longitude().toString();
		Country country = Country.parse(
				new it.itjustworks.emergency.Emergency().withBackEndUrl(emergencyServerUrl()).sendRequest(
						new Numbers().withLatitudeAndLongitude(latitude, longitude)
						), it.itjustworks.emergency.Utils.botLanguage(message.from().languageCode())
				);
		Emergency emergency = new Emergency(country.name(), country.code(), country.city(), country.police(), country.fire(), country.medical());
		return emergency.toJSON();
	}
	
}
