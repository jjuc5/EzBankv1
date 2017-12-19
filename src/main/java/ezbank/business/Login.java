/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezbank.business;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author urban
 */
public class Login
{
    @NotBlank(message = "Missing Login")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid Login")
    private String login_name = "";
    
    private String password = "";  
    
    private int Usersuser_type;
    
    public Login()
            {
                
            }
    protected Login(String login_name, String password)
    {
     
        this.login_name = login_name;
        this.password = password;
    }

    
    protected Login(String login_name, String password, int Usersuser_type)
    {
     
        this.login_name = login_name;
        this.password = password;
        this.Usersuser_type = Usersuser_type;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getUsersuser_type() {
        return Usersuser_type;
    }

    public void setUsersuser_type(int Usersuser_type) {
        this.Usersuser_type = Usersuser_type;
    }
    
}
