package pro.arsenev.petprojects.strcalc.app;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Calculator {
    String stringForCalculate;

    String readCalculateString (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Калькулятор вычисляет выражение с учётом арифметичеких правил, " +
                "\n(скобки пока не реализованны, проверка на два знака подряд (напрмер+*)) тоже пока не реализвана");
        System.out.println("Введите строку для вычисления, начиная с числа (например: 1.2+3,4-5*6/2 ENTER:");
        stringForCalculate = scanner.nextLine();
        if (stringForCalculate.charAt(0) == '+' || stringForCalculate.charAt(0) == '-' || stringForCalculate.charAt(0) == '*' || stringForCalculate.charAt(0) == '/') {

        }
        //stringForCalculate = "1.2 + 3,4 - 5*6/2";
        stringForCalculate = stringForCalculate.replaceAll(",", ".");
        // было бы хорошо ещё реализовать проверку-удаление чтобы первым было  число, а не n арфметических знаков
        // System.out.println("stringForCalculate: " + stringForCalculate);
        return stringForCalculate;
    }
    String[] makeArrayForCalculate (String stringForCalculate){
        StringTokenizer tokenizer = new StringTokenizer(stringForCalculate, "+-*/", true);
        String[] arithmeticArray = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            arithmeticArray[i++] = tokenizer.nextToken();
        }
        return arithmeticArray;
    }
    double calculate (String[] arithmeticArray){

        for (int i = 0; i < arithmeticArray.length; i++) {

            if (arithmeticArray[i].equals("/")){
                if (Double.parseDouble(arithmeticArray[i + 1]) == 0) { return  Double.POSITIVE_INFINITY;}  // было бы хороо ещё релизовать МИНУС Infinity
                double res = Double.parseDouble(arithmeticArray[i-1]) / Double.parseDouble(arithmeticArray[i+1]);
                arithmeticArray[i-1] = "";
                arithmeticArray[i] = "";
                arithmeticArray[i+1] = String.valueOf(res);
            }else if (arithmeticArray[i].equals("*")){
                double res = Double.parseDouble(arithmeticArray[i-1]) * Double.parseDouble(arithmeticArray[i+1]);
                arithmeticArray[i-1] = "";
                arithmeticArray[i] = "";
                arithmeticArray[i+1] = String.valueOf(res);
            }
        }

        int count = 0;
        for (int i = 0; i < arithmeticArray.length; i++) {
            if (!arithmeticArray[i].isEmpty()) {
                count++;
            }
        }
        String[] arithmeticArray2 = new String[count];
        count = 0;
        for (int i = 0; i < arithmeticArray.length; i++) {
            if (!arithmeticArray[i].isEmpty()) {
                arithmeticArray2[count] = arithmeticArray[i];
                count++;
            }
        }

        // System.out.println("arithmeticArray2: " + Arrays.toString(arithmeticArray2));

        double result = Double.parseDouble(arithmeticArray2[0]);
        for (int i = 1; i < arithmeticArray2.length; i++) {
            if (arithmeticArray2[i].equals("+")) {
                result = result + Double.parseDouble(arithmeticArray2[i+1]);
            } else if (arithmeticArray2[i].equals("-")) {
                result = result - Double.parseDouble(arithmeticArray2[i+1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String[] arithmeticArray = calculator.makeArrayForCalculate (calculator.readCalculateString ());
        // System.out.println("arithmeticArray: " + Arrays.toString(arithmeticArray));
        System.out.println("Результат: " + calculator.calculate (arithmeticArray));

    }
}