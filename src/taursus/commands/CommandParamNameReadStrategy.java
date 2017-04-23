package taursus.commands;

public class CommandParamNameReadStrategy implements IReadStrategy {

    @Override
    public boolean endBeforeChar(char c) {
        return c == ' ';
    }

    @Override
    public boolean endAfterChar(char c) {
        return false;
    }

    @Override
    public void before(StringIterator it) {

    }

}
