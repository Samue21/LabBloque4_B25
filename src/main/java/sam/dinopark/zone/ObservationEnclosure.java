package sam.dinopark.zone;

import sam.dinopark.model.tourist.Tourist;

public class ObservationEnclosure implements ParkZone{
    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean hasCapacity() {
        return false;
    }

    @Override
    public int getCurrentOccupancy() {
        return 0;
    }

    @Override
    public int getMaxCapacity() {
        return 0;
    }

    @Override
    public void enter(Tourist tourist) {

    }

    @Override
    public void exit(Tourist tourist) {

    }
}
