package taursus.commands.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import taursus.commands.*;
import junit.framework.Assert;

public class CommandParserTest {

	@Test
	public void testCommandWithNoParams() {
		ICommandParser parser = CommandParser.getInstance();
		
		ICommand command = parser.parse("commandName", new CommandsRepository());
		HashMap<String, String> params = command.getParams();
		
		Assert.assertEquals("commandName", command.getName());
	}

	@Test
	public void testCommandWithOneParamNoValue() {
		ICommandParser parser = CommandParser.getInstance();
		
		ICommand command = parser.parse("commandName -v", new CommandsRepository());
		HashMap<String, String> params = command.getParams();
		
		Assert.assertEquals("commandName", command.getName());
		Assert.assertEquals("", params.get("v"));
	}
	
	@Test
	public void testCommandWithOneParamWithValue() {
		ICommandParser parser = CommandParser.getInstance();
		
		ICommand command = parser.parse("commandName -v \"123\"", new CommandsRepository());
		HashMap<String, String> params = command.getParams();
		
		Assert.assertEquals("commandName", command.getName());
		Assert.assertEquals("123", params.get("v"));
	}
	
	@Test
	public void testCommandWithOneParamWithNonStringValue() {
		ICommandParser parser = CommandParser.getInstance();
		
		ICommand command = parser.parse("commandName -v 321", new CommandsRepository());
		HashMap<String, String> params = command.getParams();
		
		Assert.assertEquals("commandName", command.getName());
		Assert.assertEquals("321", params.get("v"));
	}
	
	@Test
	public void testCommandWithMultiParams() {
		ICommandParser parser = CommandParser.getInstance();
		
		ICommand command = parser.parse("commandName -v -g \"test\" -d", new CommandsRepository());
		HashMap<String, String> params = command.getParams();
		
		Assert.assertEquals("commandName", command.getName());
		Assert.assertEquals("", params.get("v"));
		Assert.assertEquals("test", params.get("g"));
		Assert.assertEquals("", params.get("d"));
		Assert.assertEquals(true, params.containsKey("v"));
		Assert.assertEquals(false, params.containsKey("test"));
	}
}
