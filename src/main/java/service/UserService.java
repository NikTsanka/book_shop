package service;

import java.sql.*;
import java.util.Scanner;

public class UserService {

    Connection connection = null;
    PreparedStatement pst;
    String loggedUser = "";

    public void loginUser(String url, String user, String dbPassword, Scanner sc) {
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
                loggedUser = uName;
                System.out.println(loggedUser + " is connected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(String url, String user, String dbPassword, Scanner sc) {
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
            loginUser(url, user, dbPassword, sc);
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

}
