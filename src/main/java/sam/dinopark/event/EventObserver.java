package sam.dinopark.event;

import sam.dinopark.model.Enums.*;
import sam.dinopark.simulation.ParkState;

/** mi intencion es que todos los compnentes que quieran ser notificados
 * que quieran ser notificados cuando ocurre un evento en el parque.
 */
public interface EventObserver {
    void onParkEvent(SimulationEvent event, ParkState state);

    // Opcional: el observador declara a qué tipos de evento le interesa
    default boolean isInterestedIn(EventType type) { return true; }
}
