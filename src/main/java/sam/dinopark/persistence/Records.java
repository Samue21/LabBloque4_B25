package sam.dinopark.persistence;

import java.time.LocalDateTime;

public class Records {
    // Java record: constructor, getters y equals/hashCode automáticos
    public record RevenueRecord(
            long id,
            String type,
            double amount,
            int touristId,
            String zone,
            LocalDateTime timestamp
    ) {

    }

    public record ExpenseRecord(
            long id,
            String type,
            double amount,
            String description,
            LocalDateTime timestamp
    ) {}

    public record EventRecord(
            long step,
            String eventName,
            String description,
            String affectedEntities,
            LocalDateTime timestamp
    ) {}
}
