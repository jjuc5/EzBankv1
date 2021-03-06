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
 * This class handles the insertion and retrieval logic for the customer table. 
 * @author melan
 */
public class CustomerData
{

    /**
     * This method inserts a customer into the customer table in the database.
     * @param customer = an object that contains all the customer information 
     */
    public static void insert(Customer customer)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update = "INSERT INTO customer "
                + "(first_name, last_name, street_no, street_name, city, province, "
                + "postal_code, birth_date, tel_no, social_security_no, email, "
                + "Usersuser_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(update,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirst_name().trim());
            ps.setString(2, customer.getLast_name().trim());
            ps.setString(3, customer.getStreet_no().trim());
            ps.setString(4, customer.getStreet_name().trim());
            ps.setString(5, customer.getCity().trim());
            ps.setString(6, customer.getProvince().trim());
            ps.setString(7, customer.getPostal_code().trim());
            ps.setString(8, customer.getBirth_date().trim());
            ps.setString(9, customer.getTel_no());
            ps.setInt(10, customer.getSocial_security_no());
            ps.setString(11, customer.getEmail().trim());
            ps.setInt(12, customer.getUser_id());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
            {
                customer.setCustomer_id(keys.getInt(1));
            }
            else
            {
                throw new RuntimeException("Cannot get the generated key.");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot insert the customer data.", e);
        }
        finally
        {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    /** 
     * This method retrieves the customer data from the customer table in the database.
     * @param id = customer id that is used as the key to retrieve the customer info
     * @return 
     */
    public static Customer get(int id)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null; // to be returned

        String query = "SELECT * FROM customer WHERE customer_id = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next())
            {
                customer = new Customer();
                customer.setCustomer_id(id);
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setStreet_no(rs.getString("street_no"));
                customer.setStreet_name(rs.getString("street_name"));
                customer.setCity(rs.getString("city"));
                customer.setProvince(rs.getString("province"));
                customer.setPostal_code(rs.getString("postal_code"));
                customer.setBirth_date(rs.getString("birth_date"));
                customer.setTel_no(rs.getString("tel_no"));
                customer.setSocial_security_no(rs.getInt("social_security_no"));
                customer.setEmail(rs.getString("email"));
                customer.setUser_id(rs.getInt("Usersuser_id"));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot get the customer record.", e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return customer;
    }
    
}
