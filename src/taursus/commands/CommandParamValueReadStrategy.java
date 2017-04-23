package taursus.commands;

public class CommandParamValueReadStrategy implements IReadStrategy {
    private boolean isWithinQuotes = false;
    
    public CommandParamValueReadStrategy(boolean isWithinQuotes) {
        this.isWithinQuotes = isWithinQuotes;
    }
    
    @Override
    public boolean endBeforeChar(char c) {
        if(this.isWithinQuotes) {
            return c == '"';
        } else {
            return c == ' ' || c == '-';
        }
    }

    @Override
    public boolean endAfterChar(char c) {
        return false;
    }

    @Override
    public void before(StringIterator it) {
        if(this.isWithinQuotes) {
            it.next(); //skip quote
        }
    }

}
