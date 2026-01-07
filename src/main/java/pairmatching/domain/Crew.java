package pairmatching.domain;

public class Crew {

    private DevelopType developType;
    private String name;

    public Crew(DevelopType developType, String name) {
        this.developType = developType;
        this.name = name;
    }

    public DevelopType getDevelopType() {
        return developType;
    }

    public String getName() {
        return name;
    }
}
