package ezbank.data;

import sheridan.studentdb.data.*;
import java.sql.*;
import java.util.*;

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
            ps.setString(7, customer.getBirth_date().trim());
            ps.setInt(8, customer.getTel_no());
            ps.setInt(9, customer.getSocial_security_no());
            ps.setString(10, customer.getEmail().trim());
            ps.setString(11, customer.getBirth_date().trim());
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

    public static Student get(int id)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null; // to be returned

        String query = "SELECT * FROM student WHERE id = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next())
            {
                student = new Student();
                student.setId(id);
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setProgram(rs.getString("program_name"));
                student.setYear(Integer.toString(rs.getInt("program_year")));
                student.setCoop(rs.getBoolean("program_coop") ? "yes" : "no");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot get the student record.", e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return student;
    }

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

}
