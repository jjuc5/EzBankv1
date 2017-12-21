package ezbank.data;

import ezbank.business.Account;
import ezbank.business.Customer;
import java.sql.*;

import ezbank.business.Transaction;
import java.util.ArrayList;
import java.util.Collections;

public class TransactionData
{

    public static void insertDeposit(Transaction transaction, Customer customer, double balance)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        // 1 is Chequing, 2 is Savings for Account_Type
        String update = "INSERT INTO transactions "
                + "(Transaction_Typestransaction_type, transaction_date, transaction_amount"
                + ", Accountsaccount_id) VALUES (?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, transaction.getTranstype());
            ps.setDate(2, transaction.getTransaction_date());
            ps.setDouble(3, transaction.getTransaction_amount());
            ps.setInt(4, transaction.getAccountsaccount_id());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
            {
                transaction.setTransaction_id(keys.getInt(1));
            }
            else
            {
                throw new RuntimeException("Cannot get the generated key.");
            }
            
            String update2 = "INSERT INTO customer_account_transactions "
                + "(Customer_AccountCustomercustomer_id, Customer_AccountAccountsaccount_id, "
                    + "Transactionstransaction_id) VALUES (?, ?, ?)";
            
            ps = connection.prepareStatement(update2); 
            ps.setInt(1, customer.getCustomer_id());
            ps.setInt(2, transaction.getAccountsaccount_id());
            ps.setInt(3, transaction.getTransaction_id());
            ps.executeUpdate();
            
            String update3 = "UPDATE accounts SET balance = ? WHERE account_id = ?";
            
            double newBalance = balance + transaction.getTransaction_amount();
            ps = connection.prepareStatement(update3);
            ps.setDouble(1, newBalance);
            ps.setInt(2, transaction.getAccountsaccount_id());
            ps.executeUpdate();
            
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Cannot insert the transaction data.", e);
        }
        finally
        {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
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
