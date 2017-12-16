/*
 * Alex Tetervak, Sheridan College, Ontario
 */
package ezbank.business;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

import java.io.Serializable;

public class Customer implements Serializable {

    private int id = 0;
    
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
            + "40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)", message = 
                    "Invalid birth date")
    private String birth_date = "";
    
    @NotBlank(message = "Missing Social Security Number")
    @Pattern(regexp = "/^\\d{9}$/", message = "Invalid Social Security Number")
    private String social_security_no = "";
    
    @NotBlank(message = "Missing Telephone Number")
    @Pattern(regexp = "/^\\d{10}$/", message = "Invalid Telephone Number")
    private String tel_no = "";
    
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
    private String user_id = "";
    
    public Customer() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getBirth_date()
    {
        return birth_date;
    }

    public void setBirth_date(String birth_date)
    {
        this.birth_date = birth_date;
    }

    public String getSocial_security_no()
    {
        return social_security_no;
    }

    public void setSocial_security_no(String social_security_no)
    {
        this.social_security_no = social_security_no;
    }

    public String getTel_no()
    {
        return tel_no;
    }

    public void setTel_no(String tel_no)
    {
        this.tel_no = tel_no;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStreet_no()
    {
        return street_no;
    }

    public void setStreet_no(String street_no)
    {
        this.street_no = street_no;
    }

    public String getStreet_name()
    {
        return street_name;
    }

    public void setStreet_name(String street_name)
    {
        this.street_name = street_name;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getPostal_code()
    {
        return postal_code;
    }

    public void setPostal_code(String postal_code)
    {
        this.postal_code = postal_code;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

   
}