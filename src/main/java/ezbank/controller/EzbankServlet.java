/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * This class represents the listener Servlet which redirects the front end requests
 * to the Controller for processing.
 * 
 * @author John Urbanowicz
 */
public class EzbankServlet extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String action = request.getServletPath();
        String jspPath = "/WEB-INF/jsp/";
        String nextPage; // where to forward/redirect

        switch (action)
        {
            case "/hello.ez":
            {
                nextPage = EzbankController.hello(request);
                break;
            }

            case "/login.ez":
            {
                nextPage = EzbankController.login(request);
                break;
            }
            
            case "/loginSubmit.ez":
            {
                nextPage = EzbankController.loginSubmit(request, response);
                break;
            }

            case "/input.ez":
            {
                nextPage = EzbankController.input(request);
                break;
            }
            
            case "/summary.ez":
            {
                nextPage = EzbankController.summary(request, response);
                break;
            }
            
            case "/chequing.ez":
            {
                nextPage = EzbankController.chequing(request, response);
                break;
            }
            
            case "/savings.ez":
            {
                nextPage = EzbankController.savings(request, response);
                break;
            }
            
                       
            case "/transfer.ez":
            {
                nextPage = EzbankController.transfer(request, response);
                break;
            }
            
            case "/submitTransfer.ez":
            {
                nextPage = EzbankController.submitTransfer(request, response);
                break;
            }
            
            case "/deposite.ez":
            {
                nextPage = EzbankController.deposite(request, response);
                break;
            }
            
            case "/submitDeposit.ez":
            {
                nextPage = EzbankController.submitDeposit(request, response);
                break;
            }
            
            case "/printe.ez":
            {
                nextPage = EzbankController.printe(request, response);
                break;
            }
            
            case "/submitWithdrawal.ez":
            {
                nextPage = EzbankController.submitWithdrawal(request, response);
                break;
            }
            
            case "/logout.ez":
            {
                nextPage = EzbankController.logout(request, response);
                break;
            }

            case "/next.ez":
            {
                nextPage = EzbankController.next(request);
                break;
            }

            case "/submit.ez":
            {
                nextPage = EzbankController.submit(request, response);
                break;
            }

            case "/thanks.ez":
            {
                nextPage = EzbankController.thanks(request);
                break;
            }
            
            default:
            {
                response.sendError(404);
                return;
            }
        }

        String greeting = getInitParameter("greeting");
        request.setAttribute("greeting", greeting);

        if (nextPage.startsWith("redirect:"))
        {
            response.sendRedirect(response.encodeRedirectURL(nextPage.substring(9)));
        }
        else
        {
            request.getRequestDispatcher(jspPath + nextPage + ".jsp")
                    .forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Student data collection servlet";
    }// </editor-fold>

}
