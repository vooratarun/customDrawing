package voora.com.customcalc.calc;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tarun on 12/6/17.
 */


public class PostFixCalculator {
    private List<String> expressionList = new ArrayList<String>();
    private Stack<Double> operandStack = new Stack<>();

    public PostFixCalculator(List<String> expressionList) {
        this.expressionList = expressionList;
    }

    private Double popStack() throws Exception {
        if(operandStack.isEmpty())
            throw new Exception();
        else
            return operandStack.pop();

    }

    /**
     * Takes the Postfix Expression list and evaluates item by item.
     * use the OperandStack for subResult.
     * The output will be single Value in OperandStack i.e. is peek item or pop.
     * @return
     * @throws Exception inCase error in math operation.
     */
    public String calculate() throws Exception {
        for (int i = 0; i != expressionList.size(); ++i) {

            if ( Character.isDigit(expressionList.get(i).charAt(0)) ) {
                operandStack.push(Double.parseDouble(expressionList.get(i)));
            } else {
                double subResult = 0;
                switch (expressionList.get(i)) {
                    case "+": {
                        double a = popStack();
                        double b = popStack();
                        subResult = b + a;
                        operandStack.push(subResult);
                        break;
                    }

                    case "-": {
                        double a = popStack();
                        double b = popStack();
                        subResult = b - a;
                        operandStack.push(subResult);
                        break;
                    }

                    case "*": {
                        double a = popStack();
                        double b = popStack();
                        subResult = b * a;
                        operandStack.push(subResult);
                        break;
                    }

                    case "/": {
                        double a = popStack();
                        double b = popStack();
                        subResult = b / a;
                        operandStack.push(subResult);

                        break;
                    }

                    case "^": {
                        double a = popStack();
                        double b = popStack();
                        subResult = Math.pow(b, a);
                        operandStack.push(subResult);
                        break;
                    }

                    case "%": {
                        double a = popStack();
                        double b = popStack();
                        subResult = b % a;
                        operandStack.push(subResult);
                        break;
                    }

                    case "$": {
                        double a = popStack();
                        operandStack.push(-1 * a);
                        break;
                    }

                    case "@": {
                        double a = popStack();
                        operandStack.push(1 * a);
                        break;
                    }

                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(operandStack.pop());
    }

    public void resetData(List<String> expressionList) {
        operandStack.clear();
        this.expressionList = expressionList;
    }

}