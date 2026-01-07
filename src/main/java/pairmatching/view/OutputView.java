package pairmatching.view;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import pairmatching.domain.Crew;
import pairmatching.domain.PairMatching;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String PAIR_MATCHING_HEADER = "페어 매칭 결과입니다.";
    private static final String PAIR_MATCHING_RESULT = " : %s";

    public void printPairmatchResult(PairMatching pairMatching) {
        printNewLine();
        System.out.println(PAIR_MATCHING_HEADER);
        printPairList(pairMatching);
        printNewLine();
    }

    private void printPairList(PairMatching pairMatching) {
        Map<Crew, List<Crew>> matchHistory = pairMatching.getMatchHistory();
        Set<Crew> usedCrew = new HashSet<>();

        for (Entry<Crew, List<Crew>> entry : matchHistory.entrySet()) {
            Crew firstCrew = entry.getKey();
            List<Crew> otherCrews = entry.getValue();
            if (usedCrew.contains(firstCrew)) continue;

            System.out.print(firstCrew.getName());
            usedCrew.add(firstCrew);
            for (Crew crew : otherCrews) {
                System.out.printf(PAIR_MATCHING_RESULT, crew.getName());
                usedCrew.add(crew);
            }
            printNewLine();
        }
    }
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
