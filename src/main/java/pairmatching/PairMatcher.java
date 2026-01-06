package pairmatching;

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

    }
}
