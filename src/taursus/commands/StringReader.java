package taursus.commands;

public class StringReader {

    public String read(StringIterator it, IReadStrategy readStrategy) {
        String content = "";

        readStrategy.before(it);

        while (it.hasNext()) {
            char c = it.next();
            if (readStrategy.endBeforeChar(c)) {
                break;
            }

            content += c;

            if (readStrategy.endAfterChar(c)) {
                break;
            }
        }

        return content;
    }
}
