package ezbank.data;

import java.sql.*;

import ezbank.business.Customer;

public class CustomerData
{

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
/*
    public static ArrayList<Student> getAll()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT * FROM student";
        try
        {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next())
            {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setProgram(rs.getString("program_name"));
                student.setYear(Integer.toString(rs.getInt("program_year")));
                student.setCoop(rs.getBoolean("program_coop") ? "yes" : "no");
                students.add(student);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot select the list of all students.", e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closeStatement(st);
            pool.freeConnection(connection);
        }
        return students;
    }

    public static void delete()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement st = null;

        String update = "TRUNCATE TABLE student";
        try
        {
            st = connection.createStatement();
            st.executeUpdate(update);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot clear the student table.", e);
        }
        finally
        {
            DatabaseUtil.closeStatement(st);
            pool.freeConnection(connection);
        }
    }

    public static void delete(int id)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update = "DELETE FROM student WHERE id = ?";
        try
        {
            ps = connection.prepareStatement(update);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot delete the student record.", e);
        }
        finally
        {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
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
/*
    public static void update(Student student)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update
                = "UPDATE student SET "
                + "first_name = ?, last_name = ?, "
                + "program_name = ?, program_year = ?, program_coop = ? "
                + "WHERE id = ?";
        try
        {
            ps = connection.prepareStatement(update);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getProgram());
            ps.setInt(4, Integer.parseInt(student.getYear()));
            ps.setBoolean(5, student.getCoop().equals("yes"));
            ps.setInt(6, student.getId());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot update the student record.", e);
        }
        finally
        {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
*/
}
