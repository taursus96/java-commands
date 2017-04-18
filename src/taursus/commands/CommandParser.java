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
    
    protected boolean endsCmdNameParsing(char c) {
    	return c == ' ';
    }
    
    protected boolean startsCmdParamNameParsing(char c) {
    	return c == '-';
    }
    
    protected boolean endsCmdParamNameParsing(char c) {
        return c == ' ';
    }
    
    protected boolean endsCmdParamValueParsing(char c, boolean withinQuotes) {
    	if(withinQuotes) {
    		return c == '"';
    	} else {
    		return c == ' ' || startsCmdParamNameParsing(c);
    	}
    }
	
    protected String readCmdName(String str) {
    	String name = "";
    	int length = str.length();
    	
    	for(int i=0; i<length; i++){
    		char c = str.charAt(i);
    		if(endsCmdNameParsing(c)) {
    			break;
    		}
    		name += c;
    	}
    	
    	return name;
    }
    
    protected String readCmdParamName(String str) {
    	String name = "";
    	int length = str.length();
    	
    	for(int i=0; i<length; i++){
    		char c = str.charAt(i);
    		if(endsCmdParamNameParsing(c)) {
    			break;
    		}
    		name += c;
    	}
    	
    	return name;
    }
    
    protected String readCmdParamValue(String str, boolean withinQuotes) {
    	String name = "";
    	int length = str.length();
 
    	for(int i=0; i<length; i++){
    		char c = str.charAt(i);
    		if(endsCmdParamValueParsing(c, withinQuotes)) {
    			break;
    		}
    		name += c;
    	}
    	
    	return name;
    }
    
    protected String skipToNextParam(String str) {
    	int skipLength = 0;
    	int length = str.length();
    	
    	for(int i=0; i<length; i++){
    		char c = str.charAt(i);
    		skipLength++;
    		if(startsCmdParamNameParsing(c)) {
    			break;
    		}
    	}
    	
    	return str.substring(skipLength);
    }
    
    protected String skipToNextNonWhiteChar(String str) {
    	int skipLength = 0;
    	int length = str.length();
    	
    	for(int i=0; i<length; i++){
    		char c = str.charAt(i);
    		if(c != ' ') {
    			break;
    		}
    		skipLength++;
    	}
    	
    	return str.substring(skipLength);
    }
    
	@Override
	public ICommand parse(String str, ICommandsRepository repository) {
		String name = readCmdName(str);
		
		ICommand command = repository.getCommand(name);
		if(command != null) {
		    command.setName(name);
	        str = str.substring(name.length());
	        
	        while(str.length() > 0) {
	            str = skipToNextParam(str);
	            
	            String paramName = readCmdParamName(str);
	            str = str.substring(paramName.length());
	            str = skipToNextNonWhiteChar(str);
	            
	            boolean withinQuotes = false;
	            if(str.length() > 0 && str.charAt(0) == '"') {
	                withinQuotes = true;
	                str = str.substring(1);
	            }
	            
	            String paramValue = readCmdParamValue(str, withinQuotes);
	            command.addParam(paramName, paramValue);
	            str = str.substring(paramValue.length());
	        }
		}
		
		return command;
	}
}
