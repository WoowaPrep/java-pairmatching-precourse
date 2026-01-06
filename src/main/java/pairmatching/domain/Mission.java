package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public enum Mission {

    LEVEL1_MISSION("레벨1"),
    LEVEL2_MISSION("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5"),
    ;

    private final String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission from(String input) {
        return Arrays.stream(values())
                .filter(mission -> mission.getName().equals(input))
                .findFirst()
                .orElseThrow(() -> PairmatchingException.from(ErrorMessage.INVALID_MISSION));
    }

    public String getName() {
        return name;
    }


}
