package sdf;

import java.io.Console;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Welcome.");

        Console cons = System.console();

        boolean exit = false;
        String input = "";
        double last = 0;  // variable to store last value
        

        while (!exit) {
  
            double num1 = 0;
            double num2 = 0;
            String operand = "";
            double result = 0;

            input = cons.readLine("> ");

            String[] terms = input.trim().split(" ");
            
            if (terms[0].equalsIgnoreCase("exit")) {
                exit = true;
                System.out.println("Bye Bye");
                break;
            }

            if (terms.length == 3) {
                if (!terms[0].equals("$last")) {
                    num1 = Double.parseDouble(terms[0]);
                }
                else {
                    num1 = last;
                }

                if (!terms[2].equals("$last")) {
                    num2 = Double.parseDouble(terms[2]);
                }
                else {
                    num2 = last;
                }
                operand = terms[1];
            }

            if (operand.equals("+")) {
                result = num1 + num2;
                System.out.println(result);
                last = result;
            }
            else if (operand.equals("-")) {
                result = num1 - num2;
                System.out.println(result);
                last = result;
            }
            else if (operand.equals("/")) {
                result = num1 / num2;
                System.out.println(result);
                last = result;
            }
            else if (operand.equals("*")) {
                result = num1 * num2;
                System.out.println(result);
                last = result;
            }

        }
    }
}
