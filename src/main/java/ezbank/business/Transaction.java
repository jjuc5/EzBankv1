/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.business;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class represents a Transaction object.  It is used to detail a transaction
 * (e.g. deposit, withdrawal) as it is represented in the database.  It consists of
 * transaction id, transaction type, transaction date, transaction amount and 
 * accountaccount ID, which is a foreign key to associate a transaction with an 
 * account.
 * 
 * @author John Urbanowicz, Melanie Iarocci
 */
public class Transaction implements Serializable, Comparable<Transaction>
{
    private int transaction_id;
    private int transtype;
    private Date transaction_date;
    private double transaction_amount;
    private int accountsaccount_id;
    
    /**
     * No-arg constructor for a Transaction object.
     */
    public Transaction()
    {
    }

    /**
     * Full-arg constructor used to represent a Transaction object.  Used when 
     * processing transaction data in or out of the database.
     * 
     * @param transaction_id - the transaction's auto-incremented ID
     * @param transtype - the transaction's type (deposit, withdrawal)
     * @param transaction_date - the date of the transaction
     * @param transaction_amount - the amount of the transaction
     * @param accountsaccount_id - the account ID associated with the transaction
     */
    public Transaction(int transaction_id, int transtype, Date transaction_date, double transaction_amount, int accountsaccount_id)
    {
        this.transaction_id = transaction_id;
        this.transtype = transtype;
        this.transaction_date = transaction_date;
        this.transaction_amount = transaction_amount;
        this.accountsaccount_id = accountsaccount_id;
    }

    /**
     * A method to get the ID of a transaction from a Transaction object.
     * 
     * @return the transaction ID
     */
    public int getTransaction_id()
    {
        return transaction_id;
    }

    /**
     * A method to set the transaction ID for a Transaction object.
     * 
     * @param transaction_id - the transaction ID
     */
    public void setTransaction_id(int transaction_id)
    {
        this.transaction_id = transaction_id;
    }

    /**
     * A method to get the transaction type of a Transaction.
     * 
     * @return the transaction type (deposit, withdrawal)
     */
    public int getTranstype()
    {
        return transtype;
    }

    /**
     * A method to set the transaction type of a Transaction.
     * 
     * @param transtype the transaction type (deposit, withdrawal)
     */
    public void setTranstype(int transtype)
    {
        this.transtype = transtype;
    }

    /**
     * A method to get the transaction date of a Transaction.
     * 
     * @return the transaction date
     */
    public Date getTransaction_date()
    {
        return transaction_date;
    }

    /**
     * A method to set the transaction date of a Transaction.
     * 
     * @param transaction_date - the date of the transaction
     */
    public void setTransaction_date(Date transaction_date)
    {
        this.transaction_date = transaction_date;
    }

    /**
     * A method to get the transaction amount in a Transaction.
     * 
     * @return the transaction amount
     */
    public double getTransaction_amount()
    {
        return transaction_amount;
    }

    /**
     * A method to set the transaction amount in a Transaction.
     * 
     * @param transaction_amount - the amount in a transaction
     */
    public void setTransaction_amount(double transaction_amount)
    {
        this.transaction_amount = transaction_amount;
    }

    /**
     * A method to get the Accountsaccount_id of a Transaction.  Accountaccount_id
     * is a foreign key from the Accounts table which identifies which account is
     * associated with the transaciton.
     * 
     * @return the accountsaccount_id
     */
    public int getAccountsaccount_id()
    {
        return accountsaccount_id;
    }

    /**
     * A method to set the Accountsaccount_id of a Transaction.
     * 
     * @param accountsaccount_id - the associated Accountsaccount_id of a transaction
     */
    public void setAccountsaccount_id(int accountsaccount_id)
    {
        this.accountsaccount_id = accountsaccount_id;
    }
    
    
    /**
     * A method to compare transaction dates for the purposes of sorting transactions
     * by date for display.
     * 
     * @param o a transaction object for comparison
     * @return the object with lowest date value
     */
    @Override
    public int compareTo(Transaction o) 
    {
      return getTransaction_date().compareTo(o.getTransaction_date());
    }
    
    
}
