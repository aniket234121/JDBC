package CRUDtoDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatementCRUD implements AutoCloseable {
    public void getAllUsers(Connection con) throws SQLException {
        try (CallableStatement cstmt = con.prepareCall("{call getAllUsers()}")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id") + ", name: " + rs.getString("name") + ", email: " + rs.getString("email"));

            }
        }
    }
    public boolean UpdateUser(Connection con,int id,String name,String email) throws SQLException
    {
        try(CallableStatement cstmt=con.prepareCall("{call updateUserById(?,?,?)}"))
        {
            cstmt.setInt(1,id);
            cstmt.setString(2,name);
            cstmt.setString(3,email);
            int rowsEffect =cstmt.executeUpdate();
            if(rowsEffect>0) {
                System.out.println("update using callable statements");
                return true;
            }
        }
        System.out.println("update failed using callable");
        return false;
    }

    public boolean createUser(Connection con,String name,String email) throws SQLException
    {
        try(CallableStatement cstmt=con.prepareCall("{call createUser(?,?)}"))
        {

            cstmt.setString(1,name);
            cstmt.setString(2,email);
            int rowsEffect =cstmt.executeUpdate();
            if(rowsEffect>0) {
                System.out.println("inserted using callable statements");
                return true;
            }
        }
        System.out.println("insertion failed using callable statement");
        return false;
    }
    public boolean deleteUserById(Connection con,int id)throws SQLException
    {
        try(CallableStatement cstmt=con.prepareCall("{call deleteUserById(?)}"))
        {
            cstmt.setInt(1,id);
            int rows=cstmt.executeUpdate();
            if(rows>0)
            {
                System.out.println("user deleted");
                return true;
            }
            System.out.println("deletion failed");
            return false;

        }
    }

    @Override
    public void close() throws Exception {

    }
}
