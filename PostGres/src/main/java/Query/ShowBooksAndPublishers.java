package Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowBooksAndPublishers extends ComplexQueries{
    public ShowBooksAndPublishers(Connection conn) {
        super(conn);
    }

    @Override
    public void executeQuery() {
        final String query = "SELECT b.title, p.name FROM books b JOIN bookpublishers bp ON b.book_id = bp.book_id " +
                "JOIN publishers p ON bp.publisher_id = p.publisher_id";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                String name = rs.getString("name");
                System.out.println("Cartea: " + title + ", apartine editurii: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la executarea interogarii:" + e.getMessage());
        }
    }
}
