package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class Initializer {

    public static void main(String[] args) {
        readAuthorsFile();
        readBooksFile();
    }

    private static void readAuthorsFile() {
        Path path = Paths.get("src/main/resources/authors.txt");
        String url = "jdbc:mysql://localhost:3306/book_shop";
        String user = "root";
        String password = "root";
        java.sql.Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            String select = "SELECT * FROM book_shop.authors";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            if (!rs.next()) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] split = line.split("/");
                    String name = split[0].trim();
                    int id = Integer.parseInt(split[1].trim());
                    insertAuthors(id, name, connection);
                }
            } else {
                System.out.println("Database is already initialized");
            }
            st.close();
            connection.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readBooksFile() {
        Path path = Paths.get("src/main/resources/books.txt");
        String url = "jdbc:mysql://localhost:3306/book_shop";
        String user = "root";
        String password = "root";
        java.sql.Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            String select = "SELECT * FROM book_shop.books";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            if (!rs.next()) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] split = line.split("/");
                    String name = split[0].trim();
                    int id = Integer.parseInt(split[1].trim());
                    String language = split[2].trim();
                    int count = Integer.parseInt(split[3].trim());
                    double price = Double.parseDouble(split[4].trim());
                    insertBooks(id, name, language, count, price, connection);
                }
            } else {
                System.out.println("Database is already initialized");
            }
            st.close();
            connection.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertAuthors(int id, String name, java.sql.Connection connection) {
        try {
            System.out.println("Connection is open!");
            String statement = "INSERT INTO book_shop.authors (id, name) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection was unsuccessful!");
            e.printStackTrace();
        }
    }

    private static void insertBooks(
            int id,
            String name,
            String language,
            int count,
            double price,
            java.sql.Connection connection
    ) {
        try {
            System.out.println("Connection is open!");
            String statement = "INSERT INTO book_shop.books (id, name, language, count, price ) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, language);
            ps.setInt(4, count);
            ps.setDouble(5, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection was unsuccessful!");
            e.printStackTrace();
        }
    }

}
