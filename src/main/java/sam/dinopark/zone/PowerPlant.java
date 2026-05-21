package sam.dinopark.zone;
import static sam.dinopark.model.Enums.*;
import static sam.dinopark.model.Enums.PowerPlantStatus.*;

public class PowerPlant {
    private PowerPlantStatus status;


    public boolean isOperational(){ return status == WORKING; }
    public void repair() {  status = WORKING; }

}
