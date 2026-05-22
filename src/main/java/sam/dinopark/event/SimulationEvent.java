package sam.dinopark.event;

import sam.dinopark.persistence.Records.*;
import sam.dinopark.simulation.ParkState;

import java.util.Random;

public interface SimulationEvent {
    String      getName();
    String      getDescription();
    void        execute(ParkState state, Random rng);
    EventRecord toRecord(long step);
    double getProbability();
}
