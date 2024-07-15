package Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowBooksNotInLoans extends ComplexQueries{
    public ShowBooksNotInLoans(Connection conn) {
        super(conn);
    }

    @Override
    public void executeQuery() {
        final String query = "SELECT title FROM books WHERE book_id NOT IN (select book_id from loans)";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                System.out.println("Cartea: " + title + " nu a fost imprumutata");
            }
        } catch (SQLException e) {
            System.err.println("Eroare la executarea interogarii:" + e.getMessage());
        }
    }
}
