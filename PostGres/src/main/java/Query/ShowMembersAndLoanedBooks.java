package Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowMembersAndLoanedBooks extends ComplexQueries{
    public ShowMembersAndLoanedBooks(Connection conn) {
        super(conn);
    }

    @Override
    public void executeQuery() {
        final String query = "SELECT m.name, b.title FROM members m JOIN loans l ON m.member_id = l.member_id " +
                "JOIN books b ON l.book_id = b.book_id";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String title = rs.getString("title");
                System.out.println("Membrul: " + name + ", a imprumutat cartea: " + title);
            }
        } catch (SQLException e) {
            System.err.println("Eroare la executarea interogarii:" + e.getMessage());
        }
    }
}
