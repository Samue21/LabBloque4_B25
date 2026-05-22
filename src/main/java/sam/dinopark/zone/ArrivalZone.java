package sam.dinopark.zone;

import sam.dinopark.model.Enums;
import sam.dinopark.model.Enums.*;
import sam.dinopark.model.tourist.Tourist;
import sam.dinopark.persistence.DatabaseService;
import sam.dinopark.persistence.Records;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Zona de Arribo — punto de entrada al parque.
 * Cola de espera.
 * Vende boletos en lotes (batchSize).
 * Cambia status del turista a IN_PARK.
 */
public class ArrivalZone implements ParkZone{

    private final int    maxCapacity;
    private final double baseTicketPrice;

    // Cola de turistas esperando para entrar al parque
    private final Deque<Tourist> waitingQueue = new ArrayDeque<>();

    // Turistas que ya están dentro del parque
    private final List<Tourist> insidePark = new ArrayList<>();

    // Estado de la zona
    private ZoneStatus status = ZoneStatus.OPERATIONAL;


    private long ticketCounter = 0;

    public ArrivalZone(int maxCapacity, double baseTicketPrice) {
        this.maxCapacity     = maxCapacity;
        this.baseTicketPrice = baseTicketPrice;
    }

    //Procesa un lote de turistas que llegan al parque

    public void processBatch(int batchSize, DatabaseService db, double discount) {
        if (status == ZoneStatus.CLOSED || status == ZoneStatus.EMERGENCY) {
            System.out.println("[Zona de Arribo]  Zona cerrada — no se procesan turistas.");
            return;
        }

        int processed = 0;
        while (processed < batchSize && !waitingQueue.isEmpty() && hasCapacity()) {
            Tourist tourist = waitingQueue.poll();

            // Calcular precio con descuento
            double finalPrice = baseTicketPrice * (1.0 - discount);

            // Marcar turista como dentro del parque
            tourist.setStatus(TouristStatus.IN_PARK);
            tourist.spend(finalPrice);
            tourist.recordVisit("ArrivalZone");
            insidePark.add(tourist);

            // Registrar ingreso en BD
            db.appendRevenue(new Records.RevenueRecord(
                    ++ticketCounter,
                    "TICKET",
                    finalPrice,
                    tourist.getId(),
                    "ArrivalZone",
                    LocalDateTime.now()
            ));

            if (discount > 0) {
                System.out.printf("[Zona de Arribo] -  Turista %s ingresó. Boleto: $%.2f (%.0f%% dto)%n",
                        tourist.getName(), finalPrice, discount * 100);
            } else {
                System.out.printf("[Zona de Arribo] -  Turista %s ingresó. Boleto: $%.2f%n",
                        tourist.getName(), finalPrice);
            }

            processed++;
        }
    }

    public void resetStatus() {
        if (status == ZoneStatus.EMERGENCY) {
            this.status = ZoneStatus.OPERATIONAL;
        }
    }

    // Agrega un turista a la cola de espera
    public void enqueue(Tourist tourist) {
        tourist.setStatus(TouristStatus.WAITING);
        waitingQueue.offer(tourist);
    }


    public int getQueueSize()    { return waitingQueue.size(); }
    public double getTicketPrice(){ return baseTicketPrice; }

    // ---------Aqui va a ir mi observer de eventos Observer ---------



    // ---------Override de Interface ParkZone ---------

    @Override public String     getName()             { return "Zona de Arribo"; }
    @Override public boolean    hasCapacity()         { return insidePark.size() < maxCapacity; }
    @Override public int        getCurrentOccupancy() { return insidePark.size(); }
    @Override public int        getMaxCapacity()      { return maxCapacity; }

    @Override
    public void enter(Tourist tourist) {
        waitingQueue.offer(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        insidePark.remove(tourist);
        tourist.setStatus(TouristStatus.EXITED);
    }


}
