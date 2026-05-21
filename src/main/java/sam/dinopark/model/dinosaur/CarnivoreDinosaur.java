package sam.dinopark.model.dinosaur;

public class CarnivoreDinosaur extends Dinosaur {

    protected CarnivoreDinosaur(int id, String name, String species, double feedingCostPerDay) {
        super(id, name, species, 500.0);
    }

    @Override
    public String getDiet() {
        return "CARNIVORE";
    }

    @Override
    public double getDangerLevel() {
        return 0.9;
    }
}
