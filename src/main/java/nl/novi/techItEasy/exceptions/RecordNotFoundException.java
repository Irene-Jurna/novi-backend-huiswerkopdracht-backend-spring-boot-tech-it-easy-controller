package nl.novi.techItEasy.exceptions;

// Hier maken we onze eigen exceptions, niet de standaard (helpt bij de communicatie)

// RunTime: exception komt op tijdens het runnen, Exception komt op tijdens het compilen (met RunTime gaat de applicatie draaien ook met exceptions, bij Exception kan die meteen crashen)
public class RecordNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public RecordNotFoundException() {
        super();
    }
    public RecordNotFoundException(String message) {
        super(message);
    }
}
