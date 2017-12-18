package ezbank.controller;

import java.util.*;
import javax.servlet.http.*;
import javax.validation.ConstraintViolation;
import ezbank.business.Customer;
import ezbank.data.AccountData;
import ezbank.data.CustomerData;
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
    
    // a user comes to the default front page at "hello.do"
    public static String login(HttpServletRequest request)
    {
        String userName = CookieUtil.getCookieValue(request.getCookies(), "userName");
        return "redirect:login.jsp";
        //return "login"; // show "login.jsp"
    }

    // a user comes to the data input page at "input.do"
    public static String input(HttpServletRequest request)
    {
        return "input"; // show "input.jsp"
    }

    //  a user clicks on "Forget Me" link to "forget.do"
    public static String forget(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("userName", "pass");
        cookie.setMaxAge(0);
        response.addCookie(cookie); // remove the cookie
        return "redirect:."; // redirect to the default front page
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
        customer.setTel_no(Integer.parseInt(request.getParameter("tel_no")));
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
        HttpSession session = request.getSession(false);
        //String customerID = request.getParameter("customer_id");
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
/*
    // a user clicks on "List All" link to "listall.do", 
    // or a user is redirected to "listall.do" 
    public static String listAll(HttpServletRequest request)
    {
        List<Student> list = StudentData.getAll();
        request.setAttribute("visits", list);
        if (request.isUserInRole("administrator"))
        {
            return "editall";
        }
        else
        {
            return "readall";
        }
    }

    public static String addAssist(HttpServletRequest request)
    {
        if (request.isUserInRole("administrator"))
        {
            return "addassist";
        }
        else
        {
            return "unauthorized";
        }
    }

    public static String listAllAssist(HttpServletRequest request)
    {
        List<Assistant> list = AssistantData.getAll();
        request.setAttribute("visits", list);
        if (request.isUserInRole("administrator"))
        {
            return "listallassist";
        }
        else
        {
            return "unauthorized";
        }
    }

    // a user clicks on "Clear All" link to "clearall.do"
    public static String clearAll()
    {
        StudentData.delete();
        return "redirect:listall.do";
    }

    // a user clicks "Edit" link (in the table) to "edit.do" 
    public static String edit(HttpServletRequest request)
    {

        try
        {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = StudentData.get(id);
            if (student != null)
            {
                request.setAttribute("student", student);
                return "edit"; // show the student data in the form to edit
            }
            else
            {
                return "redirect:listall.do";
            }
        }
        catch (NumberFormatException e)
        {
            return "notfound";
        }
    }

    public static String editAssist(HttpServletRequest request)
    {

        try
        {
            String username = request.getParameter("username");
            Assistant assistant = AssistantData.getAssist(username);

            if (assistant != null)
            {
                request.setAttribute("assistant", assistant);
                return "editassist";
            }
            else
            {
                return "redirect:input.jsp";
            }
        }
        catch (NumberFormatException e)
        {
            return "notfound";
        }
    }

    // a user clicks "Update" button in "edit.jsp", 
    // the form submits the data to "update.do"
    public static String update(HttpServletRequest request)
    {
        Student student = new Student();
        try
        {
            student.setId(Integer.parseInt(request.getParameter("id")));
            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            student.setProgram(request.getParameter("program"));
            student.setYear(request.getParameter("year"));
            String coop = request.getParameter("coop");
            student.setCoop((coop == null) ? "no" : "yes");
            Set<ConstraintViolation<Student>> errors
                    = ValidatorUtil.getValidator().validate(student);
            if (errors.isEmpty())
            {
                StudentData.update(student);
                return "redirect:listall.do";
            }
            else
            {

                request.setAttribute("errors", errors);
                request.setAttribute("student", student);
                return "edit";
            }
        }
        catch (NumberFormatException e)
        {
            return "notfound";
        }
    }

    public static String updateAssist(HttpServletRequest request)
    {
        Assistant assistant = new Assistant();

        assistant.setUsername(request.getParameter("username"));
        assistant.setRole(request.getParameter("role"));

        String originalUser = request.getParameter("originalUser");

        AssistantData.update(assistant);

        return "redirect:listallassist.do";
    }

    // a user cliks "Delete" link (in the table) to "delete.do"
    public static String delete(HttpServletRequest request)
    {
        try
        {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = StudentData.get(id);
            if (student != null)
            {
                request.setAttribute("student", student);
                return "delete"; // ask "Do you really want to remove the data?"
            }
            else
            {
                return "redirect:listall.do";
            }
        }
        catch (NumberFormatException e)
        {
            return "notfound";
        }
    }

    public static String deleteAssist(HttpServletRequest request)
    {
        try
        {

            String username = request.getParameter("username");

            Assistant assistant = AssistantData.getAssist(username);
            if (assistant != null)
            {
                request.setAttribute("assistant", assistant);
                return "deleteassist";
            }
            else
            {
                return "redirect:listallassist.do";
            }
        }
        catch (NumberFormatException e)
        {
            return "notfound";
        }
    }

    // a user clicks "Remove Record" button in "delete.jsp",
    // the form submits the data to "remove.do"
    public static String remove(HttpServletRequest request)
    {
        try
        {
            StudentData.delete(Integer.parseInt(request.getParameter("id")));
        }
        catch (NumberFormatException e)
        {
            return "notfound";
        }
        return "redirect:listall.do";
    }

    public static String removeAssist(HttpServletRequest request)
    {
        AssistantData.delete(request.getParameter("username"));

        return "redirect:listallassist.do";
    }

    // a user clicks "Change password" link
    public static String changePassword(HttpServletRequest request)
    {
        request.setAttribute("message", "Enter your new password twice");
        return "passwords";
    }

    // a user clicks "Change Password" button in "passwords.jsp",
    // the form submits data to "/update_password.do"
    public static String updatePassword(HttpServletRequest request)
    {
        String login = request.getRemoteUser();
        if (login == null)
        {
            return "expired";
        }
        String message;
        String current_password = request.getParameter("current_password");
        String new_password_1 = request.getParameter("new_password_1");
        String new_password_2 = request.getParameter("new_password_2");
        if (current_password == null || current_password.isEmpty())
        {
            message = "Current password input is left empty.";
        }
        else if (LoginData.checkPassword(login, current_password))
        {
            if (new_password_1 == null || new_password_1.isEmpty()
                    || new_password_2 == null || new_password_2.isEmpty())
            {
                message = "New password input is left empty.";
            }
            else if (new_password_1.equals(new_password_2))
            {
                LoginData.updatePassword(login, new_password_1);
                message = "Your password has been changed.";
            }
            else
            {
                message = "New password inputs do not match.";
            }
        }
        else
        {
            message = "Your current password input is wrong.";
        }
        request.setAttribute("message", message);

        return "passwords";
    }
*/

}
