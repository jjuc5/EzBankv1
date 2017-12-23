/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package sheridan.studentdb.util;

import javax.servlet.http.*;

/**
 * This class is used to for Cookie Utilities. We pass 
 * information from one form to another using cookies.
 * @author melan
 */
public class CookieUtil {

    /**
     * This class is used to get the value from Cookies. The Cookie object is
     * passed along with the cookie name and the value is returned.
     * @param cookies
     * @param cookieName
     * @return 
     */
    public static String getCookieValue(Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

}
