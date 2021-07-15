package service;

import java.sql.*;
import java.util.Scanner;

public class UserService {

    String loggedUser = "";
    int loggedUserId = 0;

    public void loginUser(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            Scanner sc
    ) {
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.users WHERE username = ? and password = ?";
            pst = connection.prepareStatement(select);
            System.out.print("Please enter username: ");
            String uName = sc.nextLine();
            System.out.print("Please enter password: ");
            String uPass = sc.nextLine();
            pst.setString(1, uName);
            pst.setString(2, uPass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                loggedUser = rs.getString("name");
                loggedUserId = rs.getInt("id");
                System.out.println("ID: " + loggedUserId + ". " + loggedUser + " is connected.");
            } else System.out.println("User: " + uName + ". not registered! ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            Scanner sc
    ) {
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String statement = "INSERT INTO book_shop.users (name, lastname, username, password) VALUES (?,?,?,?)";
            pst = connection.prepareStatement(statement);
            System.out.print("Please enter new name: ");
            String regName = sc.nextLine();
            System.out.print("Please enter new lastname: ");
            String regLastname = sc.nextLine();
            System.out.print("Please enter new username: ");
            String regUser = sc.nextLine();
            System.out.print("Please enter new password: ");
            String regPassword = sc.nextLine();
            pst.setString(1, regName);
            pst.setString(2, regLastname);
            pst.setString(3, regUser);
            pst.setString(4, regPassword);
            pst.executeUpdate();
            System.out.print("User was registered! Please login. \n");
            loginUser(url, user, dbPassword, connection, pst, sc);
        } catch (SQLException e) {
            System.out.println("Connection was unsuccessful!");
            e.printStackTrace();
        }
    }

    public boolean isUserLoggedIn() {
        if (loggedUser.isEmpty()) {
            System.out.println("-- User not connected! --");
            return false;
        }
        System.out.println("-- " + loggedUser + " is connected! --");
        return true;
    }

    public void userBooks(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            ResultSet rs
    ) {
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.books WHERE user_id = ? ORDER BY RAND() LIMIT 10";
            pst = connection.prepareStatement(select);
            pst.setInt(1, loggedUserId);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.print("ID: " + rs.getString("id") + ". ");
                System.out.println("NAME: " + rs.getString("name") + ". ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBookWithId(
            String url,
            String user,
            String dbPassword,
            Connection connection,
            PreparedStatement pst,
            Scanner sc
    ) {
        try {
            connection = DriverManager.getConnection(url, user, dbPassword);
            String addBook = "UPDATE books SET user_id = ? WHERE id = ?";
            pst = connection.prepareStatement(addBook);
            System.out.print("Enter book ID: ");
            String bookId = sc.nextLine();
            pst.setInt(1, loggedUserId);
            pst.setString(2, bookId);
            pst.executeUpdate();
            System.out.println("Book with id: " + bookId + " was added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
