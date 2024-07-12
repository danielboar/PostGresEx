package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertLoan extends InsertData{
    public InsertLoan(Connection conn) {
        super(conn);
    }

    @Override
    public void insert(Scanner scanner, int entries) {
        final String query = "INSERT INTO loans (loan_id, book_id, member_id, loan_date) VALUES (?, ?, ?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            for(int i=0; i<entries; i++) {
                try {
                    System.out.println("Introdu id-ul imprumutului: ");
                    int loanId;
                    if(scanner.hasNextInt()){
                        loanId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    System.out.println("Introdu id-ul cartii: ");
                    int bookId;
                    if(scanner.hasNextInt()){
                        bookId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    System.out.println("Introdu id-ul membrului: ");
                    int memberId;
                    if(scanner.hasNextInt()){
                        memberId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    System.out.println("Introdu data imprumutului in format yyyy-mm-dd: ");
                    String date = scanner.nextLine();

                    pstmt.setInt(1, loanId);
                    pstmt.setInt(2, bookId);
                    pstmt.setInt(3, memberId);
                    pstmt.setDate(4, java.sql.Date.valueOf(date));

                    int row = pstmt.executeUpdate();
                    if (row > 0) {
                        System.out.println("Inserarea a fost efectuata cu succes!\n");
                    }
                }catch (SQLException e) {
                    System.out.println("Eroare SQL la inserarea unui imprumut: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Datele introduse nu sunt valide " + e.getMessage());
                    break;
                }catch (NullPointerException | IllegalArgumentException e){
                    System.out.println("Eroare: Data de imprumut nu are formatul bun!");
                }
            }
        }catch (SQLException e){
            System.out.println("Eroare SQL la prepared statement: " + e.getMessage());
        }
    }
}
