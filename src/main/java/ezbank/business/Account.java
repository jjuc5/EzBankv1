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
 * This class represents an Account object.  It allows a representation inclusive
 * of account id, account type, creation date and balance and user_id.
 * 
 * @author John Urbanowicz, Melanie Iarocci
 */
public class Account implements Serializable
{
    private Integer account_id;
    private Integer account_Typesaccount_type;
    private Date creation_date;
    private double balance;
    private Integer Usersuser_id;
    
    /**
     * No-arg constructor for an Account object.
     */
    public Account()
    {
    }

    /**
     * Full argument construction of an Account object.  Contains all properties
     * required to represent an Account.
     * 
     * @param account_id - the ID of an Account
     * @param Account_Typesaccounttype - the account type of an account (e.g. Chequing, Savings)
     * @param creation_date the date an Account was created
     * @param balance the balance of funds in an Account
     * @param Usersuser_id the user_id associated with ownership of an Account
     */
    public Account(Integer account_id, Integer Account_Typesaccounttype, Date creation_date, double balance, 
            Integer Usersuser_id)
    {
        this.account_id = account_id;
        this.account_Typesaccount_type = Account_Typesaccounttype;
        this.creation_date = creation_date;
        this.balance = balance;
    }
    
    /**
     * A method to get the Account ID.  This is represented by an integer and
     * is normally an auto-incremented value by the database.
     * 
     * @return the id of the account
     */
    public Integer getAccount_id()
    {
        return account_id;
    }

    /**
     * A method to set the Account ID.  Used to set the ID of an Account on creation
     * by means of obtaining the generated keys in the database.
     * 
     * @param account_id - the id of the account
     */
    public void setAccount_id(Integer account_id)
    {
        this.account_id = account_id;
    }

    /**
     * A method to return the creation date of the Account.  The creation date
     * is the date of when the Account was created.
     * 
     * @return the creation date of the account
     */
    public Date getCreation_date()
    {
        return creation_date;
    }

    /**
     * A method to set the creation date for the Account.  
     * 
     * @param creation_date - the creation date of the account
     */
    public void setCreation_date(Date creation_date)
    {
        this.creation_date = creation_date;
    }

    /**
     * A method to retrieve the balance of an Account.  The amount of funds currently
     * in an Account.
     * 
     * @return the balance of an Account
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * A method to set the balance of an Account.  Used to set the balance of an amount
     * whenever funds are deposited or withdrawn from an Account.
     * 
     * @param balance - balance of funds in an account
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    /**
     * A method to get the Useruser_id of an Account.  A Useruser_id is a foreign key
     * in the database that is associated with a user and is used to help identify
     * which user is the owner of a particular account.
     * 
     * @return the Useruser_id of an Account
     */
    public Integer getUsersuser_id()
    {
        return Usersuser_id;
    }

    /**
     * A method to set the Useruser_id of an Account.  This is used to establish
     * the owner of accounts about their creation.
     * 
     * @param Usersuser_id - associated user of an account
     */
    public void setUsersuser_id(Integer Usersuser_id)
    {
        this.Usersuser_id = Usersuser_id;
    }

    /**
     * A method to get the Account_Typesaccount_type of an Account.  This is used
     * to identify what type an Account is (Chequing, Savings) and is a foreign key
     * from the Account_Types table which holds account type definitions.
     * 
     * @return the Account_Typesaccount_type of an Account
     */
    public Integer getAccount_Typesaccount_type()
    {
        return account_Typesaccount_type;
    }

    /**
     * A method to set the Account_Typesaccount_type of an Account.  Used to set the
     * appropriate Account_Type flag of an Account upon creation.
     * 
     * @param account_Typesaccount_type 
     */
    public void setAccount_Typesaccount_type(Integer account_Typesaccount_type)
    {
        this.account_Typesaccount_type = account_Typesaccount_type;
    }

}
