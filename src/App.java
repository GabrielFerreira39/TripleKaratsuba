import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        String strNum1 = "999";
        String strNum2 = "123";

        System.out.println(mult(strNum1, strNum2));

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

        if (n1 != n2) {
            strNum2 = zeroAdder(strNum2, n1);
        }

        int divide1 = n1 / 3;
        int divide2 = divide1 * 2;

        a1 = strNum1.substring(0, divide1);
        a2 = strNum1.substring(divide1, divide2);
        a3 = strNum1.substring(divide2);

        b1 = strNum2.substring(0, divide1);
        b2 = strNum2.substring(divide1, divide2);
        b3 = strNum2.substring(divide2);

        if (n1 == 1 && n2 == 1) {
            int answer = Integer.parseInt(strNum1) * Integer.parseInt(strNum2);
            return Integer.toString(answer);
        }

        String k1 = mult(a1, b1);
        String k2 = mult(a1, b2);
        String k3 = mult(a1, b3);
        String k4 = mult(a2, b1);
        String k5 = mult(a2, b2);
        String k6 = mult(a2, b3);
        String k7 = mult(a3, b1);
        String k8 = mult(a3, b2);
        String k9 = mult(a3, b3);

        return "0";

    }

    public static String zeroAdder(String str, int n1) {

        str = "0" + str;

        if (str.length() == n1) {
            return str;
        }

        return zeroAdder(str, n1);

    }

    public static String potency(int n, String str) {
        for (int i = 0; i < n; i++) {
            str += "0";
        }
        return str;
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