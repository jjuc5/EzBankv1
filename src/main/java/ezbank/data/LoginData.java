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
 * This class gets the user information including the login name, the user id and 
 * checks the password against the password stored in the database.
 * @author melan
 */
public class LoginData
{
    /**
     * This method finds the user id using the login name that is passed.
     * @param login_name = the customer's login name that they created
     * @return 
     */
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
   
    /**
     * This method finds the login name using the user id that is passed.
     * @param user_id = the user id that was created for a customer
     * @return 
     */
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
   
    /**
     * This method checks the password that was entered at login against the 
     * password that is stored in the users table in the database.
     * @param login = login name
     * @param password = password
     * @return 
     */
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

