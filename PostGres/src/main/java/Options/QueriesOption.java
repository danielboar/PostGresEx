package Options;

import Query.*;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QueriesOption {
    private final Connection conn;
    private final Scanner scanner;

    public QueriesOption(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void execute(){
        while(true) {
            try {
                System.out.println("\nSelecteaza o interogare de la 1-5");
                System.out.println("1. Afiseaza toate cartile impreuna cu autorii lor");
                System.out.println("2. Afiseaza toti membrii si cartile imprumutate de acestia, daca exista");
                System.out.println("3. Afiseaza toate cartile si membrii care le-au imprumutat, daca exista");
                System.out.println("4. Afiseaza toate cartile si editurile lor");
                System.out.println("5. Afiseaza toate cartile ce nu au fost imprumutate");
                System.out.println("6. Spre meniul cu optiuni");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 6) {
                    break;
                } else if (choice > 6 || choice < 1) {
                    throw new RuntimeException("Optiune invalida!\n");
                }

                switch (choice) {
                    case 1:
                        var q1 = new ShowBooksAndAuthors(conn);
                        q1.executeQuery();
                        break;
                    case 2:
                        var q2 = new ShowMembersAndLoanedBooks(conn);
                        q2.executeQuery();
                        break;
                    case 3:
                        var q3 = new ShowBooksAndMembers(conn);
                        q3.executeQuery();
                        break;
                    case 4:
                        var q4 = new ShowBooksAndPublishers(conn);
                        q4.executeQuery();
                        break;
                    case 5:
                        var q5 = new ShowBooksNotInLoans(conn);
                        q5.executeQuery();
                        break;
                }

                System.out.println("\nVrei sa executi alta interogare? (da/nu)");
                String next = scanner.nextLine();
                if (next.equals("nu")) {
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
