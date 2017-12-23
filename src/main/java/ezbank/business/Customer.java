/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.business;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import java.io.Serializable;

/**
 * This class represents a Customer as an object.  This includes the user_id,
 * the customer_id, first name, last name, birth date, SSN, telephone number, 
 * email, street number, address, city, province, postal code.  It is used in 
 * conjunction with Customer registration and therefore is also used to temporary 
 * keep track of which account types they've chosen to create, as with their username
 * and password.
 * 
 * @author John Urbanowicz, Melanie Iarocci
 */
public class Customer implements Serializable
{
    private Integer user_id;
    private Integer customer_id;

    @NotBlank(message = "Missing first name")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid first name")
    private String first_name = "";

    @NotBlank(message = "Missing last name")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid last name")
    private String last_name = "";

    @NotBlank(message = "Missing birth date")
    @Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)"
            + "[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])"
            + "\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|"
            + "40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)", message
            = "Invalid birth date")
    private String birth_date = "";

    @NotNull(message = "Missing Social Security Number")
    //  @Pattern(regexp = "/^\\d{9}$/", message = "Invalid Social Security Number")
    private Integer social_security_no;

    @NotNull(message = "Missing Telephone Number")
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}|\\(\\d{3}\\)-"
            + "\\d{3}-?\\d{4}|\\(\\d{3}\\) \\d{3} ?\\d{4}|\\(\\d{3}\\)-\\d{3} ?\\d{4}|\\("
            + "\\d{3}\\) \\d{3}-?\\d{4}", message = "Invalid Telephone Number")
    private String tel_no;

    @NotBlank(message = "Missing Email Address")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
            + "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\"
            + "x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
            + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]"
            + "?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]"
            + ":(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\"
            + "x0c\\x0e-\\x7f])+)\\])", message = "Invalid Email Address")
    private String email = "";

    @NotBlank(message = "Missing Street Number")
    @Pattern(regexp = "^\\d{0,5}$", message = "Invalid Street Number")
    private String street_no = "";

    @NotBlank(message = "Missing Street Name")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid Street Name")
    private String street_name = "";

    @NotBlank(message = "Missing City")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid City")
    private String city = "";

    @NotBlank(message = "Missing Province")
    private String province = "";

    @NotBlank(message = "Missing Postal Code")
    @Pattern(regexp = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$", message = "Invalid Postal Code")
    private String postal_code = "";

    @NotBlank(message = "Missing Username")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid Username")
    private String username = "";

    @NotBlank(message = "Missing Password")
    //@Pattern(regexp = "((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "Invalid Password")
    private String password = "";
       
    @Pattern(regexp = "yes|no", message="Invalid choice for savings")
    private String savings = "";

    @Pattern(regexp = "yes|no", message = "Invalid choice for chequing")
    private String chequing = "";

    
    /**
     * No-arg constructor for a Customer object.
     */
    public Customer()
    {
    }

    
    /**
     * Full-argument constructor of a Customer object.  This is capable of representing
     * a full customer from the database and additionally provides utility during
     * the registration process.
     * 
     * @param customer_id - the ID of a customer
     * @param first_name - the first name of a customer
     * @param last_name - the last name of a customer
     * @param birth_date - the birth date of a customer
     * @param social_security_no - the SSN of a customer
     * @param tel_no - the telephone number of a customer
     * @param email - the email address of a customer
     * @param street_no - the street number of a customer's address
     * @param street_name - the street name of a customer's address
     * @param city - the city of a customer's address
     * @param province - the province of a customer's address
     * @param postal_code - the postal code of a customer's address
     * @param user_id - the user ID of a customer
     * @param username - the username / login name of a customer during creation
     * @param password - the password of a customer for their associated login during creation
     * @param savings - used to track the customer's choice of savings account creation
     * @param chequing - used to track the customer's choice of chequing account creation
     */
    protected Customer(int customer_id, String first_name, String last_name, String birth_date, int social_security_no, String tel_no, String email, String street_no, String street_name, String city, String province, String postal_code, int user_id, String username, String password, String savings, String chequing)
    {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.social_security_no = social_security_no;
        this.tel_no = tel_no;
        this.email = email;
        this.street_no = street_no;
        this.street_name = street_name;
        this.city = city;
        this.province = province;
        this.postal_code = postal_code;
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    /**
     * A method to get the first name of a Customer. This is used to identify and
     * otherwise greeting the customer when traversing the banking site.
     * 
     * @return the first name of the customer
     */
    public String getFirst_name()
    {
        return first_name;
    }

    /**
     * A method to set the first name of a Customer.  This is primarily used 
     * during the registration process.
     * 
     * @param first_name - first name of the customer
     */
    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    /**
     * A method to get the last name of the Customer. This is used to identify and
     * otherwise greeting the customer when traversing the banking site.
     * 
     * @return the last name of the customer
     */
    public String getLast_name()
    {
        return last_name;
    }

    /**
     * A method to set the last name of a Customer.  This is primarily used 
     * during the registration process.
     * 
     * @param last_name - the last name of the Customer
     */
    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    /**
     * A method to get the birth date of a Customer.  This is strictly used in 
     * cases of verification purposes.
     * 
     * @return the birth date of a Customer
     */
    public String getBirth_date()
    {
        return birth_date;
    }

    /**
     * A method to set the birth date of a Customer.  This is used during the 
     * registration process to set the birth date of a Customer.
     * 
     * @param birth_date - the birth date of a Customer
     */
    public void setBirth_date(String birth_date)
    {
        this.birth_date = birth_date;
    }

    /**
     * A method to get the SSN of a Customer.  This is used for verification purposes.
     * 
     * @return the SSN of a Customer
     */
    public int getSocial_security_no()
    {
        return social_security_no;
    }

    /**
     * A method to set the SSN of a Customer. This is used during the registration
     * process to set the SSN of a Customer.
     * 
     * @param social_security_no - the SSN of a Customer 
     */
    public void setSocial_security_no(int social_security_no)
    {
        this.social_security_no = social_security_no;
    }

    /**
     * A method to get the telephone number of a Customer.  This is used for
     * verification purposes.
     * 
     * @return the telephone number of a Customer
     */
    public String getTel_no()
    {
        return tel_no;
    }

    /**
     * A method to set the telephone number of a Customer.  This is primarily used
     * to set the telephone number of a Customer at registration time.
     * 
     * @param tel_no - the telephone number of a Customer
     */
    public void setTel_no(String tel_no)
    {
        this.tel_no = tel_no;
    }

    /**
     * A method to get the email of a Customer.  This is used to retrieve the email
     * address associated with a Customer.
     * 
     * @return the email address of a Customer
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * A method used to set the email address of a Customer.  This is used primarily
     * to set the Customer's email address during registration.
     * 
     * @param email - the email address of a Customer
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * A method to get the street number of a Customer's address.  
     * 
     * @return The street number of a Customer's address
     */
    public String getStreet_no()
    {
        return street_no;
    }

    /**
     * A method used to set the street number of a Customer's address.
     * 
     * @param street_no - the street number of a Customers address 
     */
    public void setStreet_no(String street_no)
    {
        this.street_no = street_no;
    }

    /**
     * A method to get the street name of a Customer's address.
     * 
     * @return the street name of a Customer's address
     */
    public String getStreet_name()
    {
        return street_name;
    }

    /**
     * A method to set the street name of a Customer's address.
     * 
     * @param street_name - the street name of a Customer's address
     */
    public void setStreet_name(String street_name)
    {
        this.street_name = street_name;
    }

    /**
     * A method to get the city of a Customer's address.
     * 
     * @return the city of a Customer's address
     */
    public String getCity()
    {
        return city;
    }

    /**
     * A method to set the city of a Customer's address.
     * 
     * @param city the city of a Customer's address
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * A method to get the province of a Customer's address.
     * 
     * @return the province of the Customer's address
     */
    public String getProvince()
    {
        return province;
    }

    /**
     * A method to set the province of a Customer's address.
     * 
     * @param province the province of a Customer's address
     */
    public void setProvince(String province)
    {
        this.province = province;
    }

    /**
     * A method to get the postal code of a Customer's address.
     * 
     * @return the postal code of a Customer's address
     */
    public String getPostal_code()
    {
        return postal_code;
    }

    /**
     * A method to set the postal code of a Customer's address.
     * 
     * @param postal_code 
     */
    public void setPostal_code(String postal_code)
    {
        this.postal_code = postal_code;
    }

    /**
     * A method to get the User ID of a Customer.  The User ID an auto-incremented
     * key from the Users table to identify them as a foreign key and subsequently
     * can identify their login credentials.
     * 
     * @return the User ID of a customer.
     */
    public int getUser_id()
    {
        return user_id;
    }

    /**
     * A method to set the User ID of a Customer.  The User ID is a foreign key from
     * the Users table and is subsequently used to later identify with their login
     * credentials.
     * 
     * @param user_id the User ID of a customer
     */
    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    /**
     * A method to get the Username of a Customer.  This is primarily used during
     * but is also used for temporary storage of values when trying to perform efficient
     * logic through the servlets.
     * 
     * @return the username of a Customer
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * A method to set the Username of a Customer.  This is used during the registration
     * process or when needing to store temporary values for efficient use in logic.
     * 
     * @param username - the username of a Customer
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * A method to get the password of a Customer.  This is not generally used 
     * as different methods are used to match up during login.
     * 
     * @return the password of a Customer
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * A method to set the password of a Customer.  This is only used during registration
     * to set the password for transference to the database with other Customer details.
     * 
     * @param password - the password of a Customer
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * A method to get the customer ID of a Customer.  This is used to get the 
     * auto-incremented Customer ID and positively identify the customer.
     * 
     * @return the customer ID of a Customer
     */
    public int getCustomer_id()
    {
        return customer_id;
    }

    /**
     * A method to set the customer ID of a Customer.  This is primarily used during
     * registration or when manipulating customer objects for logical use.  A customer
     * ID is used in various look-up tables.
     * 
     * @param customer_id - the customer ID of a Customer
     */
    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    /**
     * A method to get the Savings Account creation flag by a Customer.  This returns
     * whether or not the Customer chose to have a Savings account created during
     * registration.
     * 
     * @return the choice of whether or not to create a Savings account for a Customer
     */
    public String getSavings()
    {
        return savings;
    }

    /**
     * A method to set the Savings Account creation flag for a Customer.  This can be
     * used to set whether or not a Customer has chosen a Savings account for
     * creation.
     * 
     * @param savings - the choice of whether or not to create a Savings a account for a Customer
     */
    public void setSavings(String savings)
    {
        this.savings = savings;
    }

    /**
     * A method to get the Chequing Account creation flag by a Customer.  This is 
     * used to identify whether or not a Customer chose to create a Chequing account
     * during registration.
     * 
     * @return the choice of whether or not to create a Chequing account for a Customer
     */
    public String getChequing()
    {
        return chequing;
    }

    /**
     * A method to set the Chequing account creation flag for a Customer.  This is
     * used to set whether or not a Customer chose to create a Chequing account 
     * during the registration process.
     * 
     * @param chequing - the choice of whether or not to create a Chequing Account by a customer
     */
    public void setChequing(String chequing)
    {
        this.chequing = chequing;
    }

}
