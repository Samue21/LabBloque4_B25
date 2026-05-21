package sam.dinopark.model.transaction;

public class SatisfactionSurvey {
    private final int id;
    private final int touristId;
    private final int enclosureId;
    private final int score;

    public SatisfactionSurvey(int id, int touristId, int enclosureId, int score) {
        this.id = id;
        this.touristId = touristId;
        this.enclosureId = enclosureId;
        this.score = score;
    }

    public int getId() { return id; }

    public int getTouristId() {
        return touristId;
    }

    public int getEnclosureId() {
        return enclosureId;
    }

    public int getScore() {
        return score;
    }
}
