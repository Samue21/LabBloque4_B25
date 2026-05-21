package sam.dinopark.model.worker;

public abstract class Worker {
    private final int id;
    private final String name;
    private final double dailySalary;

    public Worker(int id, String name, double dailySalary) {
        this.id = id;
        this.name = name;
        this.dailySalary = dailySalary;
    }

    public abstract String getRole();  // "GUARD" o "TECHNICIAN"
}
