package service;

import java.sql.*;
import java.util.Scanner;

public class BookService {

    public void findBookById(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            ResultSet rs,
            Scanner sc
    ) {
        try {
            System.out.print("Enter book id: ");
            String bookId = sc.nextLine();
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.books WHERE id = ?";
            pst = connection.prepareStatement(select);
            pst.setString(1, bookId);
            rs = pst.executeQuery();
            if (!rs.next()) {
                System.out.println("Book with id: " + bookId + " not found!");
            } else {
                System.out.println("There are " + rs.getString("count") + " books left in the repository");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findBookByName(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            ResultSet rs,
            Scanner sc
    ) {
        System.out.print("Enter book name: ");
        String bName = sc.nextLine();
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.books WHERE name LIKE ?";
            pst = connection.prepareStatement(select);
            pst.setString(1, "%" + bName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.print("ID: " + rs.getString("id") + ". ");
                System.out.println("Title: " + rs.getString("name") + ". ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findBookByPrice(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            ResultSet rs,
            Scanner sc
    ) {
        System.out.print("Enter book price: ");
        String bPrice = sc.nextLine();
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM books WHERE price > ? ORDER BY RAND() LIMIT 10";
            pst = connection.prepareStatement(select);
            pst.setString(1, bPrice);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("name") + ". ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void randomBooks(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            Statement st,
            ResultSet rs
    ) {
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.books ORDER BY RAND() LIMIT 10";
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                System.out.print("ID: " + rs.getString("id") + ". ");
                System.out.print("NAME: " + rs.getString("name") + ". ");
                System.out.print("LANGUAGE: " + rs.getString("language") + ". ");
                System.out.print("COUNT: " + rs.getString("count") + ". ");
                System.out.println("PRICE: " + rs.getString("price") + "$");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
