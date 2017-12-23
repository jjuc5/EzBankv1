/*
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
*/
package ezbank.login;

import java.security.SecureRandom;
import org.apache.catalina.realm.MessageDigestCredentialHandler;

/**
 * This class is used for Salting the password. We are using the SHA-256
 * hash to salt the password.
 * @author melan
 */
public class SaltedPassword
{

    final private static String symbols
            = "ABCDEFGHIJKMNPQRSTUVWXYZ"
            + "abcdefghijkmnpqrstuvwxyz"
            + "23456789";

    final private static int SALT_LENGTH = 32;
    final private static int NUM_OF_ITERATIONS = 100;
    final private static String ALGORITHM = "SHA-256";

    /**
     * This method creates a random string which will be appended to the password.
     * We pass the length of the string we want to have appended.
     * @param length
     * @return 
     */
    public static String random(int length)
    {

        StringBuilder buf = new StringBuilder(length);
        SecureRandom rand = new SecureRandom();

        for (int i = 0; i < length; ++i)
        {
            buf.append(symbols.charAt(rand.nextInt(symbols.length())));
        }

        return buf.toString();
    }

    /**
     * This method compares one password against another password. The two passwords
     * are passed into the class and a value of true or false is returned.
     * @param p1 = 1st password
     * @param p2 = 2nd password
     * @return 
     */
    public static boolean equals(String p1, String p2)
    {
        if (p1 != null && p2 != null)
        {
            return p1.equals(p2);
        }
        else
        {
            return false;
        }
    }

    /**
     * This method encodes the password using the salt. The password is passed
     * as a parameter into the method
     * @param password = the original password
     * @return 
     */
    public static String encode(String password)
    {

        MessageDigestCredentialHandler handler
                = new MessageDigestCredentialHandler();

        try
        {
            handler.setAlgorithm(ALGORITHM);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getCause());
        }

        handler.setIterations(NUM_OF_ITERATIONS);
        handler.setSaltLength(SALT_LENGTH);
        handler.setEncoding("UTF-8");

        String code = handler.mutate(password);
        return code;
    }

    /** 
     * This method checks whether an entered password matches the one stored on file.
     * It does so by salting the incoming password and verifying it against the 
     * value that is stored on file.
     * @param password = original password
     * @param code = salted password
     * @return 
     */
    public static boolean match(String password, String code)
    {

        MessageDigestCredentialHandler handler
                = new MessageDigestCredentialHandler();

        try
        {
            handler.setAlgorithm(ALGORITHM);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getCause());
        }

        handler.setIterations(NUM_OF_ITERATIONS);
        handler.setSaltLength(SALT_LENGTH);
        handler.setEncoding("UTF-8");

        return handler.matches(password, code);
    }

    /**
     * This program is used for Salted passwords.
     * @param args 
     */
    public static void main(String[] args)
    {
        String password = "sesame";

        for (int i = 1; i <= 10; ++i)
        {
            String hash = encode(password);
            System.out.println();
            System.out.println("password " + i + ":");
            System.out.println("used password = [" + password + "]");
            System.out.println("stored password = [" + hash + "]");
            System.out.println("stored length = " + hash.length());
            System.out.println("matched = " + match(password, hash));
        }
    }

}
