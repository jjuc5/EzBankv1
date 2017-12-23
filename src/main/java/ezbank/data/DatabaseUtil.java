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
 * This class provides all the database utilities that are performed.
 * @author melan
 */
public class DatabaseUtil {

    /**
     * This method closes the statement that was performed.
     * @param s = the statement name 
     */
    public static void closeStatement(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot close the statement", e);
        }
    }

    /**
     * This method closed a prepared statement.
     * @param ps = prepared statement name
     */
    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot close the prepared statement", e);
        }
    }

    /**
     * This method closed a result set statement.
     * @param rs = result set name
     */
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot close the result set", e);
        }
    }
}
