// Java code for the above approach 

import java.io.*;
import java.util.*;

class Main {

    // Function to find the sum of larger
    // numbers represented as a string
    public static String findSum(String str1, String str2) {
        // Before proceeding further, make sure length of str2 is larger
        if (str1.length() > str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }
        // Stores the result
        String str = "";

        // Calculate length of both string
        int n1 = str1.length();
        int n2 = str2.length();

        // Reverse both of strings
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();

        int carry = 0;
        for (int i = 0; i < n1; i++) {

            // Find the sum of the current digits and carry
            int sum = ((str1.charAt(i) - '0') + (str2.charAt(i) - '0') + carry);
            str += (char) (sum % 10 + '0');

            // Calculate carry for next step
            carry = sum / 10;
        }

        // Add remaining digits of larger number
        for (int i = n1; i < n2; i++) {
            int sum = ((str2.charAt(i) - '0') + carry);
            str += (char) (sum % 10 + '0');
            carry = sum / 10;
        }

        // Add remaining carry
        if (carry != 0)
            str += (char) (carry + '0');

        // Reverse resultant string
        str = new StringBuilder(str).reverse().toString();

        return str;
    }

    // Function to find difference of larger
    // numbers represented as strings
    static String findDiff(String str1, String str2) {
        // Stores the result of difference
        String str = "";

        // Calculate length of both string
        int n1 = str1.length(), n2 = str2.length();

        // Reverse both of strings
        StringBuilder sb1 = new StringBuilder(str1);
        StringBuilder sb2 = new StringBuilder(str2);
        sb1 = sb1.reverse();
        sb2 = sb2.reverse();
        str1 = sb1.toString();
        str2 = sb2.toString();

        int carry = 0;

        // Run loop till small string length
        // and subtract digit of str1 to str2
        for (int i = 0; i < n2; i++) {

            // Compute difference of the
            // current digits
            int sub = ((str1.charAt(i) - '0') - (str2.charAt(i) - '0') - carry);

            // If subtraction < 0 then add 10
            // into sub and take carry as 1
            if (sub < 0) {
                sub = sub + 10;
                carry = 1;
            } else
                carry = 0;

            str += sub;
        }

        // Subtract the remaining digits of
        // larger number
        for (int i = n2; i < n1; i++) {
            int sub = ((str1.charAt(i) - '0') - carry);

            // If the sub value is -ve,
            // then make it positive
            if (sub < 0) {
                sub = sub + 10;
                carry = 1;
            } else
                carry = 0;

            str += sub;
        }

        // Reverse resultant string
        str = new StringBuilder(str).reverse().toString();

        // Return answer
        return str;
    }

    // Function to remove all leading 0s
    // from a given string
    public static String removeLeadingZeros(String str) {
        // Regex to remove leading 0s from a string
        String pattern = "^0+(?!$)";

        // Replaces the matched value with given string
        str = str.replaceAll(pattern, "");
        return str;
    }

    // Function to multiply two numbers
    // using Karatsuba algorithm
    public static String multiply(String A, String B) {
        if (A.length() > B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }

        // Make both numbers to have
        // same digits
        int n1 = A.length(), n2 = B.length();
        while (n2 > n1) {
            A = "0" + A;
            n1++;
        }

        // Add zeros in the beginning of
        // the strings when length is odd

        String A1 = "", A2 = "", A3 = "", B1 = "", B2 = "", B3 = "";

        // Find the values of Al, Ar,
        // Bl, and Br.
        for (int i = 0; i < n1 / 3; ++i) {
            A1 += A.charAt(i);
            B1 += B.charAt(i);
            A2 += A.charAt(n1 / 3 + i);
            B2 += B.charAt(n1 / 3 + i);
            A3 += A.charAt((2 * n1) / 3 + i);
            B3 += B.charAt((2 * n1) / 3 + i);
        }

        if (n1 % 3 == 1) {
            n1++;
            A3 = "0" + A3;
            B3 = "0" + B3;
        }
        // Recursively call the function
        // to compute smaller product

        // Stores the value of Al * Bl
        String p = multiply(A1, B1);

        // Stores the value of Ar * Br
        String q = multiply(A2, B3);

        // Stores value of ((Al + Ar)*(Bl + Br)
        // - Al*Bl - Ar*Br)
        String r = findDiff(multiply(findSum(A1, A2), findSum(B1, B2)), findSum(p, q));

        // Multiply p by 10^n
        for (int i = 0; i < n1; ++i)
            p = p + "0";

        // Multiply s by 10^(n/2)
        for (int i = 0; i < n1 / 2; ++i)
            r = r + "0";

        // Calculate final answer p + r + s
        String ans = findSum(p, findSum(q, r));

        // Remove leading zeroes from ans
        ans = removeLeadingZeros(ans);

        // Return Answer
        return ans;
    }

    public static void main(String[] args) {
        String A = "74638463789";
        String B = "35284567382";
        System.out.println(multiply(A, B));
    }

}
