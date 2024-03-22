import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws Exception {

        while (true) {

            Scanner sc = new Scanner(System.in);

            String strNum1 = sc.nextLine();
            String strNum2 = sc.nextLine();

            System.out.println("R: " + zeroRemover(mult(strNum1, strNum2)));
        }

    }

    public static String mult(String strNum1, String strNum2) {

        String a1 = "", a2 = "", a3 = "", b1 = "", b2 = "", b3 = "";

        if (strNum1.length() < strNum2.length()) {
            String temp = strNum1;
            strNum1 = strNum2;
            strNum2 = temp;
        }

        int n1 = strNum1.length();
        int n2 = strNum2.length();

        if (n1 == 1 && n2 == 1) {
            int answer = Integer.parseInt(strNum1) * Integer.parseInt(strNum2);
            return Integer.toString(answer);
        }

        if (n1 != n2) {
            strNum2 = zeroAdder(strNum2, n1);
        }

        int divide1 = n1 / 3;
        int divide2 = divide1 * 2;

        if (n1 == 2) {
            a1 = "0";
            a2 = strNum1.substring(0, 1);
            a3 = strNum1.substring(1);
            b1 = "0";
            b2 = strNum2.substring(0, 1);
            b3 = strNum2.substring(1);

        } else {

            a1 = strNum1.substring(0, divide1);
            a2 = strNum1.substring(divide1, divide2);
            a3 = strNum1.substring(divide2);
            b1 = strNum2.substring(0, divide1);
            b2 = strNum2.substring(divide1, divide2);
            b3 = strNum2.substring(divide2);
        }

        String a1b1 = mult(a1, b1); // 4
        String a1b2 = mult(a1, b2); // 3
        String a1b3 = mult(a1, b3); // 2
        String a2b1 = mult(a2, b1); // 3
        String a2b2 = mult(a2, b2); // 2
        String a2b3 = mult(a2, b3); // 1
        String a3b1 = mult(a3, b1); // 2
        String a3b2 = mult(a3, b2); // 1
        String a3b3 = mult(a3, b3); // 0

        String shift2 = potency(a2.length() + a3.length());
        String shift1 = potency(a3.length());

        String ShiftedA1b1 = a1b1 + shift2 + shift2;
        String ShiftedA1b2 = a1b2 + shift2 + shift1;
        String ShiftedA1b3 = a1b3 + shift2;
        String ShiftedA2b1 = a2b1 + shift2 + shift1;
        String ShiftedA2b2 = a2b2 + shift1 + shift1;
        String ShiftedA2b3 = a2b3 + shift1;
        String ShiftedA3b1 = a3b1 + shift2;
        String ShiftedA3b2 = a3b2 + shift1;

        return longAddition(
                longAddition(
                        longAddition(
                                longAddition(
                                        longAddition(
                                                longAddition(
                                                        longAddition(
                                                                longAddition(
                                                                        ShiftedA1b1, ShiftedA1b2),
                                                                ShiftedA1b3),
                                                        ShiftedA2b1),
                                                ShiftedA2b2),
                                        ShiftedA2b3),
                                ShiftedA3b1),
                        ShiftedA3b2),
                a3b3);

    }

    public static String zeroAdder(String str, int n1) {

        str = "0" + str;

        if (str.length() == n1) {
            return str;
        }

        return zeroAdder(str, n1);

    }

    public static String zeroRemover(String string) {
        Pattern pattern = Pattern.compile("^0+");
        Matcher matcher = pattern.matcher(string);
        return matcher.replaceFirst("");
    }

    public static String potency(int n) {
        String zeroes = "";
        for (int i = 0; i < n; i++) {
            zeroes = zeroes + "0";
        }
        return zeroes;
    }

    public static String longAddition(String num1, String num2) {
        StringBuilder result = new StringBuilder();

        int carry = 0;
        int maxLength = Math.max(num1.length(), num2.length());

        for (int i = 0; i < maxLength; i++) {
            int digit1 = i < num1.length() ? Character.getNumericValue(num1.charAt(num1.length() - 1 - i)) : 0;
            int digit2 = i < num2.length() ? Character.getNumericValue(num2.charAt(num2.length() - 1 - i)) : 0;

            int sum = digit1 + digit2 + carry;

            carry = sum / 10;
            result.insert(0, sum % 10);
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

}