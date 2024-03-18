public class Test {

    public static String longAddition(String num1, String num2) {
        StringBuilder result = new StringBuilder();

        int carry = 0;
        int maxLength = Math.max(num1.length(), num2.length());

        // Pad the shorter number with leading zeros to make them equal length
        num1 = padWithZeros(num1, maxLength);
        num2 = padWithZeros(num2, maxLength);

        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = Character.getNumericValue(num1.charAt(i));
            int digit2 = Character.getNumericValue(num2.charAt(i));

            int sum = digit1 + digit2 + carry;

            carry = sum / 10;
            result.insert(0, sum % 10);
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

    // Function to pad a string with leading zeros
    private static String padWithZeros(String str, int length) {
        StringBuilder paddedStr = new StringBuilder(str);
        while (paddedStr.length() < length) {
            paddedStr.insert(0, '0');
        }
        return paddedStr.toString();
    }

    public static void main(String[] args) {
        String num1 = "437865439763875307";
        String num2 = "84750437705233479837";

        String sum = longAddition(num1, num2);

        System.out.println("Sum: " + sum);
    }
}
