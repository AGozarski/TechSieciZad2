package adam.g;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryUpdate {

    private static Connection dbConnector;

    public static int updateBook(String title, String author, int year, String isbn) throws SQLException, ClassNotFoundException {
        dbConnector = BookDao.getInstance().getConnection();
        String insert = "UPDATE books SET title = ?, author = ?, year = ? WHERE isbn = ?";
        PreparedStatement statement = dbConnector.prepareStatement(insert);
        statement.setString(1, title);
        statement.setString(2, author);
        statement.setInt(3, year);
        statement.setString(4, isbn);
        int updated = statement.executeUpdate(); // 0 lub 1 - dodano do bazy lub nie
        statement.close();
        return updated;
    }

    public static void execute() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ISBN");
        String isbn = scanner.nextLine();

        List<Book> foundBook = LibraryRead.readOneBook(isbn);

        if (!foundBook.isEmpty()) {
            System.out.println("Podaj nowy tytuł");
            String title = scanner.nextLine();
            System.out.println("Podaj nowego autora");
            String author = scanner.nextLine();
            System.out.println("Podaj nowy rok wydania");
            int year = Integer.parseInt(scanner.nextLine());

            int updated = updateBook(title, author, year, isbn);
            if (updated == 1) {
                System.out.println("Zaktualizowano książkę");
            } else if (updated > 1) {
                System.out.println("Zaktualizowano książki w liczbie " + updated);
            } else {
                System.out.println("Nie udało się zaktualizować książki");
            }
        } else System.out.println("W bazie nie ma książki o podanym ISBN.");
    }

}
