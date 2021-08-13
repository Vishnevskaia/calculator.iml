package com.company;

import java.util.*;
import java.lang.Exception;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String String1 = sc.nextLine();
        if (String1.length() < 5) throw new Exception("Строка не является математической операцией");
        String[] String2 = String1.split(" ");

        String Operand1 = String2[0];
        String Operator = String2[1];
        String Operand2 = String2[2];

        Boolean uslovie1 = false; //Boolean uslovie1  - a содержит римские цифры
        String Rimskie = "IXV";
        for (int i = 0; i < Rimskie.length(); i++) {
            if (Operand1.contains(Rimskie.substring(i, i + 1))) {
                uslovie1 = true;
            }
        }

        Boolean uslovie2 = false; //Boolean uslovie2  - b содержит римские цифры
        for (int j = 0; j < Rimskie.length(); j++) {
            if (Operand2.contains(Rimskie.substring(j, j + 1))) {
                uslovie2 = true;
            }
        }

        int counter = 0; //Проверяем, что оператор - единственный
        for (int k = 0; k < String1.length(); k++) {
            String str2 = String1.substring(k, k + 1);
            if (str2.contains("+")) {
                counter ++;
            } else if (str2.contains("-")){
                counter ++;
            } else if (str2.contains("*")){
                counter ++;
            } else if (str2.contains("/")){
                counter ++;
            }
        }

        boolean uslovie3 = uslovie1 && uslovie2; //если true то в римские
        boolean uslovie4 = (uslovie1 && !uslovie2) || (!uslovie1 && uslovie2); //если true то исключение
        boolean uslovie5 = !uslovie1 && !uslovie2; //если true то в арабские
        boolean uslovie6 = !(Operator.contains("+"))&&!(Operator.contains("-"))&&!(Operator.contains("*"))&&!(Operator.contains("/")); //если true то исключение
        boolean uslovie7 = (counter > 1 || counter == 0);

        if (uslovie4) throw new Exception("Используются одновременно разные системы счисления");
        if (uslovie6) throw new Exception("Строка не является математической операцией; необходимо ввести верный оператор");
        if (uslovie7) throw new Exception("Строка не является математической операцией; строка должна содержать два операнда и один оператор");

        if (uslovie5) { // арабские цифры
            int a = Integer.valueOf(Operand1);
            int b = Integer.valueOf(Operand2);

        if (a < 1 || a > 10 || b < 1 || b > 10)
            throw new Exception("На вход подаются числа от 1 до 10"); // Тут будут все исключения

            Arabskie result = new Arabskie();
            result.a = a;
            result.b = b;
            result.Operator = Operator;
            result.calculate();

        } else if (uslovie3) { // римские цифры
            Rimskie resultr = new Rimskie();
            resultr.Operand1 = Operand1;
            resultr.Operand2 = Operand2;
            resultr.Operator = Operator;
            resultr.calculater();

        }
    }
}

    class Arabskie {
    int a ;
    int b ;
    String Operator;
    int ravno;

    void calculate() {
        if (Operator.equals("+")) {
            System.out.println(a + b);

        } else if
            (Operator.equals("-")) {
            System.out.println(a - b);

        }else if
            (Operator.equals("*")) {
            System.out.println(a * b);

        }else if
            (Operator.equals("/")) {
            System.out.println(a / b);
        }
    }
}

class Rimskie {
    public
    String Operand1;
    String Operand2;
    String Operator;
    String Result_rimskie;
    int result;
    int last_digit;
    int first_digit;
    //int a;
    //int b;
    //int j;
    //int i;
    int index = -1;
    int index2 = -1;
    boolean uslovie10, uslovie11;

    void calculater() throws Exception {

        //int[] Arabskije = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] Rimskije = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] Rimskije10 = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        for (int k = 0; k < 10; k++) {
            if (Rimskije[k].equals(Operand1)) {
                uslovie10 = true;
            }
        }

        for (int k = 0; k < 10; k++) {
            if (Rimskije[k].equals(Operand2)) {
                uslovie11 = true;
            }
        }

        if (!uslovie10 || !uslovie11) throw new Exception("На вход подаются числа от I до X");

        for (int i = 0; (i < Rimskije.length) && (index == -1); i++) {
            if (Rimskije[i].equals(Operand1)) {
                index = i;
            }
        }
        int a = index + 1;

        for (int j = 0; (j < Rimskije.length) && (index2 == -1); j++) {
            if (Rimskije[j].equals(Operand2)) {
                index2 = j;
            }
        }
        int b = index2 + 1;


        if (Operator.equals("+")) {
            result = a + b;

        } else if
        (Operator.equals("-")) {
            result = a - b;

        } else if
        (Operator.equals("*")) {
            result = a * b;

        } else if
        (Operator.equals("/")) {
            result = a / b;
        }

        if (result < 0) throw new Exception("В римской системе нет отрицательных чисел");


        if (result < 10) {
            System.out.println(Rimskije[result - 1]);

        } else if ((result >= 10) && (result < 100)) {
            last_digit = result%10;
            first_digit = result/10;

        if ((last_digit < 9) && (last_digit != 0))  {
            Result_rimskie = (Rimskije10[first_digit - 1]) + (Rimskije[last_digit - 1]);
        } else if ((last_digit == 9)) {
            Result_rimskie = ("I" + (Rimskije10[first_digit]));
        } else if (last_digit == 0) {
            Result_rimskie = (Rimskije10[first_digit - 1]);
        }
            System.out.println(Result_rimskie);


        } else if (result == 100) {
            System.out.println("C");
        }
    }
}





