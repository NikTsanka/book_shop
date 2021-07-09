import db.Initializer;

import java.sql.*;
import java.util.Scanner;

public class Application {

    Scanner sc = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/book_shop";
    String user = "root";
    String dbPassword = "root";
    Connection connection = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    String loggedUser = "";

    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        initializer.readAuthorsFile();
        initializer.readBooksFile();
        System.out.println("Hello. Welcome to our Book Shop!");
        Application app = new Application();
        app.run();

    }

    public void run() {
        System.out.println(
                "Please choice operation: \n" +
                        "1 - Register.\n" +
                        "2 - Login. \n" +
                        "3 - Random Books. \n" +
                        "4 - Check user status. \n" +
                        "5 - Find books with id. \n" +
                        "12 - exit"
        );

        String symbol = sc.nextLine();
        switch (symbol) {
            case "1":
                registerUser(url, user, dbPassword, sc);
                break;
            case "2":
                loginUser(url, user, dbPassword, sc);
                break;
            case "3":
                randomBooks();
                break;
            case "4":
                isUserLoggedIn();
                break;
            case "5":
                findBookWithId();
                break;
            case "12":
                System.exit(0);
                break;
        }
    }

    public void userMenu() {
        System.out.println(
                "Please choice operation: \n" +
                        "1 - My Books.\n" +
                        "2 - Add Books. \n" +
                        "3 - Delete Books. \n" +
                        "4 - Home menu. \n" +
                        "5 - exit"
        );
        String symbol = sc.nextLine();
        switch (symbol) {
            case "1":

                break;
            case "2":

                break;
            case "3":

                break;
            case "4":
                run();
                break;
            case "5":
                System.exit(0);
                break;
        }
    }

    private void loginUser(String url, String user, String dbPassword, Scanner sc) {
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
                userMenu();
            } else {
                System.out.println("User not found. Please register or login with other username");
                run();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerUser(String url, String user, String dbPassword, Scanner sc) {
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

    private void randomBooks() {
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

    private void isUserLoggedIn() {
        if (loggedUser.isEmpty()) {
            System.out.println("-- User not connected! --");
            run();
        } else {
            System.out.println("-- " + loggedUser + " is connected! --");
            userMenu();
        }
    }

    private void findBookWithId() {
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
                run();
            } else {
                System.out.println("There are " + rs.getString("count") + " books left in the repository");
                run();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
