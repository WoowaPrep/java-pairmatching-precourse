package pairmatching.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatchHistoryManager {

    private static Map<String, Map<Crew, Set<Crew>>> levelHistories = new HashMap<>();

    public static String makeLevelKey(DevelopType developType, Level level) {
        return developType.getName() + "_" + level.getName();
    }

    public static boolean canMatch(String labelKey, Crew crew1, Crew crew2) {
        Map<Crew, Set<Crew>> levelHistory = levelHistories.get(labelKey);
        if (levelHistory == null) return true;

        Set<Crew> partners = levelHistory.get(crew1);
        if (partners == null) return true;

        return !partners.contains(crew2);
    }

    public static void addHistory(String levelKey, Crew crew1, Crew crew2) {
        Map<Crew, Set<Crew>> levelHistory = levelHistories.computeIfAbsent(levelKey, k -> new HashMap<>());

        levelHistory.computeIfAbsent(crew1, k -> new HashSet<>()).add(crew2);
        levelHistory.computeIfAbsent(crew2, k -> new HashSet<>()).add(crew1);

    }

    public static void clearLevel(String levelKey) {
        levelHistories.remove(levelKey);
    }

    public static void clearAll() {
        levelHistories.clear();
    }
}
