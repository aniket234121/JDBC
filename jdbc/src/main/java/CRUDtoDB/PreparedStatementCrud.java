package CRUDtoDB;

import java.sql.*;
import java.sql.PreparedStatement;

public class PreparedStatementCrud implements AutoCloseable{
    public void getFromDB(Connection con) throws SQLException {
        String query = "select * from users";
        try (PreparedStatement pstms = con.prepareStatement(query)) {
            ResultSet rs = pstms.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id")+", name: "+rs.getString("name"));
            }

        }

    }
    public boolean saveToDB(String name,String email,Connection con) throws SQLException
    {   String query="insert into users(name,email) values(?,?)";

        try(PreparedStatement pstmt=con.prepareStatement(query))
        {
            pstmt.setString(1,name);
            pstmt.setString(2,email);
            int rowsEffect=pstmt.executeUpdate();
            if(rowsEffect>0)
            {
                System.out.println("insert to db using prepared Statement");
                return true;
            }
        }
        System.out.println("insertion failed in prepared Statement");
        return false;
    }
    public boolean updateUser(int id,String name,String email,Connection con) throws SQLException
    {
        String query="update users set name=?,email=? where id=?";
        try(PreparedStatement pstmt=con.prepareStatement(query))
        {   pstmt.setInt(3,id);
            pstmt.setString(1,name);
            pstmt.setString(2,email);
            int rowsEffect=pstmt.executeUpdate();
            if(rowsEffect>0)
            {
                System.out.println("updated using prepared");
                return true;
            }
        }
        System.out.println("failed update using prepared");
        return false;
    }
    public boolean removeFromDB(int id,Connection con)throws SQLException
    {
        String query="delete from users where id=?";
        try(PreparedStatement pstmt=con.prepareStatement(query))
        {
            pstmt.setInt(1,id);
            int rowsEffected=pstmt.executeUpdate();
            if(rowsEffected>0)
            {
                System.out.println("removed from db using prepared");
                return true;
            }
        }
        System.out.println("deletion failed using prepared");
        return false;
    }
    @Override
    public void close() throws Exception {

    }
}
