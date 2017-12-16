/*
 * Alex Tetervak, Sheridan College, Ontario
 */
package sheridan.studentdb.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class InputOptions implements ServletContextListener {

    static final String[] PROGRAMS = {
        "-- select program --", "Computer Programmer", "Systems Technology",
        "Engineering Technician", "Systems Technician"};

    static final String[] YEAR_OPTIONS = {"1", "2"};

    static final String[] YEAR_LABELS = {"First", "Second"};

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("programs", PROGRAMS);
        sc.setAttribute("yearOptions", YEAR_OPTIONS);
        sc.setAttribute("yearLabels", YEAR_LABELS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }

}
