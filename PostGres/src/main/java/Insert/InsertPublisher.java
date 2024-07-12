package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertPublisher extends InsertData {
    public InsertPublisher(Connection conn) {
        super(conn);
    }

    @Override
    public void insert(Scanner scanner, int entries) {
        final String query = "INSERT INTO publishers (publisher_id, name) VALUES (?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            for(int i=0; i<entries; i++){
                try {
                    System.out.println("Introdu id-ul editurii: ");
                    int publisherId;
                    if(scanner.hasNextInt()){
                        publisherId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    System.out.println("Introdu titlul editurii: ");
                    String title = scanner.nextLine();

                    if (digitInString(title)) {
                        throw new InputMismatchException("Titlul trebuie sa contina doar litere!");
                    }

                    pstmt.setInt(1, publisherId);
                    pstmt.setString(2, title);

                    int row = pstmt.executeUpdate();
                    if (row > 0) {
                        System.out.println("Inserarea a fost efectuata cu succes!\n");
                    }
                }catch (SQLException e) {
                    System.out.println("Eroare SQL la inserarea unei edituri: " + e.getMessage());
                } catch (IllegalArgumentException | InputMismatchException e) {
                    System.out.println("Datele introduse nu sunt valide: " + e.getMessage());
                    break;
                }
            }
        }catch (SQLException e){
            System.out.println("Eroare SQL la prepared statement: " + e.getMessage());
        }
    }
}
