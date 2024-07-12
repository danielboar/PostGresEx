package Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowBooksAndMembers extends ComplexQueries{
    public ShowBooksAndMembers(Connection conn) {
        super(conn);
    }

    @Override
    public void executeQuery() {
        final String query = "SELECT b.title, m.name FROM books b JOIN loans l ON b.book_id = l.book_id "
                + "JOIN members m ON l.member_id = m.member_id";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                String name = rs.getString("name");
                System.out.println("Cartea: " + title + ", a fost imprumutata de catre: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la executarea interogarii:" + e.getMessage());
        }
    }
}
