package sam.dinopark.zone;

import sam.dinopark.model.Enums;
import sam.dinopark.model.tourist.Tourist;
import sam.dinopark.persistence.DatabaseService;
import sam.dinopark.persistence.Records;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Cada turista visita el hub
 * tiene probabilidad de comprar souvenir.
 * como soy intermedio hay descuento aqui.
 */

public class CentralHub implements ParkZone{

    private final double souvenirBasePrice;
    private final double souvenirProbability;

    private final List<Tourist> currentVisitors = new ArrayList<>();

    private long revenueCounter = 0;

    public CentralHub(double souvenirBasePrice, double souvenirProbability) {
        this.souvenirBasePrice   = souvenirBasePrice;
        this.souvenirProbability = souvenirProbability;

    }

    public void visit(Tourist tourist, Random rng, DatabaseService db, double discount) {
        if (tourist.getStatus() != Enums.TouristStatus.IN_PARK) return;

        tourist.recordVisit("CentralHub");

        // Comprará souvenir?
        if (rng.nextDouble() < souvenirProbability) {
            double price = souvenirBasePrice * (1.0 - discount);
            tourist.spend(price);

            db.appendRevenue(new Records.RevenueRecord(
                    ++revenueCounter,
                    "SOUVENIR",
                    price,
                    tourist.getId(),
                    "CentralHub",
                    LocalDateTime.now()
            ));

            System.out.printf("[Central Hub] -  %s compró souvenir: $%.2f%s%n",
                    tourist.getName(), price, discount > 0 ? " (con dto)" : "");
        }
    }


    @Override public String     getName()             { return "Central Hub"; }
    @Override public boolean    hasCapacity()         { return true; } //
    @Override public int        getCurrentOccupancy() { return currentVisitors.size(); }
    @Override public int        getMaxCapacity()      { return Integer.MAX_VALUE; }

    @Override
    public void enter(Tourist tourist) { currentVisitors.add(tourist); }

    @Override
    public void exit(Tourist tourist) { currentVisitors.remove(tourist); }
}
