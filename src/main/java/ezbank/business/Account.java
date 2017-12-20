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
public class Account implements Serializable
{
    private Integer account_id;
    private Integer account_Typesaccount_type;
    private Date creation_date;
    private double balance;
    private Integer Usersuser_id;
    
    
    public Account()
    {
    }

    public Account(Integer account_id, Integer Account_Typesaccounttype, Date creation_date, double balance, 
            Integer Usersuser_id)
    {
        this.account_id = account_id;
        this.account_Typesaccount_type = Account_Typesaccounttype;
        this.creation_date = creation_date;
        this.balance = balance;
    }
    
    public Integer getAccount_id()
    {
        return account_id;
    }

    public void setAccount_id(Integer account_id)
    {
        this.account_id = account_id;
    }

    public Date getCreation_date()
    {
        return creation_date;
    }

    public void setCreation_date(Date creation_date)
    {
        this.creation_date = creation_date;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public Integer getUsersuser_id()
    {
        return Usersuser_id;
    }

    public void setUsersuser_id(Integer Usersuser_id)
    {
        this.Usersuser_id = Usersuser_id;
    }

    public Integer getAccount_Typesaccount_type()
    {
        return account_Typesaccount_type;
    }

    public void setAccount_Typesaccount_type(Integer account_Typesaccount_type)
    {
        this.account_Typesaccount_type = account_Typesaccount_type;
    }

}
