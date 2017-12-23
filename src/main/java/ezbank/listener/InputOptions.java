/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
/**
 * This class sets up the input options for the provinces. It has a table containing 
 * the two character abbreviations for all the provinces in Canada.
 * @author melan
 */
@WebListener
public class InputOptions implements ServletContextListener {

    static final String[] PROVINCES = {
        "          ", "AB", "BC",
        "MB", "NB", "NL", "NT", "NS", "NU", "ON", "PE", "QC", "SK", "YT"};


    /**
     * This method initializes the context for the form that uses provinces.
     * @param sce = the province that is entered on the form
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("provinces", PROVINCES);
    }

    /**
     * This method destroys the context for the form that uses provinces
     * @param sce = province that is entered on the form 
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }

}
