package sam.dinopark.model.tourist;

import sam.dinopark.model.Enums.*;

import java.util.List;

public class Tourist {
    public final int id;
    public final String name;
    public TouristStatus status;
    public Double moneySpent;
    public List<String> visitedZones;

    public Tourist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void spend(){

    }
    public void recordVisit(){

    }
}
