package sam.dinopark.model.transaction;

import java.time.LocalDateTime;
import static sam.dinopark.model.Enums.*;

public class Ticket {
    private long id;
    private int touristId;
    private double price;
    private ExperienceType category;
    private LocalDateTime issuedAt;

    public Ticket(long id, int touristId, double price, ExperienceType category, LocalDateTime issuedAt) {
        this.id = id;
        this.touristId = touristId;
        this.price = price;
        this.category = category;
        this.issuedAt = issuedAt;
    }

    public long getId() {
        return id;
    }

    public int getTouristId() {
        return touristId;
    }

    public double getPrice() {
        return price;
    }

    public ExperienceType getCategory() {
        return category;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }
}
