package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public enum Menu {

    FIRST("1"),
    SECOND("2"),
    THIRD("3"),
    QUIT("Q"),
    ;

    private final String name;

    Menu(String name) {
        this.name = name;
    }

    public static Menu from(String input) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(input))
                .findFirst()
                .orElseThrow(() -> PairmatchingException.from(ErrorMessage.INVALID_FEATURE_MENU));
    }

    public String getName() {
        return name;
    }
}
