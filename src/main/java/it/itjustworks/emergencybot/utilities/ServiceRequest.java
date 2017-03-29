package it.itjustworks.emergencybot.utilities;

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.pengrad.telegrambot.model.Location;

import it.itjustworks.emergencybot.server.BotConstants;

public class ServiceRequest {
		
	public static String emergencyServerUrl(){
		if(System.getenv("EMERGENCY_URL") != null) {
			return System.getenv("EMERGENCY_URL");
		}
		return "https://emergency-server.herokuapp.com/api/v2/numbers/";
	}
	
	// GET /api/v2/numbers/:latitude/:longitude
	public String executeWithLocation(Location location) throws Exception{
		Emergency emergency = getEmergency(emergencyServerUrl() + location.latitude().toString() + "/" + location.longitude().toString());
		if(emergency == null) {
			return BotConstants.GETTING_EMERGENCY_ERROR;
		}
		return emergency.toJSON();
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
