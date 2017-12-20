package ezbank.data;

import ezbank.business.Account;
import java.sql.*;

import ezbank.business.Customer;
import java.util.ArrayList;

public class AccountData
{

    public static void insert(Customer customer)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        // 1 is Chequing, 2 is Savings for Account_Type
        String update = "INSERT INTO accounts "
                + "(Account_Typesaccount_Type, creation_date, balance, Usersuser_id) "
                + "VALUES (?, ?, ?, ?)";
        try
        {
            if(customer.getChequing().equals("yes"))
            {
                ps = connection.prepareStatement(update,
                    Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, 1);
                ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.setDouble(3, 0.00);
                ps.setInt(4, customer.getUser_id());
                ps.executeUpdate();
            }
            
            if(customer.getSavings().equals("yes"))
            {
                ps = connection.prepareStatement(update,
                    Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, 2);
                ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.setDouble(3, 0.00);
                ps.setInt(4, customer.getUser_id());
                ps.executeUpdate();
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
    
    public static ArrayList<Account> get(String login_name)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();

        String query = "SELECT user_id FROM users WHERE login_name = ?";
        String query2 = "SELECT * FROM accounts WHERE Usersuser_id = ?";
        
        int user_id = 0;
        
        try
        {
            
            
            ps = connection.prepareStatement(query);
            ps.setString(1, login_name);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                user_id = rs.getInt("user_id");
            }
            
            ps = connection.prepareStatement(query2);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            
            while (rs.next())
            {
                Account account = new Account();
                account.setAccount_id(rs.getInt("account_id"));
                account.setAccount_Typesaccount_type(rs.getInt("Account_Typesaccount_Type"));
                account.setCreation_date(rs.getDate("creation_date"));
                account.setBalance(rs.getDouble("balance"));
                account.setUsersuser_id(user_id);
                accounts.add(account);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Unable to retrieve customer's account data.", e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closeStatement(ps);
            pool.freeConnection(connection);
        }
        return accounts;
    }
}
