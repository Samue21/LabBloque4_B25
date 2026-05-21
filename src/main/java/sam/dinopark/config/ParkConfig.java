package sam.dinopark.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Long.getLong;

public final class ParkConfig {
    private static ParkConfig instance;
    private final Properties props;

    // Constructor PRIVADO — nadie puede hacer "new ParkConfig()"
    private ParkConfig() {
        props = new Properties();
        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream("park.properties")) {
            if (in != null) props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar park.properties", e);
        }
    }

    public static synchronized ParkConfig getInstance() {
        if (instance == null) instance = new ParkConfig();
        return instance;
    }

    public int    getInt   (String key, int def)    { return Integer.parseInt(props.getProperty(key, String.valueOf(def))); }
    public double getDouble(String key, double def) { return Double.parseDouble(props.getProperty(key, String.valueOf(def))); }
    public String getString(String key, String def) { return props.getProperty(key, def); }
    public long   getSeed  ()                         { return getLong("simulation.seed", 42L); }
    public int    getTotalSteps()                      { return getInt("simulation.totalSteps", 100); }

    // Solo para tests — resetea la instancia entre test
    static void resetForTesting() { instance = null; }

}
