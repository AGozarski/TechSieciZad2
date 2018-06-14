package adam.g;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookDao {

    private Connection connection;
    private static BookDao instance;
    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "UwolnicOrke0309%^";

    private BookDao() throws SQLException, ClassNotFoundException {
        try {
            connect();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static BookDao getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            synchronized (BookDao.class) {
                if (instance == null) {
                    instance = new BookDao();
                }
            }
        }
        return instance;
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
