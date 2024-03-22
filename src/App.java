import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {

        String strNum1 = "123";
        String strNum2 = "456";

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

        if (n1 == 1 && n2 == 1) {
            int answer = strNum1.length() * Integer.parseInt(strNum2);
            return Integer.toString(answer);
        }

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

        String a1b1 = mult(a1, b1); // 4
        String a1b2 = mult(a1, b2); // 3
        String a1b3 = mult(a1, b3); // 2
        String a2b1 = mult(a2, b1); // 3
        String a2b2 = mult(a2, b2); // 2
        String a2b3 = mult(a2, b3); // 1
        String a3b1 = mult(a3, b1); // 2
        String a3b2 = mult(a3, b2); // 1
        String a3b3 = mult(a3, b3); // 0

        String e1 = potency(a2.length() + a3.length());
        String e2 = potency(a3.length());

        return removerZeros(
                longAddition(
                        longAddition(
                                longAddition(longAddition(
                                        longAddition(longAddition(
                                                longAddition(longAddition(a1b1.concat(e1).concat(e1),
                                                        a1b2.concat(e1).concat(e2)), a1b3.concat(e1)),
                                                a2b1.concat(e1).concat(e2)), a2b2.concat(e2).concat(e2)),
                                        a2b3.concat(e2)), a3b1.concat(e1)),
                                a3b2.concat(e2)),
                        a3b3));

    }

    public static String zeroAdder(String str, int n1) {

        str = "0" + str;

        if (str.length() == n1) {
            return str;
        }

        return zeroAdder(str, n1);

    }

    public static String potency(int n) {
        char[] shift = new char[n];
        Arrays.fill(shift, '0');
        return new String(shift);
    }

    private static String removerZeros(String a) {
        return a.replaceAll("^0+", "");
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