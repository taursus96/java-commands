package taursus.commands;

import java.util.HashMap;

public class CommandsRepository implements ICommandsRepository {
    protected HashMap<String, Class<?>> commands = new HashMap<String, Class<?>>();

    @Override
    public void addCommand(String name, Class<?> command) {
        commands.put(name, command);
    }

    @Override
    public ICommand getCommand(String name) {
        if (commands.containsKey(name)) {
            try {
                return (ICommand) (commands.get(name).newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
