package by.bsuir.tritpo.binary;

public class BinaryCalculator {

    public String and(int number1, int number2) {
       return Integer.toBinaryString(number1 & number2);
    }

    public String or(int number1, int number2) {
        return Integer.toBinaryString(number1 | number2);
    }

    public String xor(int number1, int number2) {
        return Integer.toBinaryString(number1 ^ number2);
    }

    public String not(int number) {
        char[] bin = Integer.toBinaryString(number).toCharArray();
        for (int i = 0; i < bin.length; i++) {
            if (bin[i] == '0') {
                bin[i] = '1';
            } else {
                bin[i] = '0';
            }
        }
        return String.copyValueOf(bin);
    }
}
