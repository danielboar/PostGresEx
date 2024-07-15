package Query;

import java.sql.Connection;

public abstract class ComplexQueries {
    public Connection conn;

    public ComplexQueries(Connection conn) {
        this.conn = conn;
    }

    public abstract void executeQuery();
}
