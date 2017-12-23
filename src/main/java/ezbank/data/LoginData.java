/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.data;

import java.sql.*;


/**
 *
 * @author John
 */
public class LoginData
{
    
    public static int getUserID(String login_name)
    {
        int user_id;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT user_id FROM users WHERE login_name = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, login_name);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                user_id = rs.getInt("user_id");
                return user_id;
            }
            else
            {
               return -1; 
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot find User by login_name: " + login_name, e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static String getLoginName(int user_id)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String login_name = "";
        
        String query = "SELECT login_name FROM users WHERE user_id = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                login_name = rs.getString("login_name");
                return login_name;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot find User by login_name: " + login_name, e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return login_name;
    }
    
    public static boolean checkPassword(String login, String password)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT password FROM users WHERE login_name = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, login);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                String db_password = rs.getString("password");
                if (password.equals(db_password)) 
                {
                    
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot check password", e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return false;
    }
    
}

