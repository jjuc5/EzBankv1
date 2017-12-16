
package sheridan.studentdb.util;

import javax.validation.*;

public class ValidatorUtil {
  
  private static final ValidatorFactory 
          factory = Validation.buildDefaultValidatorFactory();
  
  public static Validator getValidator(){
      return factory.getValidator();
  }
  
}
