package taursus.commands;

public class StringIterator {
    private String content;
    private int contentLength;
    private int currentIndex = 0;
    
    public StringIterator(String content) {
        this.content = content;
        this.contentLength = content.length();
    }

    public boolean hasNext() {
        return this.currentIndex < this.contentLength;
    }

    public char next() {
        if(hasNext()) {
            return this.content.charAt(this.currentIndex++);
        }
        
        throw new IndexOutOfBoundsException();
    }

    public void skipToNextNonWhiteChar() {
        while(hasNext()) {
            if(next() != ' ') {
                rewind();
                return;
            }
        }
    }

    public void rewind() {
        if(this.currentIndex > 0) {
            this.currentIndex--;
        }
    }
    
}
