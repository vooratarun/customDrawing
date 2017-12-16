package voora.com.customcalc.calc;

import java.util.*;

/**
 * Created by tarun on 12/6/17.
 */

public class PostFixConverter {
    private String infix;
    private Stack<Character> operatorStack = new Stack<>();
    private List<String> postfixList = new ArrayList<String>();

    public PostFixConverter(String expression) {
        infix = expression;
    }

    /**
     *  It starts the conversion process. It adds operators and operands and decimal values to
     *  the PostfixList.
     */
    public void convertToPostFix() {

        for (int i = 0; i != infix.length(); ++i) {
            if (Character.isDigit(infix.charAt(i))) {
                i = addOperandsToPostFix(infix,i);
            } else {
                addOperatorsToStack(infix.charAt(i));
            }
        }
        addRemainingOperatorsToPostfix();
    }

    /**
     * It adds operands to the postFixList and handles decimal points.
     * @param infix
     * @param i
     * @return the position to be continue evaluation.
     */
    private int addOperandsToPostFix(String  infix , int i) {
        StringBuilder temp = new StringBuilder();
        temp.append(infix.charAt(i));

        while ((i + 1) != infix.length() && (Character.isDigit(infix.charAt(i + 1))
                || infix.charAt(i + 1) == '.')) {
            temp.append(infix.charAt(++i));
        }
        postfixList.add(temp.toString());
        temp.delete(0, temp.length());
        return i;
    }

    /**
     * It pushes each operator to stack .. while pushing it follows the infix to postfix rules.
     * Check Associative rules, if the precedence of the operators are same.
     * It does push and pop operations based on precedence of stack peek and current input operator.
     * @param input
     */
    private void addOperatorsToStack(char input) {
        if (operatorStack.isEmpty() || input == '(')
            operatorStack.push(input);
        else {
            if (input == ')') {
                while (!operatorStack.peek().equals('(')) {
                    postfixList.add(operatorStack.pop().toString());
                }
                operatorStack.pop();
            } else {
                if (operatorStack.peek().equals('('))
                    operatorStack.push(input);
                else {
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals('(') &&
                            isLeftToRightAssociate(operatorStack.peek(),input)) {
                        postfixList.add(operatorStack.pop().toString());
                    }
                    operatorStack.push(input);
                }
            }
        }
    }

    /**
     * Adds remaining operators to postfix list.
     */
    private void addRemainingOperatorsToPostfix() {
        while (!operatorStack.isEmpty()) {
            postfixList.add(operatorStack.pop().toString());
        }
    }

    /**
     *  checks the precedence if it is left-to-right.
     *  If it is right to left associative returns false.
     * @param a
     * @param b
     * @return
     */
    private boolean isLeftToRightAssociate(char a, char b) {
        if(a == '^' && b=='^')
            return false;
        if(a == '$' && b== '$')
            return false;
        if(a == '@' && b== '@')
            return false;
        int left  = precedence(a);
        int right = precedence(b);
        return left >= right;
    }

    /**
     * It gives the precedence order.
     * @param ch
     * @return
     */
    private int precedence(char ch){
        switch(ch) {
            case'+':
            case'-':
                return 1;
            case'*':
            case'/':
            case '%':
                return 2;
            case'^':
                return 3;
            case '$':
            case '@':
                return 4;
            default:
                return 0;
        }
    }

    public List<String> getPostfixList() {
        return postfixList;
    }

    public void resetData(String infix) {
        this.infix = infix;
        operatorStack.clear();
        postfixList.clear();
    }
}