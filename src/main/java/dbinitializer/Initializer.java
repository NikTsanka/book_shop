package dbinitializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class Initializer {

    String url = "jdbc:mysql://localhost:3306/book_shop";
    String user = "root";
    String password = "root";
    Path path;
    Connection connection = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    public void readAuthorsFile() {
        path = Paths.get("src/main/resources/authors.txt");
        try {
            connection = DriverManager.getConnection(url, user, password);
            String select = "SELECT * FROM book_shop.authors";
            st = connection.createStatement();
            rs = st.executeQuery(select);
            if (!rs.next()) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] split = line.split("/");
                    String name = split[0].trim();
                    int id = Integer.parseInt(split[1].trim());
                    insertAuthors(id, name, connection);
                    System.out.println("All authors added in database");
                }
            } else {
                System.out.println("Author database is already initialized");
            }
            st.close();
            connection.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void readBooksFile() {
        path = Paths.get("src/main/resources/books.txt");
        try {
            connection = DriverManager.getConnection(url, user, password);
            String select = "SELECT * FROM book_shop.books";
            st = connection.createStatement();
            rs = st.executeQuery(select);
            if (!rs.next()) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] split = line.split("/");
                    String name = split[0].trim();
                    int authorId = Integer.parseInt(split[1].trim());
                    String language = split[2].trim();
                    int count = Integer.parseInt(split[3].trim());
                    double price = Double.parseDouble(split[4].trim());
                    insertBooks(authorId, name, language, count, price, connection);
                    System.out.println("All books added in database");
                }
            } else {
                System.out.println("Book Database is already initialized");
            }
            st.close();
            connection.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertAuthors(int id, String name, java.sql.Connection connection) {
        try {
            String statement = "INSERT INTO book_shop.authors (id, name) VALUES (?, ?)";
            pst = connection.prepareStatement(statement);
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection was unsuccessful!");
            e.printStackTrace();
        }
    }

    private void insertBooks(
            int authorId,
            String name,
            String language,
            int count,
            double price,
            java.sql.Connection connection
    ) {
        try {
            String statement = "INSERT INTO book_shop.books (author_id, name, language, count, price ) VALUES (?,?,?,?,?)";
            pst = connection.prepareStatement(statement);
            pst.setInt(1, authorId);
            pst.setString(2, name);
            pst.setString(3, language);
            pst.setInt(4, count);
            pst.setDouble(5, price);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection was unsuccessful!");
            e.printStackTrace();
        }
    }

}
