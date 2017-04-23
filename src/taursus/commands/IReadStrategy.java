package taursus.commands;

public interface IReadStrategy {
    boolean endBeforeChar(char c);
    boolean endAfterChar(char c);
    void before(StringIterator it);
}
