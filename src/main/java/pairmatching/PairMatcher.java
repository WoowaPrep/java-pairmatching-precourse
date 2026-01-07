package pairmatching;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import pairmatching.domain.MatchHistoryManager;
import pairmatching.domain.Menu;
import pairmatching.domain.PairMatching;
import pairmatching.domain.PairRematchStatus;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;
import pairmatching.view.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatcher {

    private InputView inputView;
    private OutputView outputView;
    private InputParser inputParser;

    private Map<String, PairMatching> matchingHistory = new HashMap<>();

    public PairMatcher() {
        this(new InputView(), new OutputView(), new InputParser());
    }

    public PairMatcher(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void match() {
        boolean isContinue;
        do {
            isContinue = play();
        } while(isContinue);
    }

    private boolean play() {
        return retry(() -> {
            Menu menu = FeatureSelect();

            if (menu == Menu.FIRST) return handlePairMatching();
            if (menu == Menu.SECOND) return handlePairInquiry();
            if (menu == Menu.THIRD) return handlePairClear();
            if (menu == Menu.QUIT) return false;

            throw PairmatchingException.from(ErrorMessage.INVALID_FEATURE_MENU);
        });
    }

    private Menu FeatureSelect() {
        return retry(() -> {
            String input = inputView.printFeatureSelect();
            return Menu.from(input);
        });
    }

    private boolean handlePairMatching() {
        inputView.printPairmatchingHeader();

        while(true) {
            String input = inputView.printPairmatchingSelect();
            PairMatching pairMatching = inputParser.parsePairmatchInfo(input);
            String key = makeKey(pairMatching);

            PairMatching existing = matchingHistory.get(key);

            if (existing != null && existing.hasMatchingRecord()) {
                String rematchInput = inputView.printRematch();
                PairRematchStatus rematchMarker = PairRematchStatus.from(rematchInput);

                if (rematchMarker == PairRematchStatus.NO) continue;

                String levelKey = MatchHistoryManager.makeLevelKey(
                        pairMatching.getDevelopType(),
                        pairMatching.getLevel()
                );
                matchingHistory.remove(key);
                MatchHistoryManager.clearLevel(levelKey);
            }

            pairMatching.match();
            matchingHistory.put(key, pairMatching);
            outputView.printPairmatchResult(pairMatching);
            return true;
        }
    }

    private boolean handlePairInquiry() {
        inputView.printPairmatchingHeader();
        String input = inputView.printPairmatchingSelect();
        PairMatching pairMatching = inputParser.parsePairmatchInfo(input);
        String key = makeKey(pairMatching);

        PairMatching existing = matchingHistory.get(key);
        if (existing == null || !existing.hasMatchingRecord()) {
            throw PairmatchingException.from(ErrorMessage.NOT_EXIST_PAIR_MATCH_RECORD);
        }

        outputView.printPairmatchResult(existing);
        return true;
    }

    private boolean handlePairClear() {
        matchingHistory.clear();
        MatchHistoryManager.clearAll();
        outputView.printPairmatchClear();
        return true;
    }

    private String makeKey(PairMatching pairMatching) {
        return pairMatching.getDevelopType().getName() + "_"
                + pairMatching.getLevel().getName() + "_"
                + pairMatching.getMission().getName();
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
