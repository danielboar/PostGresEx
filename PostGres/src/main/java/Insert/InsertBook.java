package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertBook extends InsertData{
    public InsertBook(Connection conn) {
        super(conn);
    }

    @Override
    public void insert(Scanner scanner, int entries) {
        final String query = "INSERT INTO books (book_id, title, author_id) VALUES (?, ?, ?)";

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

                    System.out.println("Introdu titlul cartii: ");
                    String title = scanner.nextLine();
                    if (digitInString(title)) {
                        throw new InputMismatchException("Titlul trebuie sa contina doar litere!");
                    }

                    System.out.println("Introdu id-ul autorului: ");
                    int authorId;
                    if(scanner.hasNextInt()){
                        authorId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    pstmt.setInt(1, bookId);
                    pstmt.setString(2, title);
                    pstmt.setInt(3, authorId);

                    int row = pstmt.executeUpdate();
                    if (row > 0) {
                        System.out.println("Inserarea a fost efectuata cu succes!\n");
                    }
                }catch (SQLException e) {
                    System.out.println("Eroare SQL la inserarea unei carti: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Datele introduse nu sunt valide: " + e.getMessage());
                    break;
                }
            }
        }catch (SQLException e){
            System.out.println("Eroare SQL la prepared statement " + e.getMessage());
        }
    }
}
