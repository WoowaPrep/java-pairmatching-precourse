package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;

public class Crews {

    private static final int PAIR_MATCHING_THRESHOLD = 2;

    private List<Crew> crews;

    public Crews(List<Crew> crews) {
        validateCount(crews);
        this.crews = crews;
    }

    public void shuffle() {
        List<String> crewNames = crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());

        List<String> shuffledNames = Randoms.shuffle(crewNames);

        List<Crew> shuffledCrews = new ArrayList<>();
        for (String shuffledName : shuffledNames) {
            Crew crew = crews.stream()
                    .filter(c -> c.getName().equals(shuffledName))
                    .findFirst()
                    .orElseThrow(() -> PairmatchingException.from(ErrorMessage.INVALID_CREW));
            shuffledCrews.add(crew);
        }

        this.crews = shuffledCrews;
    }

    private void validateCount(List<Crew> crews) {
        if (crews.size() < PAIR_MATCHING_THRESHOLD) {
            throw PairmatchingException.from(ErrorMessage.INVALID_CREW_COUNT);
        }
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public int count() {
        return crews.size();
    }
}
