/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package sheridan.studentdb.util;

import javax.validation.*;

/**
 * This class contains the Validator Factory. There are no 
 * parameters passed to this class * 
 * 
 * @author melan
 */
public class ValidatorUtil {
  
  private static final ValidatorFactory 
          factory = Validation.buildDefaultValidatorFactory();
  
  /**
   * This class gets the Validator from the factory. There 
   * are no parameters passed to this class
   * @return 
   */
  public static Validator getValidator(){
      return factory.getValidator();
  }
  
}
