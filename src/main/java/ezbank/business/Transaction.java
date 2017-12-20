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
public class Transaction implements Serializable
{
    private int transaction_id;
    private int typestransaction_type;
    private Date transaction_date;
    private double transaction_amount;
    private int accountsaccount_id;
    
    
    public Transaction()
    {
    }

    public Transaction(int transaction_id, int typestransaction_type, Date transaction_date, double transaction_amount, int accountsaccount_id)
    {
        this.transaction_id = transaction_id;
        this.typestransaction_type = typestransaction_type;
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

    public int getTypestransaction_type()
    {
        return typestransaction_type;
    }

    public void setTypestransaction_type(int typestransaction_type)
    {
        this.typestransaction_type = typestransaction_type;
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
    
    
    
    
    
}
