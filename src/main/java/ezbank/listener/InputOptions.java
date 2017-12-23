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

@WebListener
public class InputOptions implements ServletContextListener {

    static final String[] PROVINCES = {
        "          ", "AB", "BC",
        "MB", "NB", "NL", "NT", "NS", "NU", "ON", "PE", "QC", "SK", "YT"};


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("provinces", PROVINCES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }

}
