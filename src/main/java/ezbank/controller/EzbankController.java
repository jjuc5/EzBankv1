/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.controller;

import ezbank.business.Account;
import java.util.*;
import javax.servlet.http.*;
import javax.validation.ConstraintViolation;
import ezbank.business.Customer;
import ezbank.business.Transaction;
import ezbank.data.AccountData;
import ezbank.data.CustomerData;
import ezbank.data.LoginData;
import ezbank.data.TransactionData;
import ezbank.data.UserData;
import sheridan.studentdb.util.ValidatorUtil;
import sheridan.studentdb.util.CookieUtil;


/**
 * This class represents the Servlet logic used for data processing, verification, 
 * initiates database transactions and otherwise redirecting users to respective pages.
 * It also passes along session attributes which are used to populate the jsp pages
 * with information appropriate for that particular user.
 * 
 * @author John Urbanowicz, Melanie Iarocci
 */
public class EzbankController
{
    public static String hello(HttpServletRequest request)
    {
        String userName = CookieUtil.getCookieValue(request.getCookies(), "userName");
        if (userName.isEmpty())
        {
            return input(request);
        }
        return "hello"; // show "hello.jsp"
    }

    // a user comes to the default front page at "login.ez" where they can choose
    /**
     * This method is invoked when a user comes to the default page of the site ("login.ez").
     * It allows them to either login or to register for an account with EzBank.
     * 
     * @param request 
     * @return the login.jsp page
     */
    public static String login(HttpServletRequest request)
    {
        String userName = CookieUtil.getCookieValue(request.getCookies(), "userName");
        if (userName.isEmpty())
        {
            return input(request);
        }
        return "redirect:login.jsp";
        //return "login"; // show "login.jsp"
    }

  
    /**
     * This method is invoked when a user clicks submit on the login page.  It 
     * verifies if the user credentials used are valid and if so, grabs the appropriate
     * data required to populate that user's account summary page ("summary.ez").
     * It also sets the appropriate session attributes to track the user through
     * the session.  If login credentials are invalid, the user is redirected to 
     * the login page with an appropriate error message.
     * 
     * @param request
     * @param response
     * @return the summary.jsp page or the login.jsp page.
     */
    public static String loginSubmit(HttpServletRequest request, HttpServletResponse response)
    {
        String login_name = request.getParameter("login_name");
        String password = request.getParameter("password");
        String message;

        if (LoginData.checkPassword(login_name, password) == true)
        {
            HttpSession session = request.getSession();
            Cookie cookie = new Cookie("userLogin", login_name);
            cookie.setMaxAge(30 * 24 * 60 * 60);// Approximately one month in seconds
            response.addCookie(cookie);
            
            int user_id = LoginData.getUserID(login_name);
            Customer customer = CustomerData.get(user_id);
            session.setAttribute("customer", customer);
            session.setAttribute("login_name", login_name);
            ArrayList<Account> accounts;
            accounts = AccountData.getAccounts(login_name);
            session.setAttribute("accounts", accounts);
            
            message = "";
            session.setAttribute("message", message);
            
            return "summary"; // show "summary.jsp"
        }
        else
        {
            HttpSession session = request.getSession();
            message = "Invalid login credentials.  Please try again.";
            session.setAttribute("message", message);
            return login(request); // go back to showing "login.jsp"
        }
    }

    /**
     * A method which directs a user to the ("input.ez") page.
     * 
     * @param request
     * @return input.jsp
     */
    public static String input(HttpServletRequest request)
    {
        return "input"; // show "input.jsp"
    }

    // a user comes to the input page at "summary.do"
    
    /**
     * A method which is invoked when the user logs in successfully, chooses to view
     * the summary page or is otherwise redirected ("summary.ez").  Gets and sets the appropriate
     * session attributes to populate the summary page.
     * 
     * @param request
     * @param response
     * @return summary.jsp
     */
    public static String summary(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String login_name = session.getAttribute("login_name").toString();
        
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        
        session.setAttribute("accounts", accounts);

        return "summary"; // show "summary.jsp"
    }
    
    /**
     * This method is invoked when a user initiates "chequing.ez".  It retrieves 
     * and populates the chequing account data if it exists for that user.
     * 
     * @param request
     * @param response
     * @return chequing.jsp
     */
    public static String chequing(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null)
        {
            return "expired"; // show "your session has expired" with "expired.jsp"
        }
        else
        {

            Customer customer = (Customer) session.getAttribute("customer");
            ArrayList<Transaction> transactionsChequing;
            transactionsChequing = TransactionData.getAllChequing(customer.getUser_id());

            session.setAttribute("customer", customer);
            session.setAttribute("transactionsChequing", transactionsChequing);

            return "chequing"; // show "chequing.jsp"
        }
    }
    
    /**
     * This method is invoked when the user initiates ("savings.ez").  It retrieves
     * and populates the respective savings account data for the user if it exists.
     * 
     * @param request
     * @param response
     * @return savings.jsp
     */
    public static String savings(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null)
        {
            return "expired"; // show "your session has expired" with "expired.jsp"
        }
        else
        {

            Customer customer = (Customer) session.getAttribute("customer");
            ArrayList<Transaction> transactionsSavings;
            transactionsSavings = TransactionData.getAllSavings(customer.getUser_id());

            session.setAttribute("customer", customer);
            session.setAttribute("transactionsSavings", transactionsSavings);

            return "savings"; // show "savings.jsp"
        }
    }
    
    /**
     * This method is invoked when a user selects to initiate ("transfer.ez").  It
     * retrieves and populates the screen with appropriate account data to allow 
     * for transfers between accounts.
     * 
     * @param request
     * @param response
     * @return transfer.jsp
     */
    public static String transfer(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String login_name = session.getAttribute("login_name").toString();
        
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        
        session.setAttribute("accounts", accounts);
        
        return "transfer"; // show "transfer.jsp"
    }
    
    /**
     * This method is invoked when the user at ("transfer.ez") chooses to proceed with
     * a transfer request ("submitTransfer.ez").  This method verifies that the transfer can be completed
     * without any conflicts and returns the user to the transfer page to view the
     * completed transfer amounts.  Otherwise, the user is returned to the summary 
     * page if there are any conflicts.
     * 
     * @param request
     * @param response
     * @return transfer.jsp or summary.jsp
     */
    public static String submitTransfer(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        String login_name = session.getAttribute("login_name").toString();
        String accIDSourceString = request.getParameter("accountsSource");
        int accIDSource = Integer.parseInt(accIDSourceString);
        String accIDDestinationString = request.getParameter("accountsDestination");
        int accIDDestination = Integer.parseInt(accIDDestinationString);
        Double amount = Double.parseDouble(request.getParameter("amount"));
        String message = "";
        double sourceBalance = 0.0;
        double destBalance = 0.0;
        
        Account sourceAccount = AccountData.get(accIDSource);
        Account destAccount = AccountData.get(accIDDestination);

        sourceBalance = sourceAccount.getBalance();
        destBalance = destAccount.getBalance();
        
        Customer customer = (Customer) session.getAttribute("customer");
        customer.setUsername(LoginData.getLoginName(customer.getUser_id()));
        
        if(!(accIDSource == accIDDestination))
        {
            if(amount <= sourceBalance)
            {
                Transaction withdrawalTransfer = new Transaction();

                withdrawalTransfer.setTranstype(2);
                withdrawalTransfer.setTransaction_amount(Math.round(amount));
                withdrawalTransfer.setTransaction_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
                withdrawalTransfer.setAccountsaccount_id(accIDSource);

                Transaction depositTransfer = new Transaction();

                depositTransfer.setTranstype(1);
                depositTransfer.setTransaction_amount(Math.round(amount));
                depositTransfer.setTransaction_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
                depositTransfer.setAccountsaccount_id(accIDDestination);

                TransactionData.insertTransfer(withdrawalTransfer, depositTransfer, customer, sourceAccount, destAccount, 
                        sourceBalance, destBalance);

                session.setAttribute("customer", customer);
                session.setAttribute("login_name", customer.getUsername());
                ArrayList<Account> accounts;
                accounts = AccountData.getAccounts(customer.getUsername());
                session.setAttribute("accounts", accounts);
            }
            else
            {
                message = "[Insufficient Funds]:  Unable to withdraw $" + amount + " with a balance of $" + sourceBalance + " "
                        + "from source account";
                request.setAttribute("message", message);
                return "summary";
            }
        }
        else
        {
            message = "[Account Selection Error]:  Cannot transfer funds to the same account";
            request.setAttribute("message", message);
            return "summary";
        }
        
        
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        
        session.setAttribute("accounts", accounts);
        
        return "transfer"; // show "transfer.jsp"
    }

    // a user comes to the data input page at "deposite.do"
    /**
     * This method is invoked when a user chooses to go to the deposit page ("deposite.ez").
     * The page is appropriately populated with session attributes.
     * 
     * @param request
     * @param response
     * @return deposite.jsp
     */    
    public static String deposite(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String login_name = session.getAttribute("login_name").toString();
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        session.setAttribute("accounts", accounts);
        
        Customer customer = (Customer) session.getAttribute("customer");
        session.setAttribute("customer", customer);
        
        return "deposite"; // show "deposite.jsp"
    }
    
    /**
     * This method is invoked when a user chooses to submit their deposit ("submitDeposit.ez").
     * The logic then assembles the appropriate data and sends it for insertion into
     * the database.  The session attributes are then set and new values displayed when
     * the user is redirected to the summary page.
     * 
     * @param request
     * @param response
     * @return summary.jsp
     */
    public static String submitDeposit(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String accIDString = request.getParameter("accountsDeposit");
        int accID = Integer.parseInt(accIDString);
        Double amount = Double.parseDouble(request.getParameter("amount"));
        double balance = 0.0;
        
        Customer customer = (Customer) session.getAttribute("customer");
        customer.setUsername(LoginData.getLoginName(customer.getUser_id()));
        Transaction depositTransaction = new Transaction();
        
        depositTransaction.setTranstype(1);
        depositTransaction.setTransaction_amount(amount);
        depositTransaction.setTransaction_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        depositTransaction.setAccountsaccount_id(accID);
        
        Account getAccount = AccountData.get(accID);
        balance = getAccount.getBalance();
        
        TransactionData.insertDeposit(depositTransaction, customer, getAccount, balance);
        
        session.setAttribute("customer", customer);
        session.setAttribute("login_name", customer.getUsername());
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(customer.getUsername());
        session.setAttribute("accounts", accounts);
        
        return "summary"; // show "summary.jsp"
    }
    
    /**
     * This method is invoked when a user chooses to withdraw / print an e-Cheque
     * at ("printe.ez").  The appropriate account information is populated for display.
     * 
     * @param request
     * @param response
     * @return printe.jsp
     */
    public static String printe(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String login_name = session.getAttribute("login_name").toString();
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        session.setAttribute("accounts", accounts);
        
        Customer customer = (Customer) session.getAttribute("customer");
        session.setAttribute("customer", customer);
        
        return "printe"; // show "printe.jsp"
    }
    
    /**
     * This method is invoked when the user confirms their withdrawal request ("submitWithdrawal.ez").
     * The logic in this code verifies that the amount requested is available for the selected account.
     * In the future, this method will also display an e-Cheque for the customer to print.
     * 
     * @param request
     * @param response
     * @return summary.jsp
     */
    public static String submitWithdrawal(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String accIDString = request.getParameter("accountsWithdrawal");
        int accID = Integer.parseInt(accIDString);
        Double amount = Double.parseDouble(request.getParameter("amount"));
        double balance = 0.0;
        String message;
        
        Customer customer = (Customer) session.getAttribute("customer");
        customer.setUsername(LoginData.getLoginName(customer.getUser_id()));
        Transaction withdrawalTransaction = new Transaction();
        
        Account getAccount = AccountData.get(accID);
        balance = getAccount.getBalance();
        
        if(amount <= balance)
        {
            withdrawalTransaction.setTranstype(2);
            withdrawalTransaction.setTransaction_amount(amount);
            withdrawalTransaction.setTransaction_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
            withdrawalTransaction.setAccountsaccount_id(accID);
            TransactionData.insertWithdrawal(withdrawalTransaction, customer, getAccount, balance);
        }
        else
        {
            message = "[Insufficient Funds]:  Unable to withdraw $" + amount + " with a balance of $" + balance;
            request.setAttribute("message", message);
            return "summary";
        }
        
        session.setAttribute("customer", customer);
        session.setAttribute("login_name", customer.getUsername());
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(customer.getUsername());
        session.setAttribute("accounts", accounts);
        
        return "summary"; // show "summary.jsp"
    }
    
    /**
     * This method is invoked when a user wishes to logout of the banking site ("logout.ez").
     * This method will terminate a user's session and return them to the login.jsp page.
     * 
     * @param response
     * @return login.jsp
     */
    public static String logout(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        Cookie cookie = new Cookie("userLogin", "pass");
        cookie.setMaxAge(0);
        response.addCookie(cookie); // remove the cookie
        session.invalidate();  
        return "redirect:login.jsp"; // redirect to the default front page
    }

    /**
     * This method is invoked when the user is submitting their registration data ("next.ez").
     * The user is transferred to next.jsp where they can review and confirm the information
     * they are submitted for accuracy.  Any errors or validation issues are also shown beside
     * the corresponding fields.
     * 
     * @param request
     * @return next.jsp
     */
    public static String next(HttpServletRequest request)
    {
        Customer customer = new Customer();

        customer.setFirst_name(request.getParameter("first_name"));
        customer.setLast_name(request.getParameter("last_name"));
        customer.setBirth_date(request.getParameter("birth_date"));
        customer.setSocial_security_no(Integer.parseInt(request.getParameter("social_security_no")));
        customer.setTel_no(request.getParameter("tel_no"));
        customer.setEmail(request.getParameter("email"));
        customer.setStreet_no(request.getParameter("street_no"));
        customer.setStreet_name(request.getParameter("street_name"));
        customer.setCity(request.getParameter("city"));
        customer.setProvince(request.getParameter("province"));
        customer.setPostal_code(request.getParameter("postal_code"));
        customer.setUsername(request.getParameter("username"));
        customer.setPassword(request.getParameter("password"));
       
        String savings = request.getParameter("savingsAcct");
        customer.setSavings((savings == null) ? "no" : "yes");

        String chequing = request.getParameter("chequingAcct");
        customer.setChequing((chequing == null) ? "no" : "yes");
             
        Set<ConstraintViolation<Customer>> errors
                = ValidatorUtil.getValidator().validate(customer);
        if (errors.isEmpty())
        {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            session.setAttribute("login_name", customer.getUsername());
            // no data saving yet, the user must look through and confirm
            return "next"; // show "next.jsp"
        }
        else
        {
            request.setAttribute("errors", errors);
            return input(request); // go back to showing "input.jsp"
        }
    }

    /**
     * This method is invoked when the user clicks submit in the next.jsp page ("submit.ez").
     * It takes the confirmed customer data and any account creation flags and populates
     * the appropriate database tables with the customer, user and account data.
     * Session attributes are set for the customer to be treated as logged in and 
     * they are redirected to a thank you page.
     * 
     * @param request
     * @param response
     * @return thanks.do and subsequently thanks.jsp
     */
    public static String submit(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null)
        {
            return "expired"; // show "your session has expired" with "expired.jsp"
        }
        else
        {
            Customer customer = (Customer) session.getAttribute("customer");
            UserData.insert(customer);
            CustomerData.insert(customer);
            AccountData.insert(customer);
            
            ArrayList<Account> accounts;
            accounts = AccountData.getAccounts(customer.getUsername());
            
            session.setAttribute("accounts", accounts);

            String userName = String.format("%s",
                    customer.getFirst_name().trim());
            Cookie cookie = new Cookie("userName", userName);
            cookie.setMaxAge(30 * 24 * 60 * 60);// one month in sec
            response.addCookie(cookie);
            session.setAttribute("customer", customer);
            
            return "redirect:thanks.ez";
        }
    }
    
    /**
     * This method is invoked at ("thanks.ez").  Acknowledged customer and sets
     * any necessary session attributes.
     * 
     * @param request
     * @return thanks.jsp
     */
    public static String thanks(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null)
        {
            return "notfound";
        }
        else
        {
            return "thanks";
        }
    }
}
