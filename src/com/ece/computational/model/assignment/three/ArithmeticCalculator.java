package com.ece.computational.model.assignment.three;

import java.util.Stack;

/**
 * An Arithmetic calculator class that can take an expression of arbitrary lengths, levels of parentheses, and
 * multi-digit numbers, and produce the final result
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class ArithmeticCalculator {

    public int calculate(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                numbers.push(num);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(operation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && precedence(c, operators.peek())) {
                    numbers.push(operation(operators.pop(), numbers.pop(),numbers.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(operation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private int operation(char op, int b, int a) {

        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }

    private boolean precedence(char op1, char op2) {

        if (op2 == '(' || op2 == ')') {
            return false;
        } else if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator();

        System.out.println("1+(2/2*2+1) = " + arithmeticCalculator.calculate("1+(2/2*2+1)"));

        System.out.println("11+(2/2*2+1) = " + arithmeticCalculator.calculate("11+(2/2*2+1)"));

        System.out.println("11+(2+22)-5+76+(4/2) = " + arithmeticCalculator.calculate("11+(2+22)-5+76+(4/2)"));
    }
}
