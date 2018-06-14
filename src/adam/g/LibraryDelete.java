package adam.g;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryDelete {
    private static Connection dbConnector;

    public static int deleteBook(String isbn) throws SQLException, ClassNotFoundException {
        dbConnector = BookDao.getInstance().getConnection();
        String insert = "DELETE FROM books WHERE isbn = ?";
        PreparedStatement statement = dbConnector.prepareStatement(insert);
        statement.setString(1, isbn);
        int deleted = statement.executeUpdate();
        statement.close();
        return deleted;
    }
    public static void execute() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer ISBN");
        String isbn = scanner.nextLine();

        int deleted = deleteBook(isbn);
        if (deleted!=0) {
            System.out.println("Usunięto poprawnie wszystkie książki o podanym ISBN.");
        } else System.out.println("Nie udało się usunąć książki. POdano zły ISBN");

    }
}
