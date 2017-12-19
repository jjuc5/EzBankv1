package ezbank.data;

import ezbank.business.Login;
import java.sql.*;

import ezbank.login.SaltedPassword;

/**
 *
 * @author Alex
 */
public class LoginData
{

   public static void insertLogin(Login login)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update = "INSERT INTO users "
                + "(login_name, password, Usersuser_type) " 
                + "VALUES (?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(update,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, login.getLogin_name().trim());
            ps.setString(2, SaltedPassword.encode(login.getPassword().trim()));
            ps.setInt(3, 1);

            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
            {
                login.setUsersuser_type(keys.getInt(1));
            }
            else
            {
                throw new RuntimeException("Cannot get the generated key.");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot insert the user data.", e);
        }
        finally
        {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
    
       
    public static void updatePassword(String login, String password)
    {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update
                = "UPDATE users SET password = ? WHERE login_name = ?";

        try
        {
            ps = connection.prepareStatement(update);
            ps.setString(1, SaltedPassword.encode(password));
            ps.setString(2, login);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot update password", e);
        }
        finally
        {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String resetPassword(String login)
    {

        String password = SaltedPassword.random(8);
        updatePassword(login, password);

        return password;
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
                return SaltedPassword.match(password, rs.getString("password"));
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
    }

}
