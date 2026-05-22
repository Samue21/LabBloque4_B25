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
        moneySpent = 0.00;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TouristStatus getStatus() {
        return status;
    }

    public void setStatus(TouristStatus status) {
        this.status = status;
    }


    public void spend(double amount){
        moneySpent += amount;
    }
    public void recordVisit(String zone){
        visitedZones.add(zone);
    }
}
