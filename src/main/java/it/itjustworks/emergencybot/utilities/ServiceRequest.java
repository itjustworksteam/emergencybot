package it.itjustworks.emergencybot.utilities;

import java.io.IOException;

import com.pengrad.telegrambot.model.Location;

import it.itjustworks.emergency.Country;
import it.itjustworks.emergency.Numbers;

public class ServiceRequest {
		
	public static String emergencyServerUrl(){
		if(System.getenv("EMERGENCY_URL") != null) {
			return System.getenv("EMERGENCY_URL");
		}
		return "https://emergency-server.herokuapp.com";
	}
	
	public String executeWithLocation(Location location) throws IOException{
		String latitude = location.latitude().toString();
		String longitude = location.longitude().toString();
		Country country = Country.parse(
				new it.itjustworks.emergency.Emergency().withBackEndUrl(emergencyServerUrl()).sendRequest(
						new Numbers().withLatitudeAndLongitude(latitude, longitude)
						)
				);
		Emergency emergency = new Emergency(country.name(), country.code(), country.city(), country.police(), country.fire(), country.medical());
		return emergency.toJSON();
	}
	
}
