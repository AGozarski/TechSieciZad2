package adam.g;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LibrarySave {
    private static Connection dbConnector;

    public static int addBook(String title, String author, int year, String isbn) throws SQLException, ClassNotFoundException {
        dbConnector = BookDao.getInstance().getConnection();
        String insert = "INSERT INTO books (title, author, year, isbn) VALUES(?, ?, ?, ?)";
        PreparedStatement statement = dbConnector.prepareStatement(insert);
        statement.setString(1, title);
        statement.setString(2, author);
        statement.setInt(3, year);
        statement.setString(4, isbn);
        int inserted = statement.executeUpdate(); // 0 lub 1 - dodano do bazy lub nie
        statement.close();
        return inserted;
    }

    public static void execute() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj tytuł");
        String title = scanner.nextLine();
        System.out.println("Podaj autora");
        String author = scanner.nextLine();
        System.out.println("Podaj rok wydania");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Podaj ISBN");
        String isbn = scanner.nextLine();

        int inserted = addBook(title, author, year, isbn);
        if (inserted==1) {
            System.out.println("Dodano książkę");
        } else {
            System.out.println("Nie udało się dodać książki");
        }
    }

}
