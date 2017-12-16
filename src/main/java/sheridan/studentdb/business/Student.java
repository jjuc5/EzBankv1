/*
 * Alex Tetervak, Sheridan College, Ontario
 */
package sheridan.studentdb.business;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

import java.io.Serializable;

public class Student implements Serializable {

    private int id = 0;
    
    @NotBlank(message = "Missing first name")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid first name")
    private String firstName = "";
    
    @NotBlank(message = "Missing last name")
    @Pattern(regexp = "(([a-zA-Z]+['.,-]?)|\\s)*", message = "Invalid last name")
    private String lastName = "";
    
    @NotBlank(message = "Missing program name")
    @Pattern(regexp = "(Computer Programmer|Systems Technology|Engineering Technician|Systems Technician)?",
            message = "Invalid program name")
    private String program = "";
    
    @NotNull(message = "Missing program year")
    @Pattern(regexp = "[1-2]", message="Invalid program year")
    private String year = "";
    
    @NotNull(message = "Missing co-op data")
    @Pattern(regexp = "yes|no", message="Invalid co-op data")
    private String coop = "";

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCoop() {
        return coop;
    }

    public void setCoop(String coop) {
        this.coop = coop;
    }
}
