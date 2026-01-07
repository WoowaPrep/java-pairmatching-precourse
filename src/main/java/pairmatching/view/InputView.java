package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final static String NEW_LINE = System.lineSeparator();
    private final static String FEATURE_SELECT_INPUT =
            "기능을 선택하세요." + NEW_LINE
            + "1. 페어 매칭" + NEW_LINE
            + "2. 페어 조회" + NEW_LINE
            + "3. 페어 초기화" + NEW_LINE
            + "Q. 종료";
    private final static String PROGRESS_MISSION_HEAR =
            "#############################################" + NEW_LINE
            + "과정: 백엔드 | 프론트엔드" + NEW_LINE
            + "미션:" + NEW_LINE
            + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임" + NEW_LINE
            + "  - 레벨2: 장바구니 | 결제 | 지하철노선도" + NEW_LINE
            + "  - 레벨3: " + NEW_LINE
            + "  - 레벨4: 성능개선 | 배포" + NEW_LINE
            + "  - 레벨5: " + NEW_LINE
            + "############################################" + NEW_LINE
            + "과정, 레벨, 미션을 선택하세요." + NEW_LINE
            + "ex) 백엔드, 레벨1, 자동차경주";

    public String printFeatureSelect() {
        printNewLine();
        System.out.println(FEATURE_SELECT_INPUT);
        return readLine();
    }

    public String printPairmatchingHeader() {
        printNewLine();
        System.out.println(PROGRESS_MISSION_HEAR);
        return readLine();
    }

    private String readLine() {
        return Console.readLine();
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
