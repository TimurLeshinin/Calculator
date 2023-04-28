import java.util.Scanner;

public class Main {

    static final String dictionary_nums_a = "0123456789";
    static final String dictionary_nums_r = "IVXLCDM";


    // -1 - error format
    //  0 - Arabic nums
    //  2 - Roman nums
    static int check_digits(String s) {
        boolean f1 = false;
        boolean f2 = false;
        int counter =0;
        int len = 0;
        int counterOp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (dictionary_nums_a.contains(String.valueOf(s.charAt(i)))) {
                f1 = true;
                len++;
            } else if (dictionary_nums_r.contains(String.valueOf(s.charAt(i)))) {
                f2 = true;
                len++;
            }
            else {

                if("+-*/".contains(String.valueOf(s.charAt(i))))
                {
                    counterOp++;
                }

                if(len > 0) {
                    len =0;
                    counter++;
                }
            }
        }

        if (f1 == f2 || counter != 2 || counterOp != 1) {
            return -1;
        }
        return f2 ? 1 : 0;
    }


    static int romanDigitToInt(char ch) {
        return switch (ch) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    static int romanToArabic(String num) {
        if (num.isEmpty())
            return 0;

        int value = 0;

        int current = romanDigitToInt(num.charAt(0));

        for (int i = 0; i < num.length() - 1; i++) {

            int currentDigit = romanDigitToInt(num.charAt(i));
            int nextDigit = romanDigitToInt(num.charAt(i + 1));

            if (currentDigit == nextDigit) {
                current += nextDigit;
            } else if (currentDigit < nextDigit) {
                current = nextDigit - current;
            } else {
                value += current;
                current = nextDigit;
            }
        }

        return value + current;
    }


    static String intToRoman(int num) {
        if (num < 0 || num == 0)
            throw new IllegalArgumentException("negative numbers do not exist in Roman notation");

        StringBuilder number = new StringBuilder();

        while (num >= 1000) {
            num -= 1000;
            number.append("M");
        }

        while (num >= 500) {
            num -= 500;
            number.append("D");
        }

        while (num >= 100) {
            num -= 100;
            number.append("C");
        }

        while (num >= 50) {
            num -= 50;
            number.append("L");
        }

        while (num >= 10) {
            num -= 10;
            number.append("X");
        }

        while (num >= 5) {
            num -= 5;
            number.append("V");
        }
        while (num >= 1) {
            num -= 1;
            number.append("I");
        }
        int index = -1;
        while ((index = number.indexOf("IIII")) >= 0) {
            number.replace(index, index + 4, "IV");
        }

        while ((index = number.indexOf("VIV")) >= 0) {
            number.replace(index, index + 3, "IX");
        }

        while ((index = number.indexOf("IIII")) >= 0) {
            number.replace(index, index + 4, "IV");
        }

        while ((index = number.indexOf("VIV")) >= 0) {
            number.replace(index, index + 3, "IX");
        }

        while ((index = number.indexOf("XXXX")) >= 0) {
            number.replace(index, index + 4, "XL");
        }

        while ((index = number.indexOf("LXL")) >= 0) {
            number.replace(index, index + 3, "XC");
        }

        while ((index = number.indexOf("CCCC")) >= 0) {
            number.replace(index, index + 4, "CD");
        }

        while ((index = number.indexOf("DCD")) >= 0) {
            number.replace(index, index + 3, "CM");
        }
        return number.toString();
    }

    static int Calculate(int first,int second, char op)
    {
       return switch (op)
        {
            case '+' -> first + second;
            case '-' -> first - second;
            case '*' -> first * second;
            case '/' -> first / second;
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String exp = scanner.nextLine().toUpperCase();


        int a = check_digits(exp);

        if (a == -1) {
            System.err.println("Number format exception");
            return;
        }
        // a + b, a - b, a * b, a / b
         String[] strings = exp.split(" ");

        try{
            String result;
            if(a ==0)
            {
                result = String.valueOf(Calculate(Integer.parseInt(strings[0]), Integer.parseInt(strings[2]), strings[1].charAt(0)));

            }
            else{

                result = intToRoman(Calculate(romanToArabic(strings[0]),romanToArabic(strings[2]), strings[1].charAt(0)));
            }

            System.out.print(exp + " = " + result);
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }


    }
}
