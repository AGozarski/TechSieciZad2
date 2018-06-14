package adam.g;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryController {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Welcome in my library! What do you want to do?:");
        int selected = -1;
        while (selected!=0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" +
                    "1 - dodaj książkę\n" +
                    "2 - usuń książkę\n" +
                    "3 - zaktualizuj książkę\n" +
                    "4 - wyświetl informacje o książce\n" +
                    "5 - wyświetl wszystkie książki\n" +
                    "0 - zakończ");

            selected = scanner.nextInt();
            switch (selected) {
                case 1:
                    LibrarySave.execute();
                    break;
                case 2:
                    LibraryDelete.execute();
                    break;
                case 3:
                    LibraryUpdate.execute();
                    break;
                case 4:
                    LibraryRead.execute();
                    break;
                case 5:
                    LibraryRead.readAllBooks();
            }
        }
        BookDao.getInstance().disconnect();
    }
}
