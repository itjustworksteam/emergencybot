package it.itjustworks.emergencybot;

import static org.junit.Assert.*;

import org.junit.Test;

import it.itjustworks.emergencybot.commands.CommandParser;

public class CommandParserTest {

	@Test
    public void testClass() {
        new CommandParser("");
    }

    @Test
    public void testOne() {
        CommandParser parser = new CommandParser("/start start");
        assertEquals("/start", parser.getCommand());
        assertEquals("start", parser.getArgument());
    }

    @Test
    public void testTwo() {
        CommandParser c = new CommandParser("/start");
        assertEquals("/start", c.getCommand());
        assertEquals("", c.getArgument());
    }

    @Test
    public void testThree() {
        CommandParser c = new CommandParser("");
        assertEquals("", c.getCommand());
        assertEquals("", c.getArgument());
    }

    @Test
    public void testFour() {
        CommandParser c = new CommandParser(null);
        assertEquals("", c.getCommand());
        assertEquals("", c.getArgument());
    }

    @Test
    public void testTFive() {
        CommandParser c = new CommandParser("/comando lungo lungo lungo");
        assertEquals("/comando", c.getCommand());
        assertEquals("lungo lungo lungo", c.getArgument());
    }
    
    @Test
    public void testSix(){
    	CommandParser c = new CommandParser("/contact_115");
    	assertEquals("/contact", c.getCommand());
    	assertEquals("115", c.getArgument());
    }
	
}
