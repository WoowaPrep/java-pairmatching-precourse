package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public enum Level {

    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5"),
    ;

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level from(String input) {
        return Arrays.stream(values())
                .filter(level -> level.getName().equals(input))
                .findFirst()
                .orElseThrow(() -> PairmatchingException.from(ErrorMessage.INVALID_LEVEL));
    }

    public String getName() {
        return name;
    }
}
