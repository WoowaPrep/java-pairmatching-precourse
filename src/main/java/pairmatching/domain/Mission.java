package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public enum Mission {

    CAR_RACE(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL_GAME(Level.LEVEL1, "숫자야구게임"),

    SHOPPING_BASKET(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY_MAP(Level.LEVEL2, "지하철노선도"),

    PERFORMANCE_IMPROVEMENT(Level.LEVEL4, "성능개선"),
    DISTRIBUTION(Level.LEVEL4, "배포"),
    ;

    private final Level level;
    private final String name;

    public static Mission from(Level level, String name) {
        return Arrays.stream(values())
                .filter(mission -> mission.getLevel() == level)
                .filter(mission -> mission.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> PairmatchingException.from(ErrorMessage.INVALID_MISSION));
    }

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
