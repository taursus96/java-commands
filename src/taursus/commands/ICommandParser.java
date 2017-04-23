package taursus.commands;

public interface ICommandParser {
    public ICommand parse(String command, ICommandsRepository repository);
}
