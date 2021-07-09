package service;

import java.sql.*;
import java.util.Scanner;

public class UserService {

   /*
    Application application = new Application();
    Scanner sc = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/book_shop";
    String user = "root";
    String dbPassword = "root";
    Connection connection = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
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
                application.userMenu();
            } else {
                System.out.println("User not found. Please register or login with other username");
                application.run();
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

    public void randomBooks() {
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

    public void isUserLoggedIn() {
        if (loggedUser.isEmpty()) {
            System.out.println("-- User not connected! --");
            application.run();
        } else {
            System.out.println("-- " + loggedUser + " is connected! --");
            application.userMenu();
        }
    }
    */


}
