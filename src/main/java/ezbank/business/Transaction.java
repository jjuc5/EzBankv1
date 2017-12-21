/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezbank.business;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author urban
 */
public class Transaction implements Serializable, Comparable<Transaction>
{
    private int transaction_id;
    private int transtype;
    private Date transaction_date;
    private double transaction_amount;
    private int accountsaccount_id;
    
    
    public Transaction()
    {
    }

    public Transaction(int transaction_id, int transtype, Date transaction_date, double transaction_amount, int accountsaccount_id)
    {
        this.transaction_id = transaction_id;
        this.transtype = transtype;
        this.transaction_date = transaction_date;
        this.transaction_amount = transaction_amount;
        this.accountsaccount_id = accountsaccount_id;
    }

    public int getTransaction_id()
    {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id)
    {
        this.transaction_id = transaction_id;
    }

    public int getTranstype()
    {
        return transtype;
    }

    public void setTranstype(int transtype)
    {
        this.transtype = transtype;
    }

    public Date getTransaction_date()
    {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date)
    {
        this.transaction_date = transaction_date;
    }

    public double getTransaction_amount()
    {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount)
    {
        this.transaction_amount = transaction_amount;
    }

    public int getAccountsaccount_id()
    {
        return accountsaccount_id;
    }

    public void setAccountsaccount_id(int accountsaccount_id)
    {
        this.accountsaccount_id = accountsaccount_id;
    }
    
    
    @Override
    public int compareTo(Transaction o) 
    {
      return getTransaction_date().compareTo(o.getTransaction_date());
    }
    
    
}
