package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private final Connection CONN;
    private static DataBaseConnection instance;

    private DataBaseConnection() throws SQLException {
        try {
            Class.forName("org.neo4j.jdbc.bolt.BoltDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Ocorreu um erro ao tentar estabelecer a conexão com o banco de dados!");
            System.exit(1);
        }

        final String HOSTNAME = "neo4j@bolt://localhost";
        final String PORT = "7687";
        final String CONNECTION_URL = String.format("%s:%s", HOSTNAME, PORT);
        final String USER_NAME = "neo4j";
        final String PASSWORD = "moviebox123";
        this.CONN = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
    }

    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) instance = new DataBaseConnection();

        return instance;
    }

    public Connection getConn() {
        return this.CONN;
    }

}
