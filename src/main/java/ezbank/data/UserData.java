/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.data;

import java.sql.*;

import ezbank.business.Customer;
/**
 * This class inserts data into the users table in the database.
 * @author melan
 */
public class UserData
{

    /**
     * This method inserts data into the users table in the database.
     * The customer object contains the user name and password which is passed
     * from the account summary form.
     * @param customer = contains all the personal information about a customer.
     */
    public static void insert(Customer customer)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update = "INSERT INTO users "
                + "(login_name, password, User_Typesuser_type) "
                + "VALUES (?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(update,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getUsername().trim());
            ps.setString(2, customer.getPassword().trim());
            //ps.setString(2, SaltedPassword.encode(customer.getPassword().trim()));
            ps.setInt(3, 1);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
            {
                customer.setUser_id(keys.getInt(1));
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

}
