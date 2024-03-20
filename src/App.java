
public class App {
    public static void main(String[] args) throws Exception {

        String strNum1 = "999";
        String strNum2 = "123";

        mult(strNum1, strNum2);

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

        if (n1 == 1) {
            int answer = Integer.parseInt(strNum1) * Integer.parseInt(strNum2);
            return Integer.toString(answer);
        }

        mult(a1, b1);
        mult(a1, b2);
        mult(a1, b3);
        mult(a2, b1);
        mult(a2, b2);
        mult(a2, b3);
        mult(a3, b1);
        mult(a3, b2);
        mult(a3, b3);

        karatsuba(a1, a2, a3, b1, b2, b3);

        return null;
    }

    public static String zeroAdder(String str, int n1) {

        str = "0" + str;

        if (str.length() == n1) {
            return str;
        }

        return zeroAdder(str, n1);

    }

    public static String longAddition(String num1, String num2, String num3) {
        StringBuilder result = new StringBuilder();

        int carry = 0;
        int maxLength = Math.max(num1.length(), Math.max(num2.length(),
                num3.length()));

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

    public static String karatsuba(String a1, String a2, String a3, String b1, String b2, String b3) {
        String answer = mult(longAddition(a1, a2, a3), longAddition(b1, b2, b3));
        System.out.println(answer);
        return answer;
    }

}