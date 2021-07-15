import dbinitializer.Initializer;
import service.AuthorService;
import service.BookService;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Application {

    private final String url = "jdbc:mysql://localhost:3306/book_shop";
    private final String user = "root";
    private final String dbPassword = "root";
    Scanner sc = new Scanner(System.in);
    Connection connection = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    UserService userService;
    BookService bookService;
    AuthorService authorService;

    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        initializer.readAuthorsFile();
        initializer.readBooksFile();
        System.out.println("--- Hello. Welcome to our Book Shop! ---");
        Application app = new Application();
        app.run();

    }

    private void run() {
        userService = new UserService();
        bookService = new BookService();
        authorService = new AuthorService();
        boolean isEnabled = true;
        while (isEnabled) {
            System.out.println(
                    "-------------------\n" +
                            "1 - Register.\n" +
                            "2 - Login. \n" +
                            "3 - Random Books. \n" +
                            "4 - Check user status. \n" +
                            "5 - Find books by id. \n" +
                            "6 - Show user books. \n" +
                            "7 - Add user books. \n" +
                            "8 - Random Authors. \n" +
                            "9 - Find books by author id. \n" +
                            "10 - Find books by name. \n" +
                            "11 - Find books by price. \n" +
                            "12 - exit "

            );
            System.out.println("-------------------");
            System.out.print("Please choice operation: ");
            String symbol = sc.nextLine();
            switch (symbol) {
                case "1":
                    userService.registerUser(url, user, dbPassword, connection, pst, sc);
                    break;
                case "2":
                    userService.loginUser(url, user, dbPassword, connection, pst, sc);
                    break;
                case "3":
                    bookService.randomBooks(url, user, dbPassword, connection, st, rs);
                    break;
                case "4":
                    boolean loggedIn = userService.isUserLoggedIn();
                    if (loggedIn) {
                        userMenu();
                    }
                    break;
                case "5":
                    bookService.findBookById(url, user, dbPassword, connection, pst, rs, sc);
                    break;
                case "6":
                    userService.userBooks(url, user, dbPassword, connection, pst, rs);
                    break;
                case "7":
                    userService.addBookWithId(url, user, dbPassword, connection, pst, sc);
                    break;
                case "8":
                    authorService.randomAuthors(url, user, dbPassword, connection, st, rs);
                    break;
                case "9":
                    authorService.getBooksByAuthorId(url, user, dbPassword, connection, pst, rs, sc);
                    break;
                case "10":
                    bookService.findBookByName(url, user, dbPassword, connection, pst, rs, sc);
                    break;
                case "11":
                    bookService.findBookByPrice(url, user, dbPassword, connection, pst, rs, sc);
                    break;
                case "12":
                    System.exit(0);
                    break;
            }
        }
    }

    private void userMenu() {
//        boolean isEnabled = true;
//        while (isEnabled) {
        System.out.println(
                "Please choice operation: \n" +
                        "1 - My Books.\n" +
                        "2 - Add Books. \n" +
                        "3 - Home menu. \n" +
                        "4 - exit"
        );
        String symbol = sc.nextLine();
        switch (symbol) {
            case "1":
                userService.userBooks(url, user, dbPassword, connection, pst, rs);
                break;
            case "2":
                userService.addBookWithId(url, user, dbPassword, connection, pst, sc);
                break;
            case "3":
                run();
                break;
            case "4":
                System.exit(0);
                break;
        }
//        }
    }

}
