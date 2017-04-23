package taursus.commands;

public class CommandSkipToNextParamReadStrategy implements IReadStrategy {

    @Override
    public boolean endBeforeChar(char c) {
        return false;
    }

    @Override
    public boolean endAfterChar(char c) {
        return c == '-';
    }

    @Override
    public void before(StringIterator it) {

    }

}
