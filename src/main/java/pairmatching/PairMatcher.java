package pairmatching;

import java.util.function.Supplier;
import pairmatching.domain.Crews;
import pairmatching.domain.Menu;
import pairmatching.domain.PairMatching;
import pairmatching.view.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatcher {

    private InputView inputView;
    private OutputView outputView;
    private InputParser inputParser;

    public PairMatcher() {
        this(new InputView(), new OutputView(), new InputParser());
    }

    public PairMatcher(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void match() {
        Menu menu = FeatureSelect();
        play(menu);
    }

    private Menu FeatureSelect() {
        String input = inputView.printFeatureSelect();
        return Menu.from(input);
    }

    private void play(Menu menu) {
        if (menu == Menu.FIRST) {
            String input = inputView.printPairmatchingHeader();
            PairMatching pairMatching = inputParser.parsePairmatchInfo(input);
            Crews Crews = inputParser.parseCrews(pairMatching.getDevelopType());

            pairMatching.match(Crews);
            outputView.printPairmatchResult(pairMatching);
        }
        if (menu == Menu.SECOND) {
            String input = inputView.printPairmatchingHeader();
        }
        if (menu == Menu.THIRD) {

        }
        if (menu == Menu.QUIT) {

        }
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
