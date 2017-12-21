package ezbank.data;

import ezbank.business.Account;
import java.sql.*;

import ezbank.business.Transaction;
import java.util.ArrayList;
import java.util.Collections;

public class TransactionData
{

    //Need to finish this logic in regards to inserting a deposit and/or withdrawal record
    /*
    public static void insert(Transaction transaction)
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
    */
    
    public static ArrayList<Transaction> getAllChequing(int user_id)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Transaction> transactions = new ArrayList<>();
        Account account = new Account();
        
        String query = "SELECT * FROM accounts WHERE Usersuser_id = ? AND Account_Typesaccount_type = 1";
        String query2 = "SELECT * FROM transactions WHERE Accountsaccount_id = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                account = new Account();
                account.setAccount_id(rs.getInt("account_id"));
                account.setAccount_Typesaccount_type(rs.getInt("Account_Typesaccount_Type"));
                account.setCreation_date(rs.getDate("creation_date"));
                account.setBalance(rs.getDouble("balance"));
                account.setUsersuser_id(user_id);
            }
            ps = connection.prepareStatement(query2);
            ps.setInt(1, account.getAccount_id());
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Transaction transaction = new Transaction();
                transaction.setTransaction_id(rs.getInt("transaction_id"));
                transaction.setTranstype(rs.getInt("Transaction_Typestransaction_type"));
                transaction.setTransaction_date(rs.getDate("transaction_date"));
                transaction.setTransaction_amount(rs.getDouble("transaction_amount"));
                transaction.setAccountsaccount_id(rs.getInt("Accountsaccount_id"));
                transactions.add(transaction);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Unable to retrieve customer's chequing transactions.", e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closeStatement(ps);
            pool.freeConnection(connection);
        }
        Collections.sort(transactions, Collections.reverseOrder());
        return transactions;
    }
    
    public static ArrayList<Transaction> getAllSavings(int user_id)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Transaction> transactions = new ArrayList<>();
        Account account = new Account();
        
        String query = "SELECT * FROM accounts WHERE Usersuser_id = ? AND Account_Typesaccount_type = 2";
        String query2 = "SELECT * FROM transactions WHERE Accountsaccount_id = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                account = new Account();
                account.setAccount_id(rs.getInt("account_id"));
                account.setAccount_Typesaccount_type(rs.getInt("Account_Typesaccount_Type"));
                account.setCreation_date(rs.getDate("creation_date"));
                account.setBalance(rs.getDouble("balance"));
                account.setUsersuser_id(user_id);
            }
            ps = connection.prepareStatement(query2);
            ps.setInt(1, account.getAccount_id());
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Transaction transaction = new Transaction();
                transaction.setTransaction_id(rs.getInt("transaction_id"));
                transaction.setTranstype(rs.getInt("Transaction_Typestransaction_type"));
                transaction.setTransaction_date(rs.getDate("transaction_date"));
                transaction.setTransaction_amount(rs.getDouble("transaction_amount"));
                transaction.setAccountsaccount_id(rs.getInt("Accountsaccount_id"));
                transactions.add(transaction);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Unable to retrieve customer's savings transactions.", e);
        }
        finally
        {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closeStatement(ps);
            pool.freeConnection(connection);
        }
        Collections.sort(transactions, Collections.reverseOrder());
        return transactions;
    }
}
