package Options;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuOption {
    private final InsertOption insertOption;
    private final QueriesOption queriesOption;
    private final Scanner scanner;

    public MenuOption(Connection conn, Scanner scanner) {
        this.insertOption = new InsertOption(conn, scanner);
        this.queriesOption = new QueriesOption(conn, scanner);
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Alege o optiune:");
                System.out.println("1. Adauga date in tabele");
                System.out.println("2. Executa interogari");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();

                if (choice == 3) {
                    System.out.println("La revedere!");
                    break;
                }else if(choice > 3 || choice < 1){
                    throw new RuntimeException("Optiune invalida\n");
                }

                switch (choice) {
                    case 1:
                        System.out.println("Ati ales optiunea de a adauga date in tabela!");
                        insertOption.execute();
                        break;
                    case 2:
                        System.out.println("Ati ales optiunea de a executa interogari!");
                        queriesOption.execute();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Eroare: Trebuie sa introduci doar cifre");
                scanner.nextLine();
            }catch (RuntimeException e) {
                System.out.println("Eroare: " + e.getMessage());
            }
        }
    }
}
