import db.Initializer;
import service.AuthorService;
import service.BookService;
import service.UserService;

import java.util.Scanner;

public class Application {

    Scanner sc = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/book_shop";
    String user = "root";
    String dbPassword = "root";

    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        initializer.readAuthorsFile();
        initializer.readBooksFile();
        System.out.println("--- Hello. Welcome to our Book Shop! ---");
        Application app = new Application();
        app.run();

    }

    public void run() {
        UserService userService = new UserService();
        BookService bookService = new BookService();
        AuthorService authorService = new AuthorService();
        boolean isEnabled = true;
        while (isEnabled) {
            System.out.println(
                    "-------------------\n" +
                            "1 - Register.\n" +
                            "2 - Login. \n" +
                            "3 - Random Books. \n" +
                            "4 - Check user status. \n" +
                            "5 - Find books by id. \n" +
                            "6 - . \n" +
                            "7 - . \n" +
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
                    userService.registerUser(url, user, dbPassword, sc);
                    break;
                case "2":
                    userService.loginUser(url, user, dbPassword, sc);
                    break;
                case "3":
                    bookService.randomBooks();
                    break;
                case "4":
                    boolean loggedIn = userService.isUserLoggedIn();
                    if (loggedIn) {
                        userMenu();
                    }
                    break;
                case "5":
                    bookService.findBookWithId();
                    break;
                case "6":

                    break;
                case "7":

                    break;
                case "8":
                    authorService.randomAuthors();
                    break;
                case "9":
                    authorService.getBooksByAuthorId();
                    break;
                case "10":
                    bookService.findBooksByName();
                    break;
                case "11":
                    bookService.findBooksByPrice();
                    break;
                case "12":
                    System.exit(0);
                    break;
            }
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

}
