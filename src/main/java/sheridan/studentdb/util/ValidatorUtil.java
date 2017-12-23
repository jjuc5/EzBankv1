/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package sheridan.studentdb.util;

import javax.validation.*;

public class ValidatorUtil {
  
  private static final ValidatorFactory 
          factory = Validation.buildDefaultValidatorFactory();
  
  public static Validator getValidator(){
      return factory.getValidator();
  }
  
}
