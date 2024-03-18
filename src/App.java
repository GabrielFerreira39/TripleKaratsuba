import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        String strNum1 = "6753480572358";
        String strNum2 = "978349839884938";

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

}
