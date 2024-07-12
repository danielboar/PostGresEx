package Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowBooksAndAuthors extends ComplexQueries{
    public ShowBooksAndAuthors(Connection conn) {
        super(conn);
    }

    @Override
    public void executeQuery() {
        final String query = "SELECT b.title, a.name FROM books b JOIN authors a ON b.author_id = a.author_id";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                String name = rs.getString("name");
                System.out.println("Titlul cartii: " + title + ", Autor: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la executarea interogarii:" + e.getMessage());
        }
    }
}
