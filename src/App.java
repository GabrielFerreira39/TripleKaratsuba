import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        String strNum1 = "1231212";
        String strNum2 = "456";

        karatsuba(strNum1, strNum2);

    }

    public static String karatsuba(String strNum1, String strNum2) {

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

        return b3;
    }

    public static String zeroAdder(String str, int n1) {

        str = "0" + str;

        if (str.length() == n1) {
            return str;
        }

        return zeroAdder(str, n1);

    }

}
