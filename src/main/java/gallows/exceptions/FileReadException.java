package gallows.exceptions;

public class FileReadException extends RuntimeException {

    public FileReadException(String text, Exception e) {
        super(text, e);
    }

}
