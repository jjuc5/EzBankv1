/*
 * Alex Tetervak, Sheridan College, Ontario
 */
package sheridan.studentdb.util;

import javax.servlet.http.*;

public class CookieUtil {

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
