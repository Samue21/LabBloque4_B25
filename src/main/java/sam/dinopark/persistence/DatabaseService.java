package sam.dinopark.persistence;


import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;


public class DatabaseService {
    private final Connection connection;

    public DatabaseService(String dbPath) throws Exception {
        // 1. Abrir conexión JDBC
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dinoparksam_bd","postgres","root");
        // 2. Ejecutar Liquibase (crea tablas si no existen)
        runLiquibase();
    }

    private void runLiquibase() throws Exception {
        Database db = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        new Liquibase(
                "db/changelog/db.changelog-master.xml",
                new ClassLoaderResourceAccessor(), db
        ).update(new Contexts());
    }

    // Mismos nombres que CsvWriter para no cambiar el código de las zonas
    public void appendRevenue(Records.RevenueRecord r) {
        String sql = "INSERT INTO revenues(type,amount,touristId,zone,timestamp) VALUES(?,?,?,?,?)";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setString(1, r.type());
            ps.setDouble(2, r.amount());
            ps.setInt(3, r.touristId());
            ps.setString(4, r.zone());
            ps.setTimestamp(5, Timestamp.valueOf(r.timestamp()));
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    public void appendExpense(Records.ExpenseRecord e) {
        String sql = "INSERT INTO expenses(type,amount,description,timestamp) VALUES(?,?,?,?)";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setString(1, e.type());
            ps.setDouble(2, e.amount());
            ps.setString(4, e.description());
            ps.setTimestamp(5, Timestamp.valueOf(e.timestamp()));
            ps.executeUpdate();
        } catch (SQLException ex) { throw new RuntimeException(ex); }
    }
    public void appendEvent  (Records.EventRecord ev) {
        String sql = "INSERT INTO events(step,eventName,description,affectedEntities,timestamp) VALUES(?,?,?,?,?)";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setLong(1, ev.step());
            ps.setString(2, ev.eventName());
            ps.setString(3, ev.description());
            ps.setString(4, ev.affectedEntities());
            ps.setTimestamp(5, Timestamp.valueOf(ev.timestamp()));
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public void close() {
        try { if (connection != null) connection.close(); }
        catch (SQLException ignored) {}
    }
}
