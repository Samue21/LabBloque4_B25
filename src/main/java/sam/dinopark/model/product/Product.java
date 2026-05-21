package sam.dinopark.model.product;

public class Product {
    private final int id;
    private final String name;      // "Llavero T-Rex", "Gorra Velociraptor"
    private final double price;
    private final String category;  // "CLOTHING", "TOY", "ACCESSORY"
    private int stock;               // unidades disponibles

    public Product(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;

    }

    public boolean isAvailable() { return stock > 0; }
    public void    sell()         { if (stock > 0) stock--; }
}
