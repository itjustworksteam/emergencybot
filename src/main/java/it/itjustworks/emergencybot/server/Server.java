package it.itjustworks.emergencybot.server;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.service.LogService;

public class Server {
	private final Component component;
	
	public Server(){
		component = new Component();
		component.getServers().add(Protocol.HTTP, Config.INSTANCE.PORT);
		component.getDefaultHost().attachDefault(new BotApplication());
		component.setLogService(new LogService(false));
		component.getLogger().info("Server starded at http://localhost:" + Config.INSTANCE.PORT + "/bot/"+ Config.INSTANCE.SERVER_TOKEN);
	}
	
	public void start() throws Exception {
		component.start();
	}
	
	public void stop() throws Exception {
		component.stop();
	}
	
	public static void main(String[] args) throws Exception{
		final Server server = new Server();
		server.start();
	}
}
