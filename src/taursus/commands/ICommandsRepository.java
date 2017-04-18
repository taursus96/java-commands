package taursus.commands;

public interface ICommandsRepository {
	public void addCommand(String name, Class<?> command);
	public ICommand getCommand(String name);
}
