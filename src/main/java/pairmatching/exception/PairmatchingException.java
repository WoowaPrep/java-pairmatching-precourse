package pairmatching.exception;

public class PairmatchingException extends IllegalArgumentException {

    private PairmatchingException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static PairmatchingException from(ErrorMessage errorMessage) {
        return new PairmatchingException(errorMessage);
    }
}
