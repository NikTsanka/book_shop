package service;

import java.sql.*;
import java.util.Scanner;

public class AuthorService {

    public void randomAuthors(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            Statement st,
            ResultSet rs
    ) {
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

    public void getBooksByAuthorId(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            ResultSet rs,
            Scanner sc
    ) {
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
