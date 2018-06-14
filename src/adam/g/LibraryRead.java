package adam.g;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryRead {
    private static Connection dbConnector;

    public static List<Book> readOneBook(String isbnGiven) throws SQLException, ClassNotFoundException {
        dbConnector = BookDao.getInstance().getConnection();
        String insert = "SELECT * FROM books WHERE isbn = ?";
        PreparedStatement statement = dbConnector.prepareStatement(insert);
        statement.setString(1, isbnGiven);
        return read(statement);
    }

    public static void readAllBooks() throws SQLException, ClassNotFoundException {
        dbConnector = BookDao.getInstance().getConnection();
        String insert = "SELECT * FROM books";
        PreparedStatement statement = dbConnector.prepareStatement(insert);
        List<Book> allBooks = read(statement);
        if(allBooks.isEmpty()) {
            System.out.println("Brak książek w bazie danych");
        } else {
            for (Book b : allBooks) {
                System.out.println(b);
            }
        }
    }

    private static List<Book> read(PreparedStatement statement) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = statement.executeQuery();
        String title, author, isbn;
        int id, year;
        List<Book> out = new ArrayList();
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            title = resultSet.getString("title");
            author = resultSet.getString("author");
            year = resultSet.getInt("year");
            isbn = resultSet.getString("isbn");
            out.add(new Book(id, title, author, year, isbn));
        }
        statement.close();
        return out;
    }

    public static void execute() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ISBN");
        String isbn = scanner.nextLine();

        List<Book> foundBook = readOneBook(isbn);
        if (!foundBook.isEmpty()) {
            System.out.println(foundBook.get(0));
        } else System.out.println("Nie odnaleziono książki o podanym ISBN.");

    }

}
