package pairmatching.view;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.PairMatching;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;
import pairmatching.utils.ResourceReader;

public class InputParser {

    public static final int SEPARATION_COUNT = 3;

    public static final String COMMA = ",";
    public static final String SPACE = " ";

    public PairMatching parsePairmatchInfo(String input) {
        String[] splitInput = input.split(COMMA + SPACE);
        validateCount(splitInput);
        DevelopType developType = DevelopType.from(splitInput[0]);
        Level level = Level.from(splitInput[1]);
        Mission mission = Mission.from(level, splitInput[2]);
        return new PairMatching(developType, level, mission);
    }

    private void validateCount(String[] input) {
        if (input.length != SEPARATION_COUNT) {
            throw PairmatchingException.from(ErrorMessage.INVALID_SEPARATION_COUNT);
        }
    }

}
