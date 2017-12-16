
package sheridan.studentdb.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sheridan.studentdb.business.Assistant;
import sheridan.studentdb.business.Student;
import sheridan.studentdb.login.SaltedPassword;

/**
 *
 * @author John Urbanowicz
 */
public class AssistantData 
{
    public static void insert(Assistant assistant) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String userUpdate = "INSERT INTO users (user_login, user_password)"
                + "VALUES (?, ?)";
        
        String roleUpdate = "INSERT INTO roles (user_login, user_role)"
                + "VALUES (?, 'assistant')";
                
        try {
            ps = connection.prepareStatement(userUpdate,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, assistant.getUsername().trim());
            ps.setString(2, SaltedPassword.encode(assistant.getPassword().trim()));
            ps.executeUpdate();
            
            ps = connection.prepareStatement(roleUpdate,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, assistant.getUsername().trim());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot insert the assistant data.", e);
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Assistant> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Assistant> assistants = new ArrayList<>();

        String query = "SELECT * FROM roles";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Assistant assistant = new Assistant();
                assistant.setUsername(rs.getString("user_login"));
                assistant.setRole(rs.getString("user_role"));
                assistants.add(assistant);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot select the list of all assistants.", e);
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closeStatement(st);
            pool.freeConnection(connection);
        }
        return assistants;
    }


    public static void delete(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String delUserAcc1 = "DELETE FROM roles WHERE user_login = ?";
        String delUserAcc2 = "DELETE FROM users WHERE user_login = ?";
        
        try {
            ps = connection.prepareStatement(delUserAcc1);
            ps.setString(1, username);
            ps.executeUpdate();
            
            ps = connection.prepareStatement(delUserAcc2);
            ps.setString(1, username);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Cannot delete the assistant record.", e);
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static Assistant getAssist(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Assistant assistant = null; // to be returned

        String query = "SELECT * FROM roles WHERE user_login = ?";
        
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                assistant = new Assistant();
                assistant.setUsername(rs.getString("user_login"));
                assistant.setRole(rs.getString("user_role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get the assistant record.", e);
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return assistant;
    }

    public static void update(Assistant assistant) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String updateUser = "UPDATE roles "
                + "SET user_role = ? "
                + "WHERE user_login = ?";
        
        
        try {
            ps = connection.prepareStatement(updateUser);
            ps.setString(1, assistant.getRole());
            ps.setString(2, assistant.getUsername());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Cannot update the assistant record.", e);
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
