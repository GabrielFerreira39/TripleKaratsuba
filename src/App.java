import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        String strNum1 = "123456";
        String strNum2 = "123456";

        numCorrector(strNum1, strNum2);

    }

    public static String numCorrector(String strNum1, String strNum2) {

        String a1 = "", a2 = "", a3 = "", b1 = "", b2 = "", b3 = "";

        if (strNum2.length() > strNum1.length()) {
            String temp = strNum1;
            strNum1 = strNum2;
            strNum2 = temp;
        }

        int n1 = strNum1.length();
        int n2 = strNum2.length();

        try {

            for (int i = 0; i < n1; i++) {
                a1 += strNum1.charAt(i);
                a2 += strNum1.charAt(n1 / 3 + i);
                a3 += strNum1.charAt((2 * n1) / 3 + i);

                b1 += strNum2.charAt(i);
                b2 += strNum2.charAt(n1 / 3 + i);
                b3 += strNum2.charAt((2 * n1) / 3 + i);
            }
        }

        catch (Exception e) {
            int j = a1.length() - a3.length();
            if (j > 0) {
                for (int i = 0; i < j; i++) {
                    a3 = "0" + a3;
                }
            }
            int h = b1.length() - b3.length();
            if (h > 0) {
                for (int i = 0; i < h; i++) {
                    b3 = "0" + b3;
                }

            }

        }

        return a1;

    }

    public String longAddition(String num1, String num2, String num3) {
        StringBuilder result = new StringBuilder();

        int carry = 0;
        int maxLength = Math.max(num1.length(), num2.length());

        num1 = padWithZeros(num1, maxLength);
        num2 = padWithZeros(num2, maxLength);
        num3 = padWithZeros(num3, maxLength);

        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = Character.getNumericValue(num1.charAt(i));
            int digit2 = Character.getNumericValue(num2.charAt(i));
            int digit3 = Character.getNumericValue(num3.charAt(i));

            int sum = digit1 + digit2 + digit3 + carry;

            carry = sum / 10;
            result.insert(0, sum % 10);
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

    private static String padWithZeros(String str, int length) {
        StringBuilder paddedStr = new StringBuilder(str);
        while (paddedStr.length() < length) {
            paddedStr.insert(0, '0');
        }
        return paddedStr.toString();
    }

}
