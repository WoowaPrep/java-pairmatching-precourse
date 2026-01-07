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
            + "############################################";

    private final static String PROGRESS_MISSION_SELECT_MESSAGE =
            "과정, 레벨, 미션을 선택하세요." + NEW_LINE
            + "ex) 백엔드, 레벨1, 자동차경주";

    private final static String REMATCH_MESSAGE_HEADER = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?";
    private final static String REMATCH_INPUT_EXAMPLE_MESSAGE = "네 | 아니오";

    public String printFeatureSelect() {
        printNewLine();
        System.out.println(FEATURE_SELECT_INPUT);
        return readLine();
    }

    public void printPairmatchingHeader() {
        printNewLine();
        System.out.println(PROGRESS_MISSION_HEAR);
    }

    public String printPairmatchingSelect() {
        printNewLine();
        System.out.println(PROGRESS_MISSION_SELECT_MESSAGE);
        return readLine();
    }

    public String printRematch() {
        printNewLine();
        System.out.println(REMATCH_MESSAGE_HEADER);
        System.out.println(REMATCH_INPUT_EXAMPLE_MESSAGE);
        return readLine();
    }

    private String readLine() {
        return Console.readLine();
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
