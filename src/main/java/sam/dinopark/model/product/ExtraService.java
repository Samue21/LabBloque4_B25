package sam.dinopark.model.product;

public class ExtraService {
    private final int id;
    private final String name;       // "SPA", "Tour Guiado", "Foto con Dino"
    private final double price;


    public ExtraService(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
