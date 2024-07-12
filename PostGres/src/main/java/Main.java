import ConnectionFactory.Database;
import Options.MenuOption;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in); Connection conn = Database.getConnection()) {
            if (conn == null) {
                throw new SQLException("Nu s-a putut conecta la baza de date!");
            }

            MenuOption menu = new MenuOption(conn, scanner);
            menu.run();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
