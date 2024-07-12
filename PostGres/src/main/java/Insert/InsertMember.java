package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertMember extends InsertData{
    public InsertMember(Connection conn) {
        super(conn);
    }

    @Override
    public void insert(Scanner scanner, int entries) {
        final String query = "INSERT INTO members (member_id, name, join_date) VALUES (?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            for(int i=0; i<entries; i++) {
                try {
                    System.out.println("Introdu id-ul membrului: ");
                    int memberId;
                    if(scanner.hasNextInt()){
                        memberId = scanner.nextInt();
                        scanner.nextLine();
                    }else{
                        throw new InputMismatchException("Nu s-a introdus un id format din cifre!");
                    }

                    System.out.println("Introdu numele membrului: ");
                    String name = scanner.nextLine();

                    if (digitInString(name)) {
                        throw new InputMismatchException("Numele trebuie sa contina doar litere!");
                    }

                    System.out.println("Introdu data de inscriere a membrului in format yyyy-mm-dd: ");
                    String date = scanner.nextLine();

                    pstmt.setInt(1, memberId);
                    pstmt.setString(2, name);
                    pstmt.setDate(3, java.sql.Date.valueOf(date));

                    int row = pstmt.executeUpdate();
                    if (row > 0) {
                        System.out.println("Inserarea a fost efectuata cu succes!");
                    }
                } catch (SQLException e) {
                    System.out.println("Eroare SQL la inserarea unui membru: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Datele introduse nu sunt valide: " + e.getMessage());
                    break;
                }catch (NullPointerException | IllegalArgumentException e){
                    System.out.println("Eroare: Data de inscriere nu are formatul bun!");
                }
            }
        }catch (SQLException e){
            System.out.println("Eroare SQL la prepared statement: " + e.getMessage());
        }
    }
}
