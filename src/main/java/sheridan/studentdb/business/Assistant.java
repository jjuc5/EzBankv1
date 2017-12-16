
package sheridan.studentdb.business;

import java.io.Serializable;

/**
 *
 * @author John Urbanowicz
 */
public class Assistant implements Serializable
{
    private int id = 0;
    private String username;
    private String password;
    private String role;

    public Assistant()
    {
    }

    public Assistant(String username, String role)
    {
        this.username = username;
        this.role = role;
    }
    
//     public Assistant(String username, String password)
//    {
//        this.username = username;
//        this.password = password;
//    }

    public Assistant(int id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    
    public Assistant(int id, String username, String password, String role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    
}
