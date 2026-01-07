package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public enum PairRematchStatus {

    YES("네"),
    NO("아니요"),
    ;

    private final String status;

    PairRematchStatus(String status) {
        this.status = status;
    }

    public static PairRematchStatus from(String input) {
        return Arrays.stream(values())
                .filter(status -> status.getStatus().equals(input))
                .findFirst()
                .orElseThrow(() -> PairmatchingException.from(ErrorMessage.INVALID_REMATCH_STATUS));
    }

    public String getStatus() {
        return status;
    }
}
