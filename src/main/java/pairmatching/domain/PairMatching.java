package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PairMatching {

    private DevelopType developType;
    private Level level;
    private Mission mission;
    private Map<Crew, List<Crew>> matchHistory = new LinkedHashMap<>();

    public PairMatching(DevelopType developType, Level level, Mission mission) {
        this.developType = developType;
        this.level = level;
        this.mission = mission;
    }

    public void match(Crews crews) {
        int crewCount = crews.getCount();
        crews.shuffle();
        List<Crew> crewList = crews.getCrews();
        for (Crew crew : crewList) {
            System.out.println(crew.getName());
        }
        System.out.println();
        int count = crewList.size();


        for (int i = 0; i < count - 1; i += 2) {
            Crew leftCrew = crewList.get(i);
            Crew rightCrew = crewList.get(i+1);
            addMatchHistory(leftCrew, rightCrew);
        }

        tripleMatch(crewCount, crewList);
    }

    private void tripleMatch(int crewCount, List<Crew> crews) {
        if (crewCount % 2 != 0) {
            Crew firstCrew = crews.get(crewCount - 3);
            Crew secondCrew = crews.get(crewCount - 2);
            Crew thirdCrew = crews.get(crewCount - 1);
            addMatchHistory(firstCrew, thirdCrew);
            addMatchHistory(secondCrew, thirdCrew);
        }
    }

    private void addMatchHistory(Crew firstCrew, Crew secondCrew) {
        matchHistory.putIfAbsent(firstCrew, new ArrayList<>());
        matchHistory.get(firstCrew).add(secondCrew);

        matchHistory.putIfAbsent(secondCrew, new ArrayList<>());
        matchHistory.get(secondCrew).add(firstCrew);
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
