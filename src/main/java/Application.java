import java.sql.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        String url = "jdbc:mysql://localhost:3306/book_shop";
        String user = "root";
        String dbPassword = "root";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello. Welcome to our Book Shop!");
        System.out.println(
                "Please choice an operation: \n" +
                        "1 - Login.\n" +
                        "2 - Register. \n" +
                        "3 - Choice book. \n" +
                        "4 - exit"
        );

        String symbol = sc.nextLine();
        switch (symbol) {
            case "1":
                loginUser(url, user, dbPassword, sc);
                break;
            case "2":
                registerUser(url, user, dbPassword, sc);
                break;
                /** Insert
                 try {
                 Connection connection = DriverManager.getConnection(url,user,dbPassword);
                 System.out.println("Connection is open!");
                 String statement = "INSERT INTO book_shop.users (username, password) VALUES (?, ?)";
                 PreparedStatement ps = connection.prepareStatement(statement);
                 ps.setString(1, username);
                 ps.setString(2, userPassword);
                 ps.executeUpdate();

                 } catch (SQLException e) {
                 System.out.println("Connection was unsuccessful!");
                 e.printStackTrace();
                 }*/
        }
    }

    private static void loginUser(String url, String user, String dbPassword, Scanner sc) {
        try {
            Connection connection = DriverManager.getConnection(url, user, dbPassword);
            String select = "SELECT * FROM book_shop.users WHERE username = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(select);
            System.out.print("Please enter username: ");
            String username = sc.nextLine();
            System.out.print("Please enter password: ");
            String userPassword = sc.nextLine();
            ps.setString(1, username);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("User is connected!");
            } else {
                System.out.println("User not found. Please register!...");
                registerUser(url, user, dbPassword, sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registerUser(String url, String user, String dbPassword, Scanner sc) {
        try {
            Connection connection = DriverManager.getConnection(url, user, dbPassword);
            String statement = "INSERT INTO book_shop.users (name, lastname, username, password) VALUES (?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(statement);
            System.out.print("Please enter new name: ");
            String regName = sc.nextLine();
            System.out.print("Please enter new lastname: ");
            String regLastname = sc.nextLine();
            System.out.print("Please enter new username: ");
            String regUser = sc.nextLine();
            System.out.print("Please enter new password: ");
            String regPassword = sc.nextLine();
            ps1.setString(1, regName);
            ps1.setString(2, regLastname);
            ps1.setString(3, regUser);
            ps1.setString(4, regPassword);
            ps1.executeUpdate();
            System.out.print("User was registered!");
        } catch (SQLException e) {
            System.out.println("Connection was unsuccessful!");
            e.printStackTrace();
        }
    }

    /** select
     try {
     Connection connection = DriverManager.getConnection(url, user, dbPassword);
     String select = "SELECT * FROM book_shop.books";
     Statement st = connection.createStatement();
     ResultSet rs = st.executeQuery(select);
     while (rs.next()) {
     System.out.println(rs.getString(1));
     System.out.println(rs.getString(2));
     System.out.println(rs.getString(3));
     System.out.println(rs.getString(4));
     System.out.println(rs.getString(5));

     }
     } catch (SQLException e) {
     e.printStackTrace();
     }
     */

    /** //  ResultSet rs = st.executeQuery(select);
     while (rs.next()) {
     String uName = rs.getString("username");
     String uPass = rs.getString("password");
     if (uName.equals(username) && uPass.equals(userPassword)) {
     System.out.println("User is connected!");
     } else {
     System.out.println("User not found. Please register");
     try {
     String statement = "INSERT INTO book_shop.users (username, password) VALUES (?, ?)";
     PreparedStatement ps1 = connection.prepareStatement(statement);
     System.out.print("Please enter username: ");
     ps1.setString(1, username);
     System.out.print("Please enter password: ");
     ps1.setString(2, userPassword);
     ps1.executeUpdate();

     } catch (SQLException e) {
     System.out.println("Connection was unsuccessful!");
     e.printStackTrace();
     }
     }
     }*/
}
