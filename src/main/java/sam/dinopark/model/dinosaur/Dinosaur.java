package sam.dinopark.model.dinosaur;

import static sam.dinopark.model.Enums.*;
import static sam.dinopark.model.Enums.DinosaurStatus.*;

public abstract class Dinosaur {

    // Campos comunes a todos los dinosaurios
    private final int id;
    private final String name, species;
    private DinosaurStatus status;  // inicia en IN_ENCLOSURE
    private final double feedingCostPerDay;

    protected Dinosaur(int id, String name, String species, double feedingCostPerDay) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.feedingCostPerDay = feedingCostPerDay;
        this.status = IN_ENCLOSURE;
    }

    // Métodos abstractos — cada subclase define su propio comportamiento
    public abstract String getDiet();        // "CARNIVORE" o "HERBIVORE"
    public abstract double getDangerLevel(); // 0.0 a 1.0

    // Métodos concretos — iguales para todos
    public void escape()           { status = ESCAPED;     }
    public void recapture()        { status = RECAPTURED;  }
    public void returnToEnclosure(){ status = IN_ENCLOSURE;}
    public DinosaurStatus isInEnclosure(){ return status; }
}