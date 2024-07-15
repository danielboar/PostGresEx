package Insert;

import java.sql.Connection;
import java.util.Scanner;

public abstract class InsertData {
    public Connection conn;

    public InsertData(Connection conn) {
        this.conn = conn;
    }

    public abstract void insert(Scanner scanner, int entries);

    public boolean digitInString(String str){
        boolean ok = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                ok = true;
                break;
            }
        }

        return ok;
    }
}
