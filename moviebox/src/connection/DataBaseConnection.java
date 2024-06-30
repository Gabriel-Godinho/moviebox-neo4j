package connection;

import lombok.Getter;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

@Getter
public class DataBaseConnection {

    private final Driver driver;
    private static DataBaseConnection instance;

    private DataBaseConnection() {
        final String HOSTNAME = "bolt://localhost:7687";
        final String USER_NAME = "neo4j";
        final String PASSWORD = "moviebox234";
        this.driver = GraphDatabase.driver(HOSTNAME, AuthTokens.basic(USER_NAME, PASSWORD));
    }

    public static DataBaseConnection getInstance() {
        if (instance == null) instance = new DataBaseConnection();

        return instance;
    }

    public Session getSession() {
        return this.driver.session();
    }
}


