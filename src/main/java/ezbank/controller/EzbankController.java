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

public class EzbankController
{

    // a user comes to the default front page at "hello.do"
    public static String hello(HttpServletRequest request)
    {
        String userName = CookieUtil.getCookieValue(request.getCookies(), "userName");
        if (userName.isEmpty())
        {
            return input(request);
        }
        return "hello"; // show "hello.jsp"
    }

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
            
            return "transaction"; // show "transaction.jsp"
        }
        else
        {
            HttpSession session = request.getSession();
            message = "Invalid login credentials.  Please try again.";
            session.setAttribute("message", message);
            return login(request); // go back to showing "login.jsp"
        }
    }

    public static String input(HttpServletRequest request)
    {
        return "input"; // show "input.jsp"
    }

    // a user comes to the input page at "transaction.do"
    public static String transaction(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String login_name = session.getAttribute("login_name").toString();
        
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        
        session.setAttribute("accounts", accounts);

        return "transaction"; // show "transaction.jsp"
    }
    
    // a user comes to the data input page at "chequing.do"    
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
    
    // a user comes to the data input page at "savings.do"    
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

            return "savings"; // show "printe.jsp"
        }
    }
    
    // a user comes to the page at "transfer.do"    
    public static String transfer(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        
        String login_name = session.getAttribute("login_name").toString();
        
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        
        session.setAttribute("accounts", accounts);
        
        return "transfer"; // show "transfer.jsp"
    }
    
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
                return "transaction";
            }
        }
        else
        {
            message = "[Account Selection Error]:  Cannot transfer funds to the same account";
            request.setAttribute("message", message);
            return "transaction";
        }
        
        
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(login_name);
        
        session.setAttribute("accounts", accounts);
        
        return "transfer"; // show "transfer.jsp"
    }

    // a user comes to the data input page at "deposite.do"    
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
        
        return "transaction"; // show "transaction.jsp"
    }
    
     // a user comes to the data input page at "printe.do"    
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
            return "transaction";
        }
        
        session.setAttribute("customer", customer);
        session.setAttribute("login_name", customer.getUsername());
        ArrayList<Account> accounts;
        accounts = AccountData.getAccounts(customer.getUsername());
        session.setAttribute("accounts", accounts);
        
        return "transaction"; // show "transaction.jsp"
    }
    
    //  a user clicks on "Logout" link to "forget.do"
    public static String logout(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("userLogin", "pass");
        cookie.setMaxAge(0);
        response.addCookie(cookie); // remove the cookie
        return "redirect:logout.jsp"; // redirect to the default front page
    }

    // user clicks on "Continue" button in "input.jsp", 
    // the form submits the data to "next.do"
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

    /*
    public static String nextAssist(HttpServletRequest request)
    {

        Assistant assistant = new Assistant();
        assistant.setUsername(request.getParameter("assistUser"));
        assistant.setPassword(request.getParameter("pass"));

        HttpSession session = request.getSession();
        session.setAttribute("assistant", assistant);

        return "nextassist";
    }
     */
    // a user clicks "Register" button in "next.jsp", the form submits to "submit.do"
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
            
            return "redirect:thanks.do";
        }
    }

    

    /*
    public static String submitAssist(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("assistant") == null)
        {
            return "expired"; // show "your session has expired" with "expired.jsp"
        }
        else
        {

            Assistant assistant = (Assistant) session.getAttribute("assistant");
            AssistantData.insert(assistant);

            return "redirect:listallassist.do";
        }
    }
     */
    // a user is redirected to "Thank you" page at "thanks.do"
    public static String thanks(HttpServletRequest request)
    {
        // I REMOVED FALSE FROM request.getSession();
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
