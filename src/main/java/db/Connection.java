package db;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;


public class Connection {

    public static void main(String[] args) {
        readAuthorsFile();
    }

    private static void readAuthorsFile() {
        Path path = Paths.get("src/main/resources/authors.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] split = line.split("/");
                String name = split[0].trim();
                int id = Integer.parseInt(split[1].trim());
                insertAuthor(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertAuthor(int id, String name) {

        String url = "jdbc:mysql://localhost:3306/book_shop";
        String user = "root";
        String password = "root";

        try {
            java.sql.Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is open!");
            String statement = "INSERT INTO book_shop.autors (id, name) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Connection was unsuccessful!");
            e.printStackTrace();
        }
    }

}
