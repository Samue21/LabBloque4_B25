package sam.dinopark.model.vehicle;
import static sam.dinopark.model.Enums.*;
import static sam.dinopark.model.Enums.VehicleStatus.*;

public class Vehicle {
    private int id;
    private VehicleStatus status;

    public Vehicle(int id, VehicleStatus status) {
        this.id = id;
        this.status = AVAILABLE;
    }

    public void use(){ status = IN_USE; }
    public void free(){ status = AVAILABLE;}
    public VehicleStatus getStatus(){ return status; }

}
