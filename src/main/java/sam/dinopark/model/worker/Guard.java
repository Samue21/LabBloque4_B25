package sam.dinopark.model.worker;

import sam.dinopark.model.dinosaur.Dinosaur;

import static sam.dinopark.model.Enums.*;
import java.util.List;


public class Guard extends Worker {

    public Guard(int id, String name, double dailySalary) {
        super(id, name, dailySalary);
    }

    @Override
    public String getRole() {
        return "GUARD";
    }

    public void recaptureEscapedDinosaurs(List<Dinosaur> dinosaurs) {
        dinosaurs.stream()
                .filter(d -> d.isInEnclosure() == DinosaurStatus.ESCAPED)
                .forEach(Dinosaur::returnToEnclosure);
    }
}
