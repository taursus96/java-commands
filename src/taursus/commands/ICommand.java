package taursus.commands;

import java.util.HashMap;

public interface ICommand {
	public String getName();
	public void setName(String name);
	public HashMap<String, String> getParams();
	public void addParam(String name, String value);
	public void exec();
}
