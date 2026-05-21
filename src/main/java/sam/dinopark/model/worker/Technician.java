package sam.dinopark.model.worker;

import sam.dinopark.zone.PowerPlant;
import sam.dinopark.model.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

import static sam.dinopark.model.Enums.VehicleStatus.*;


public class Technician extends Worker {
    public Technician(int id, String name, double dailySalary) {
        super(id, name, dailySalary);
    }

    @Override
    public String getRole() {
        return "TECHNICIAN";
    }

    // INTERMEDIO: necesita un vehículo AVAILABLE para reparar
    public void repairIfNeeded(PowerPlant plant, List<Vehicle> vehicles) {
        if (!plant.isOperational()) {
            // Busca el primer vehículo con status AVAILABLE
            Optional<Vehicle> available = vehicles.stream()
                    .filter(v -> v.getStatus() == AVAILABLE)
                    .findFirst();
            if (available.isPresent()) {
                available.get().use();     // marca como IN_USE
                plant.repair();
                available.get().free();    // devuelve a AVAILABLE
            }
            // Si no hay vehículo: la planta queda sin reparar este step
        }
    }
}
