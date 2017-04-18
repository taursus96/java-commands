package taursus.commands;

import java.util.HashMap;

public class Command implements ICommand {
	protected String name;
	protected HashMap<String, String> params = new HashMap<String, String>();
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public HashMap<String, String> getParams() {
		return this.params;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addParam(String name, String value) {
		this.params.put(name, value);
	}

	public void exec() {}
}
