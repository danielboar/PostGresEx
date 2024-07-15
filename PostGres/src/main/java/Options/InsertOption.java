package Options;

import Insert.*;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertOption {
    private final Connection conn;
    private final Scanner scanner;

    public InsertOption(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void execute(){
        while(true) {
            try {
                System.out.println("\nIn ce tabel vrei sa adaugi de la 1-6?");
                System.out.println("1. Authors");
                System.out.println("2. Books");
                System.out.println("3. Members");
                System.out.println("4. Loans");
                System.out.println("5. Publishers");
                System.out.println("6. BookPublishers");
                System.out.println("7. Spre meniul cu optiuni");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 7) {
                    break;
                } else if (choice > 7 || choice < 1) {
                    System.err.println("Eroare: Optiune invalida!");
                    continue;
                }

                System.out.println("Cate intrari vrei sa introduci?");
                int entries = scanner.nextInt();
                scanner.nextLine();
                if (entries <= 0) {
                    throw new RuntimeException("Intrarile nu pot fi 0 sau negative!");
                }

                switch (choice) {
                    case 1:
                        var insertAuthor = new InsertAuthor(conn);
                        insertAuthor.insert(scanner, entries);
                        break;
                    case 2:
                        var insertBook = new InsertBook(conn);
                        insertBook.insert(scanner, entries);
                        break;
                    case 3:
                        var insertMember = new InsertMember(conn);
                        insertMember.insert(scanner, entries);
                        break;
                    case 4:
                        var insertLoan = new InsertLoan(conn);
                        insertLoan.insert(scanner, entries);
                        break;
                    case 5:
                        var insertPublishers = new InsertPublisher(conn);
                        insertPublishers.insert(scanner, entries);
                        break;
                    case 6:
                        var insertBookPublishers = new InsertBookPublisher(conn);
                        insertBookPublishers.insert(scanner, entries);
                        break;
                }

                System.out.println("Vreti sa introduceti alte date? (da/nu)");
                String data = scanner.nextLine();
                if (data.equals("nu")) {
                    break;
                }
            }catch (InputMismatchException e) {
                System.err.println("Eroare: Trebuie sa introduci doar cifre");
                scanner.nextLine();
            }catch (RuntimeException e) {
                System.err.println("Eroare: " + e.getMessage());
            }
        }
    }
}
