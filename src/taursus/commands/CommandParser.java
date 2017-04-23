package taursus.commands;

public class CommandParser implements ICommandParser {
	protected static class CommandParserLoader {
        private static final CommandParser INSTANCE = new CommandParser();
    }

	protected CommandParser() {
        if (CommandParserLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static CommandParser getInstance() {
        return CommandParserLoader.INSTANCE;
    }
    
	@Override
	public ICommand parse(String str, ICommandsRepository repository) {
	    StringReader reader = new StringReader();
	    StringIterator it = new StringIterator(str);
		String name = reader.read(it, new CommandNameReadStrategy());
		
		ICommand command = repository.getCommand(name);
		
		if(command == null) {
		    return null;
		}
		
	    command.setName(name);
        
        while(it.hasNext()) {
            reader.read(it, new CommandSkipToNextParamReadStrategy()); //Skip to next param
            
            String paramName = reader.read(it, new CommandParamNameReadStrategy());
            it.skipToNextNonWhiteChar();
            
            String paramValue = "";
            
            if(determineIfParamValueExists(it)) {
                boolean isWithinQuotes = determineIfParamValueIsWithinQuotes(it);
                paramValue = reader.read(it, new CommandParamValueReadStrategy(isWithinQuotes));
            }
            
            command.addParam(paramName, paramValue);
        }
		
		return command;
	}

    private boolean determineIfParamValueExists(StringIterator it) {
        if(it.hasNext()) {
            char c = it.next();
            it.rewind();
            
            if(c != '-') {
                return true;
            }
        }
        
        return false;
    }

    private boolean determineIfParamValueIsWithinQuotes(StringIterator it) {
        if(it.hasNext()) {
            boolean withinQuotes = it.next() == '"';
            it.rewind();
            return withinQuotes;
        }
        
        return false;
    }
}
