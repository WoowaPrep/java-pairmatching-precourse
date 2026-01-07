package pairmatching.domain;

public class Crew {

    private DevelopType developType;
    private String name;

    public Crew(DevelopType developType, String name) {
        this.developType = developType;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
