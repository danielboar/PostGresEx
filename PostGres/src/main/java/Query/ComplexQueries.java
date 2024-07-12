package Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class ComplexQueries {
    public Connection conn;

    public ComplexQueries(Connection conn) {
        this.conn = conn;
    }

    public abstract void executeQuery();
}
