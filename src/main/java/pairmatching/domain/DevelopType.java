package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public enum DevelopType {

    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    ;

    private final String name;

    DevelopType(String name) {
        this.name = name;
    }

    public static DevelopType from(String input) {
        return Arrays.stream(values())
                .filter(developType -> developType.getName().equals(input))
                .findFirst()
                .orElseThrow(() -> PairmatchingException.from(ErrorMessage.INVALID_DEVELOP_TYPE));
    }

    public String getName() {
        return name;
    }
}
