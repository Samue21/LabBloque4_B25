package sam.dinopark.model;

public class Enums {

    public enum TouristStatus { WAITING, IN_PARK, ATTACKED, EXITED }

    public enum DinosaurStatus { IN_ENCLOSURE, ESCAPED, RECAPTURED }

    public enum PowerPlantStatus { WORKING, BROKEN}

    public enum VehicleStatus { AVAILABLE, IN_USE}

    public enum ExperienceType { BASIC, PREMIUM, VIP }

    public enum EventType { DINOSAUR_ESCAPE, BLACKOUT, STORM, DEALS_HOUR, VEHICLE_FAILURE }

    public enum ZoneStatus { OPERATIONAL, MAINTENANCE, EMERGENCY, CLOSED}


}
