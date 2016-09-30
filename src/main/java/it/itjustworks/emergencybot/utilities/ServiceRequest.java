package it.itjustworks.emergencybot.utilities;

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.pengrad.telegrambot.model.Location;

import it.itjustworks.emergencybot.server.BotConstants;

public class ServiceRequest {
	
	public String executeWithLocation(Location location) throws Exception{
		Country country = getCountry("http://scatter-otl.rhcloud.com/location?lat="+ location.latitude().toString() + "&long=" + location.longitude().toString());
		if (country == null) {
			return BotConstants.GETTING_COUNTRY_ERROR;
		}
		Emergency emergency = getEmergency("https://emergency-phone-numbers.herokuapp.com/country/" + country.getCode());
		if(emergency == null) {
			return BotConstants.GETTING_EMERGENCY_ERROR;
		}
		return emergency.prettyToString();
	}
	
	private Country getCountry(String url) throws Exception {
		Client client = new Client(new Context(), Protocol.HTTP);
		ClientResource clientResource = new ClientResource(url);
		clientResource.setNext(client);
		Representation data = clientResource.get();
		Country country = new Country(data.getText());
		client.stop();
		return (country.getCode() == null) ? null : country;
	}
	
	private Emergency getEmergency(String url) throws Exception {
		Client client = new Client(new Context(), Protocol.HTTPS);
		ClientResource clientResource = new ClientResource(url);
		clientResource.setNext(client);
		Representation data = clientResource.get();
		Emergency emergency = new Emergency(data.getText());
		client.stop();
		return (emergency.getCountryName() == null) ? null : emergency;
	}
	
}
