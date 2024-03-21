public class App {

    public static void main(String[] args) throws Exception {

        String strNum1 = "123456789";
        String strNum2 = "123456789";

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

        String a1b1 = potency(strNum1.length(), mult(a1, b1));
        String a1b2 = potency(3, mult(a1, b2));
        String a1b3 = potency(2, mult(a1, b3));
        String a2b1 = potency(3, mult(a2, b1));
        String a2b2 = potency(2, mult(a2, b2));
        String a2b3 = potency(1, mult(a2, b3));
        String a3b1 = potency(2, mult(a3, b1));
        String a3b2 = potency(1, mult(a3, b2));
        String a3b3 = potency(0, mult(a3, b3));

        String result = longAddition(a1b1, a1b2);
        result = longAddition(result, a1b3);
        result = longAddition(result, a2b1);
        result = longAddition(result, a2b2);
        result = longAddition(result, a2b3);
        result = longAddition(result, a3b1);
        result = longAddition(result, a3b2);
        result = longAddition(result, a3b3);

        return result;

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
            str = str + "0";
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