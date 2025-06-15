package Service;

import CRUDtoDB.PreparedStatementCrud;
import CRUDtoDB.StatementCRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class ConnectionCreation {

    public static int handleStatement(Connection con, Scanner sc) throws Exception {
        try (StatementCRUD stmtCrud = new StatementCRUD()) {
            int option = 0;
            while (option != 5) {
                System.out.println("=====statement arena======");
                System.out.println("1.Create user");
                System.out.println("2.Read user");
                System.out.println("3.Update user");
                System.out.println("4.Delete user");
                System.out.println("5.Return to Main Menu");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("enter name of user:- ");
                        String name = sc.next();
                        System.out.println("enter email of user:- ");
                        String email = sc.next();
                        stmtCrud.saveToDB(name, email, con);
                        break;
                    case 2:
                        stmtCrud.printAllUsers(con);
                        break;
                    case 3:
                        System.out.println("Id of User to Update");
                        int id = sc.nextInt();
                        System.out.println("enter new Name");
                        String newname = sc.next();
                        System.out.println("enter new Email");
                        String newemail = sc.next();
                        stmtCrud.updateUser(id, newname, newemail, con);
                        break;
                    case 4:
                        System.out.println("enter user id to delete");
                        id = sc.nextInt();
                        stmtCrud.removeFromDB(id, con);
                        break;
                    case 5:
                        System.out.println("Returning to main menu...");
                        System.out.println("============================");
                        return 0;
                }
            }
        }
        return 0;
    }
    public static int handlePreparedStatement(Connection con, Scanner sc) throws Exception {
        try (PreparedStatementCrud pstmtCrud = new PreparedStatementCrud()) {
            int option = 0;
            while (option != 5) {
                System.out.println("=====Prepared statement arena======");
                System.out.println("1.Create user");
                System.out.println("2.Read user");
                System.out.println("3.Update user");
                System.out.println("4.Delete user");
                System.out.println("5.Return to Main Menu");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("enter name of user:- ");
                        String name = sc.next();
                        System.out.println("enter email of user:- ");
                        String email = sc.next();
                        pstmtCrud.saveToDB(name, email, con);
                        break;
                    case 2:
                        pstmtCrud.getFromDB(con);
                        break;
                    case 3:
                        System.out.println("Id of User to Update");
                        int id = sc.nextInt();
                        System.out.println("enter new Name");
                        String newname = sc.next();
                        System.out.println("enter new Email");
                        String newemail = sc.next();
                        pstmtCrud.updateUser(id, newname, newemail, con);
                        break;
                    case 4:
                        System.out.println("enter user id to delete");
                        id = sc.nextInt();
                        pstmtCrud.removeFromDB(id, con);
                        break;
                    case 5:
                        System.out.println("Returning to main menu...");
                        System.out.println("============================");
                        return 0;
                }
            }
        }
        return 0;
    }
    public void start() {
        String url = "jdbc:mysql://localhost:3306/testJDBC";
        String userName = "newuser";
        String password = "password";

        try (Connection con = DriverManager.getConnection(url, userName, password); Scanner sc = new Scanner(System.in)) {
            System.out.println("connection established");
            int choice = 0;
            while (choice != 4) {

                System.out.println("--------------------------------------------------");
                System.out.println("choose your death:-");
                System.out.println("1.Use Statement");
                System.out.println("2.Use Prepared Statement");
                System.out.println("3.Use Callable Statement");
                System.out.println("4. Exit");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        handleStatement(con, sc); // Scanner passed here
                        break;
                    case 2:
                        handlePreparedStatement(con,sc);
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            System.out.println("Exception handled: " + e.getMessage());
        }
    }
}
