/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.data;

import ezbank.business.Account;
import ezbank.business.Customer;
import java.sql.*;

import ezbank.business.Transaction;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class handles all the transaction data for the transaction table.
 * @author melan
 */
public class TransactionData
{

    /**
     * This method insert deposits into the database.
     * Creates the transaction table, the customer_account_transaction table, and
     * updates the balance in the account table.
     * @param transaction = object that contains all the data for the transaction
     * @param customer = object that contains all the data for the customer
     * @param account = object that contains all the data for the account
     * @param balance = the account balance before the transaction is deposited
     */
    public static void insertDeposit(Transaction transaction, Customer customer, Account account, double balance)
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
            ps.setInt(4, account.getAccount_id());
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
            ps.setInt(2, account.getAccount_id());
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
    
    /**
     * This method inserts withdrawals into the database.
     * Creates the transaction table, the customer_account_transaction table, and
     * updates the balance in the account table.
     * @param transaction = object that contains all the transaction data
     * @param customer = object that contains all the customer data
     * @param account = object that contains all the account data
     * @param balance = account balance before the withdrawal transaction is made.
     */
    public static void insertWithdrawal(Transaction transaction, Customer customer, Account account, double balance)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String update = "INSERT INTO transactions "
                + "(Transaction_Typestransaction_type, transaction_date, transaction_amount"
                + ", Accountsaccount_id) VALUES (?, ?, ?, ?)";
        
        try
        {
            ps = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, transaction.getTranstype());
            ps.setDate(2, transaction.getTransaction_date());
            ps.setDouble(3, transaction.getTransaction_amount());
            ps.setInt(4, account.getAccount_id());
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
            ps.setInt(2, account.getAccount_id());
            ps.setInt(3, transaction.getTransaction_id());
            ps.executeUpdate();
            
            String update3 = "UPDATE accounts SET balance = ? WHERE account_id = ?";
            
            double newBalance = Math.round((balance - transaction.getTransaction_amount()) * 100.0) / 100.0;
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
    
    /**
     * This method inserts transfer records into the database.
     * Creates the transaction table for the deposit and the withdrawal, creates 
     * the customer_account_transaction for the deposit and the withdrawal, and updates
     * the accounts with the new balances
     * @param withdrawalTransfer = object that contains all the withdrawal information
     * @param depositTransfer = object that contains all the deposit information
     * @param customer = object that contains the customer information
     * @param sourceAccount = object that contains the source account information
     * @param destAccount = object that contains the destination account information
     * @param sourceBalance = the source account balance before the transfer out
     * @param destBalance = the destination balance before the transfer in 
     */
    public static void insertTransfer(Transaction withdrawalTransfer, Transaction depositTransfer, Customer customer, 
            Account sourceAccount, Account destAccount, double sourceBalance, double destBalance)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String updateSourceWithdrawal = "INSERT INTO transactions "
                + "(Transaction_Typestransaction_type, transaction_date, transaction_amount"
                + ", Accountsaccount_id) VALUES (?, ?, ?, ?)";
        
        try
        {
            ps = connection.prepareStatement(updateSourceWithdrawal, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, withdrawalTransfer.getTranstype());
            ps.setDate(2, withdrawalTransfer.getTransaction_date());
            ps.setDouble(3, withdrawalTransfer.getTransaction_amount());
            ps.setInt(4, sourceAccount.getAccount_id());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
            {
                withdrawalTransfer.setTransaction_id(keys.getInt(1));
            }
            else
            {
                throw new RuntimeException("Cannot get the generated key.");
            }
            
            String updateSourceWithdrawal2 = "INSERT INTO customer_account_transactions "
                + "(Customer_AccountCustomercustomer_id, Customer_AccountAccountsaccount_id, "
                    + "Transactionstransaction_id) VALUES (?, ?, ?)";
            
            ps = connection.prepareStatement(updateSourceWithdrawal2); 
            ps.setInt(1, customer.getCustomer_id());
            ps.setInt(2, sourceAccount.getAccount_id());
            ps.setInt(3, withdrawalTransfer.getTransaction_id());
            ps.executeUpdate();
            
            String updateSourceWithdrawal3 = "UPDATE accounts SET balance = ? WHERE account_id = ?";
            
            double newBalanceSource = Math.round((sourceBalance - withdrawalTransfer.getTransaction_amount()) * 100.0) / 100.0;
            ps = connection.prepareStatement(updateSourceWithdrawal3);
            ps.setDouble(1, newBalanceSource);
            ps.setInt(2, withdrawalTransfer.getAccountsaccount_id());
            ps.executeUpdate();
            
            
            String updateDestinationDeposit = "INSERT INTO transactions "
                + "(Transaction_Typestransaction_type, transaction_date, transaction_amount"
                + ", Accountsaccount_id) VALUES (?, ?, ?, ?)";
            
            ps = connection.prepareStatement(updateDestinationDeposit, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, depositTransfer.getTranstype());
            ps.setDate(2, depositTransfer.getTransaction_date());
            ps.setDouble(3, depositTransfer.getTransaction_amount());
            ps.setInt(4, destAccount.getAccount_id());
            ps.executeUpdate();
            ResultSet keys2 = ps.getGeneratedKeys();
            if (keys2.next())
            {
                depositTransfer.setTransaction_id(keys2.getInt(1));
            }
            else
            {
                throw new RuntimeException("Cannot get the generated key.");
            }
            
            String updateDestinationDeposit2 = "INSERT INTO customer_account_transactions "
                + "(Customer_AccountCustomercustomer_id, Customer_AccountAccountsaccount_id, "
                    + "Transactionstransaction_id) VALUES (?, ?, ?)";
            
            ps = connection.prepareStatement(updateDestinationDeposit2); 
            ps.setInt(1, customer.getCustomer_id());
            ps.setInt(2, destAccount.getAccount_id());
            ps.setInt(3, depositTransfer.getTransaction_id());
            ps.executeUpdate();
            
            String updateDestinationDeposit3 = "UPDATE accounts SET balance = ? WHERE account_id = ?";
            
            double newBalanceDest = Math.round((destBalance + depositTransfer.getTransaction_amount()) * 100.0) / 100.0;
            ps = connection.prepareStatement(updateDestinationDeposit3);
            ps.setDouble(1, newBalanceDest);
            ps.setInt(2, depositTransfer.getAccountsaccount_id());
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
    
    /**
     * This method retrieves the chequing account using the user_id.
     * This method finds the chequing account which has an account type of 1
     * and then finds all the transactions for that account. These transactions
     * are stored in the account and transaction objects. The transaction object
     * is returned form. The transactions are sorted by date in descending order.
     * @param user_id = the user id for the customer in question
     * @return 
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
   
    /**
    * This method retrieves the savings account using the user_id.
     * This method finds the savings account which has an account type of 2
     * and then finds all the transactions for that account. These transactions
     * are stored in the account and transaction objects. The transaction object
     * is returned form. The transactions are sorted by date, in descending order.
     * @param user_id = the user id for the customer in question
     * @return 
     */
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
