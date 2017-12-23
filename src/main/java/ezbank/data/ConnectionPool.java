/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This class establishes a connection pool with the database.
 * @author melan
 */
public class ConnectionPool
{

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    /**
     * This method makes the connection pool with the banking database
     */
    private ConnectionPool()
    {
        try
        {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/banking");
        }
        catch (NamingException e)
        {
            throw new RuntimeException("Cannot make the connection pool.", e);
        }
    }

    /**
     * This method creates an instance of the connection that was created/
     * @return 
     */
    public static synchronized ConnectionPool getInstance()
    {
        if (pool == null)
        {
            pool = new ConnectionPool();
        }
        return pool;
    }

    /**
     * This method gets the connection that was previously made.
     * @return 
     */
    public Connection getConnection()
    {
        try
        {
            return dataSource.getConnection();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot get the connection from the pool", e);
        }
    }

    /**
     * This method frees up the connection that was previously made
     * @param c = instance of the connection
     */
    public void freeConnection(Connection c)
    {
        try
        {
            c.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot return the connection to the pool", e);
        }
    }
}
