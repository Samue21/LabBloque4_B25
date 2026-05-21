package sam.dinopark.zone;

import sam.dinopark.model.tourist.Tourist;

public interface ParkZone {
    String getName();
    boolean hasCapacity();
    int getCurrentOccupancy();
    int getMaxCapacity();
    void enter(Tourist tourist);
    void exit(Tourist tourist);
}
