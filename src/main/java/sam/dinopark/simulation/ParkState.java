package sam.dinopark.simulation;

import sam.dinopark.model.dinosaur.Dinosaur;
import sam.dinopark.model.tourist.Tourist;
import sam.dinopark.model.vehicle.Vehicle;
import sam.dinopark.model.worker.Worker;
import sam.dinopark.persistence.DatabaseService;
import sam.dinopark.zone.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParkState {

    /**
     * La guia dice que contiene todo el estado del parque.
     *
     * Es el objeto que se pasa a los eventos y al monitor — el "estado global"
     * Guarda referencias a: turistas, dinosaurios, workers, zonas, CsvWriter, Random
     * Acumula totalRevenue y totalExpenses
     * Métodos de utilidad: countActiveTourists(), countDinosaursInEnclosure()
     */

    // Entidades del parque
    private final List<Tourist>  tourists;
    private final List<Dinosaur> dinosaurs;
    private final List<Worker>   workers;
    private final List<Vehicle>  vehicles;

    private final ArrivalZone arrivalZone;
    private final CentralHub centralHub;
    private final BathroomZone bathroomZone;
    private final ObservationEnclosure observationEnclosure;
    private final PowerPlant powerPlant;

    private final DatabaseService db;
    private final Random rng;

    private double totalRevenue  = 0.0;
    private double totalExpenses = 0.0;

    private boolean dealsHourActive = false;
    private double  currentDiscount = 0.0;
    private final List<String> activeEventNames = new ArrayList<>();

    private int currentStep = 0;

    public ParkState(List<Tourist> tourists, List<Dinosaur> dinosaurs, List<Worker> workers, List<Vehicle> vehicles, ArrivalZone arrivalZone, CentralHub centralHub, BathroomZone bathroomZone, ObservationEnclosure observationEnclosure, PowerPlant powerPlant, DatabaseService db, Random rng) {
        this.tourists = tourists;
        this.dinosaurs = dinosaurs;
        this.workers = workers;
        this.vehicles = vehicles;
        this.arrivalZone = arrivalZone;
        this.centralHub = centralHub;
        this.bathroomZone = bathroomZone;
        this.observationEnclosure = observationEnclosure;
        this.powerPlant = powerPlant;
        this.db = db;
        this.rng = rng;
    }
}
