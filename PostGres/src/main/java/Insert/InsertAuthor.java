package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertAuthor extends InsertData {
    public InsertAuthor(Connection conn) {
        super(conn);
    }

    @Override
    public void insert(Scanner scanner, int entries) {
        final String query = "INSERT INTO authors (author_id, name, date_of_birth) VALUES (?, ?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            for(int i=0; i<entries; i++){
                try {
                    System.out.println("Introdu id-ul autorului: ");
                    int authorId;
                    if(scanner.hasNextInt()){
                        authorId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    System.out.println("Introdu numele autorului: ");
                    String name = scanner.nextLine();

                    if (digitInString(name)) {
                        throw new InputMismatchException("Numele trebuie sa contina doar litere!");
                    }

                    System.out.println("Introdu data nasterii a autorului in format yyyy-mm-dd: ");
                    String birthdate = scanner.nextLine();

                    pstmt.setInt(1, authorId);
                    pstmt.setString(2, name);
                    pstmt.setDate(3, java.sql.Date.valueOf(birthdate));

                    int row = pstmt.executeUpdate();
                    if (row > 0) {
                        System.out.println("Inserarea a fost efectuata cu succes!\n");
                    }
                } catch (SQLException e) {
                    System.out.println("Eroare SQL la inserarea unui autor: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Datele introduse nu sunt valide: " + e.getMessage());
                    break;
                }catch (NullPointerException | IllegalArgumentException e){
                    System.out.println("Eroare: Data de nastere nu are formatul bun!");
                }
            }
        }catch (SQLException e){
            System.out.println("Eroare SQL la prepared statement: " + e.getMessage());
        }
    }
}
