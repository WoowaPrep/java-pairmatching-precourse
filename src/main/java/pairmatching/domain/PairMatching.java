package pairmatching.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.exception.ErrorMessage;
import pairmatching.exception.PairmatchingException;
import pairmatching.utils.ResourceReader;

public class PairMatching {

    private static final int MAX_RETRY_COUNT = 3;

    private DevelopType developType;
    private Level level;
    private Mission mission;
    private Map<Crew, List<Crew>> matchHistory = new LinkedHashMap<>();

    public PairMatching(DevelopType developType, Level level, Mission mission) {
        this.developType = developType;
        this.level = level;
        this.mission = mission;
    }

    public void match() {
        Crews crews = loadCrews();
        String levelKey = MatchHistoryManager.makeLevelKey(developType, level);

        boolean isPossible;
        int tryCount = 0;
        do {
            if (tryCount == MAX_RETRY_COUNT) {
                throw PairmatchingException.from(ErrorMessage.PAIR_MATCHING_FAILURE);
            }
            crews.shuffle();
            isPossible = isPossiblePair(crews, levelKey);
            tryCount++;
        } while(!isPossible);

        List<Crew> crewList = crews.getCrews();
        int crewCount = crewList.size();

        for (int i = 0; i < crewCount - 1; i += 2) {
            Crew leftCrew = crewList.get(i);
            Crew rightCrew = crewList.get(i+1);
            addMatchHistory(leftCrew, rightCrew);
            MatchHistoryManager.addHistory(levelKey, leftCrew, rightCrew);
        }

        tripleMatch(crewCount, crewList, levelKey);
    }

    private boolean isPossiblePair(Crews crews, String levelKey) {
        List<Crew> crewList = crews.getCrews();
        int crewCount = crewList.size();

        for (int i = 0; i < crewCount - 1; i += 2) {
            Crew leftCrew = crewList.get(i);
            Crew rightCrew = crewList.get(i+1);
            if (!MatchHistoryManager.canMatch(levelKey, leftCrew, rightCrew)) return false;
        }
        if (!tripleCanMatch(crewCount, crewList, levelKey)) return false;

        return true;
    }

    public Crews loadCrews() {
        List<String> crewNames = readResourceLines();
        validateEmpty(crewNames);

        List<Crew> crews = crewNames.stream()
                .map(name -> new Crew(developType, name))
                .collect(Collectors.toList());

        return new Crews(crews);
    }

    private List<String> readResourceLines() {
        if (developType == DevelopType.BACKEND) return ResourceReader.readLines("backend-crew.md");
        if (developType == DevelopType.FRONTEND) return ResourceReader.readLines("frontend-crew.md");

        throw PairmatchingException.from(ErrorMessage.FILE_NOT_FOUND);
    }

    public boolean hasMatchingRecord() {
        return !matchHistory.isEmpty();
    }

    private void tripleMatch(int crewCount, List<Crew> crews, String levelKey) {
        if (crewCount % 2 != 0) {
            Crew firstCrew = crews.get(crewCount - 3);
            Crew secondCrew = crews.get(crewCount - 2);
            Crew thirdCrew = crews.get(crewCount - 1);
            addMatchHistory(firstCrew, thirdCrew);
            addMatchHistory(secondCrew, thirdCrew);
            MatchHistoryManager.addHistory(levelKey, firstCrew, thirdCrew);
            MatchHistoryManager.addHistory(levelKey, secondCrew, thirdCrew);
        }
    }

    private boolean tripleCanMatch(int crewCount, List<Crew> crews, String levelKey) {
        if (crewCount % 2 != 0) {
            Crew firstCrew = crews.get(crewCount - 3);
            Crew secondCrew = crews.get(crewCount - 2);
            Crew thirdCrew = crews.get(crewCount - 1);
            if (!MatchHistoryManager.canMatch(levelKey, firstCrew, thirdCrew) ||
                    !MatchHistoryManager.canMatch(levelKey, secondCrew, thirdCrew)) return false;
        }
        return true;
    }

    private void addMatchHistory(Crew firstCrew, Crew secondCrew) {
        matchHistory.computeIfAbsent(firstCrew, k -> new ArrayList<>()).add(secondCrew);
        matchHistory.computeIfAbsent(secondCrew, k -> new ArrayList<>()).add(firstCrew);
    }

    private void validateEmpty(List<String> input) {
        if (input == null || input.isEmpty()) {
            throw PairmatchingException.from(ErrorMessage.EMPTY_CREW_DATA);
        }
    }

    public DevelopType getDevelopType() {
        return developType;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }

    public Map<Crew, List<Crew>> getMatchHistory() {
        return matchHistory;
    }
}
