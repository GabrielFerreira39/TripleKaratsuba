// import java.util.*;

// public class Main {
// public static void main(String[] args) throws Exception {
// Scanner in = new Scanner(System.in);

// String strNum1 = "123456";
// String strNum2 = "123456";

// numCorrector(strNum1, strNum2);

// }

// public static String numCorrector(String strNum1, String strNum2) {

// String a1 = "", a2 = "", a3 = "", b1 = "", b2 = "", b3 = "";

// int maxLength = Math.max(strNum1.length(), strNum2.length());

// try {

// for (int i = 0; i < maxLength; i++) {
// a1 += strNum1.charAt(i);
// a2 += strNum1.charAt(maxLength / 3 + i);
// a3 += strNum1.charAt((2 * maxLength) / 3 + i);

// b1 += strNum2.charAt(i);
// b2 += strNum2.charAt(maxLength / 3 + i);
// b3 += strNum2.charAt(2 * maxLength / 3 + i);
// }
// }

// catch (Exception e) {
// int j = a1.length() - a3.length();
// if (j > 0) {
// for (int i = 0; i < j; i++) {
// a3 = "0" + a3;
// }
// }
// int h = b1.length() - b3.length();
// if (h > 0) {
// for (int i = 0; i < h; i++) {
// b3 = "0" + b3;
// }

// }

// }

// return a1;

// }

// public String longAddition(String num1, String num2, String num3) {
// StringBuilder result = new StringBuilder();

// int carry = 0;
// int maxLength = Math.max(num1.length(), Math.max(num2.length(),
// num3.length()));

// num1 = padWithZeros(num1, maxLength);
// num2 = padWithZeros(num2, maxLength);
// num3 = padWithZeros(num3, maxLength);

// for (int i = maxLength - 1; i >= 0; i--) {
// int digit1 = Character.getNumericValue(num1.charAt(i));
// int digit2 = Character.getNumericValue(num2.charAt(i));
// int digit3 = Character.getNumericValue(num3.charAt(i));

// int sum = digit1 + digit2 + digit3 + carry;

// carry = sum / 10;
// result.insert(0, sum % 10);
// }

// if (carry > 0) {
// result.insert(0, carry);
// }

// return result.toString();
// }

// private static String padWithZeros(String str, int length) {
// StringBuilder paddedStr = new StringBuilder(str);
// while (paddedStr.length() < length) {
// paddedStr.insert(0, '0');
// }
// return paddedStr.toString();
// }

// }

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Arrays.fill;
import java.util.Arrays;

class Katsuba {
    public static void main(String[] args) {
        System.out.println(KatsubraTriplo("123", "456213123"));
    }

    public static String KatsubraTriplo(String x, String y) {
        // Condicoes primarias do algoritmo
        if (x.length() == 1 || y.length() == 1) {
            return Integer.toString(Integer.parseInt(x) * Integer.parseInt(y));
        }

        // Concatenar os numeros com "0" para que o tamanho dos strings seja sempre o
        // mesmo
        while (x.length() != y.length()) {
            if (x.length() > y.length()) {
                y = "0".concat(y);
            } else {
                x = "0".concat(x);
            }

        }

        // Calculando o numero de digitos do numero
        int tamanhoParte = x.length() / 3;
        String x1, x2, x3, y1, y2, y3;

        if (x.length() == 2) {
            x1 = "0";
            x2 = x.substring(0, 1);
            x3 = x.substring(1);

            y1 = "0";
            y2 = y.substring(0, 1);
            y3 = y.substring(1);
        } else {

            // Separando o primeiro numero x em três pedacos
            x1 = x.substring(0, tamanhoParte);
            x2 = x.substring(tamanhoParte, tamanhoParte * 2);
            x3 = x.substring(tamanhoParte * 2);

            // Separando o segundo numero y em tres pedacos
            y1 = y.substring(0, tamanhoParte);
            y2 = y.substring(tamanhoParte, tamanhoParte * 2);
            y3 = y.substring(tamanhoParte * 2);

        }

        // 9 Operacoes recursivas
        String x1y1 = KatsubraTriplo(x1, y1);
        String x1y2 = KatsubraTriplo(x1, y2);
        String x1y3 = KatsubraTriplo(x1, y3);

        String x2y1 = KatsubraTriplo(x2, y1);
        String x2y2 = KatsubraTriplo(x2, y2);
        String x2y3 = KatsubraTriplo(x2, y3);

        String x3y1 = KatsubraTriplo(x3, y1);
        String x3y2 = KatsubraTriplo(x3, y2);
        String x3y3 = KatsubraTriplo(x3, y3);

        // Determinando os shifts
        String e1 = shift(x2.length() + x3.length());
        String e2 = shift(x3.length());

        // Fazendo a operacao final de conquista
        return removerZeros(
                somaString(
                        somaString(
                                somaString(somaString(
                                        somaString(somaString(
                                                somaString(somaString(x1y1.concat(e1).concat(e1),
                                                        x1y2.concat(e1).concat(e2)), x1y3.concat(e1)),
                                                x2y1.concat(e1).concat(e2)), x2y2.concat(e2).concat(e2)),
                                        x2y3.concat(e2)), x3y1.concat(e1)),
                                x3y2.concat(e2)),
                        x3y3));

    }

    // Metodo para fazer o Shift
    public static String shift(int length) {
        char[] shift = new char[length];
        Arrays.fill(shift, '0');
        return new String(shift);
    }

    // Metodo para somar as Strings
    public static String somaString(String a, String b) {
        int aux = 0;
        StringBuilder somafinal = new StringBuilder();

        while (a.length() != b.length()) {
            if (a.length() > b.length()) {
                b = "0".concat(b);
            } else {
                a = "0".concat(a);
            }

        }

        for (int i = a.length() - 1; i >= 0; i--) {
            int x = parseInt(valueOf(a.charAt(i)));
            int y = parseInt(valueOf(b.charAt(i)));

            String soma = Integer.toString(x + y + aux);
            aux = 0;

            if (soma.length() > 1) {
                aux = 1;
                soma = valueOf(soma.charAt(1));
            }

            somafinal.insert(0, soma);
        }

        if (aux == 1) {
            somafinal.insert(0, aux);
        }

        return somafinal.toString();
    }

    // Remover os zeros do inicio do numero que sao desnecessarios
    private static String removerZeros(String a) {
        return a.replaceAll("^0+", "");
    }
}