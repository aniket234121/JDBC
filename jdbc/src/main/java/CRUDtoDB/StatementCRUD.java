package CRUDtoDB;

import Entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementCRUD implements AutoCloseable {
    public boolean saveToDB(String name, String email, Connection con) throws SQLException {
        String str = "Insert into users(name,email) values(\"" + name + "\",\"" + email + "\")";
        try (Statement stmt = con.createStatement()) {
            int rowsUpdated = stmt.executeUpdate(str);
            if (rowsUpdated > 0) {
                System.out.println("inserted to DB using statement");
                return true;
            }
        }
        System.out.println("not able to insert values to db using statement");
        return false;
    }

    public boolean removeFromDB(int id, Connection con) throws SQLException {
        String query = "delete from users where id=" + id;
        try (Statement stmt = con.createStatement()) {
            int rowsEffect = stmt.executeUpdate(query);
            if (rowsEffect > 0) {
                System.out.println("remove id by statement");
                return true;
            }
        }
        System.out.println("cannot removed by statement where id: " + id);
        return false;
    }

    public void printAllUsers(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement();) {
            String query = "select * from users";

            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                    System.out.println(user);
                }
            }
        }

    }

    public boolean updateUser(int id, String newName, String newEmail, Connection con) throws SQLException {
        String query = "update users set name='"+newName+"', email='"+newEmail+"' where id=" + id;
        try (Statement stmt = con.createStatement()) {
            int rowsEffected = stmt.executeUpdate(query);
            if (rowsEffected > 0) {
                System.out.println("updated with statement");
                return true;
            }
        }
        System.out.println("not update with statement");
        return false;
    }


    @Override
    public void close() throws Exception {

    }
}
