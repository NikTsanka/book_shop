package service;

import java.sql.*;
import java.util.Scanner;

public class AuthorService {

    Scanner sc = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/book_shop";
    String user = "root";
    String dbPassword = "root";
    Connection connection = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    public void randomAuthors() {
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.authors ORDER BY RAND() LIMIT 10";
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                System.out.print("ID: " + rs.getString("id") + ". ");
                System.out.println("NAME: " + rs.getString("name") + ". ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getBooksByAuthorId() {
        try {
            System.out.print("Enter author id: ");
            String authorId = sc.nextLine();
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.books WHERE author_id = ?";
            pst = connection.prepareStatement(select);
            pst.setString(1, authorId);
            rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.print("ID: " + rs.getString("id") + ". ");
                    System.out.println("Title: " + rs.getString("name") + ". ");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
