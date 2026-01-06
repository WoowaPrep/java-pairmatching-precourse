package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final static String NEW_LINE = System.lineSeparator();

    private String readLine() {
        return Console.readLine();
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
