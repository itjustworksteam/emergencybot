package it.itjustworks.emergencybot.utilities;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.pengrad.telegrambot.model.Message;

import it.itjustworks.emergencybot.Location.City;
import it.itjustworks.emergencybot.Location.CityServiceImpl;

public class ServiceRequest {
		
	public static String emergencyServerUrl(){
		if(System.getenv("EMERGENCY_URL") != null) {
			return System.getenv("EMERGENCY_URL");
		}
		return "https://emergency-server.herokuapp.com";
	}
	
	public String executeWithMessage(Message message) throws UnexpectedInputException, ParseException, NumberFormatException, Exception{
		String latitude = message.location().latitude().toString();
		String longitude = message.location().longitude().toString();
		City city = new CityServiceImpl().findByLatLong(Double.parseDouble(latitude), Double.parseDouble(longitude));
		/*
		Country country = Country.parse(
				new it.itjustworks.emergency.Emergency().withBackEndUrl(emergencyServerUrl()).sendRequest(
						new Numbers().withCountry(city.getCountrycode())
						), it.itjustworks.emergency.Utils.botLanguage(message.from().languageCode())
				);
		*/
		Numbers numbers = new Numbers();
		Country country = numbers.getCountry(city.getCountrycode().toUpperCase());
		country.setCity(city.getAsciiname());
		Emergency emergency = new Emergency(country.prettyToString(), country.getPolice(), country.getFire(), country.getMedical());
		emergency.addCity(city.getAsciiname());
		return emergency.toJSON();
	}
	
}
