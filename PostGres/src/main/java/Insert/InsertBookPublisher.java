package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertBookPublisher extends InsertData {
    public InsertBookPublisher(Connection conn) {
        super(conn);
    }

    @Override
    public void insert(Scanner scanner, int entries) {
        final String query = "INSERT INTO bookpublishers (book_id, publisher_id) VALUES (?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            for(int i=0; i<entries; i++){
                try {
                    System.out.println("Introdu id-ul cartii: ");
                    int bookId;
                    if(scanner.hasNextInt()){
                        bookId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    System.out.println("Introdu id-ul editurii: ");
                    int publisherId;
                    if(scanner.hasNextInt()){
                        publisherId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    pstmt.setInt(1, bookId);
                    pstmt.setInt(2, publisherId);

                    int row = pstmt.executeUpdate();
                    if (row > 0) {
                        System.out.println("Inserarea a fost efectuata cu succes!\n");
                    }
                }catch (SQLException e) {
                    System.out.println("Eroare SQL la inserare: " + e.getMessage());
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
