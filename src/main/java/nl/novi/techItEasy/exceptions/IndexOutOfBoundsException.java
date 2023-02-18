package nl.novi.techItEasy.exceptions;

public class IndexOutOfBoundsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public IndexOutOfBoundsException() {
        super();
    }
    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
