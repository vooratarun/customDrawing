package voora.com.customcalc.calc;

/**
 * Created by tarun on 12/6/17.
 */

public class PreProcessor {

    private static final String OPERATOR_LIST = "+-*/^%";
    private static final String NON_UNARY_OPERATORS = "*/^%";
    public static final String ERROR = "SYNTAX ERROR";

    public PreProcessor() {

    }

    /**
     * it adds 0 infrontof all the '.' points , if user entered without zero.
     * TODO NEED IMPROVEMENTS.
     * @param infix
     * @return
     */
    public String handleFloatPoints(String infix) {
        StringBuilder temp = new StringBuilder();

        for(int i = 0;i <infix.length();i++) {
            char ch = infix.charAt(i);
            if(ch == '.') {
                char prevChar = infix.charAt(i-1);
                if(OPERATOR_LIST.indexOf(prevChar) > -1 )
                    temp.append("0").append(".");
                else
                    temp.append("0").append(ch);
            } else  {
                temp.append(ch);
            }
        }

        return temp.toString();
    }


    /**
     * Take the input String and process and validate the String.
     * Handle First Character, if it is non-unary operators .. then syntax Error.
     * Handle Unary Operators (+ or - unary operators will be converted to @,$ respectively.)
     * TODO need to be improved.
     * @param infix
     * @return
     */
    public String preProcessString(String infix) {

        StringBuilder output = new StringBuilder();


        if(NON_UNARY_OPERATORS.indexOf(infix.charAt(0)) > -1) {
            return ERROR;
        }

        infix = "0" + infix;
        infix = handleFloatPoints(infix);

        for(int i = 0;i < infix.length();i++) {
            char ch = infix.charAt(i);
            if(Character.isDigit(ch)) {                                     // Its an operand
                output.append(ch);
            } else {

                if(i-1 < 0) continue;

                char prevChar = infix.charAt(i-1);

                if (OPERATOR_LIST.indexOf(prevChar) > -1) {                 // PrevChar might be normal operator _,-.*,/,%,^
                                                                            // ch should be either + or - .
                    if (ch == '+') {
                        output.append('@');                                 // Positive unary operator is converted to @
                    } else if (ch == '-') {
                        output.append('$');                                 // Negative unary operator is converted to $

                    } else if (NON_UNARY_OPERATORS.indexOf(ch) > -1) {
                        return ERROR;
                    }

                } else {
                    output.append(ch);                                      // this is normal operator might be +,-,/,*, % ...
                }
            }
        }

        return output.toString();
    }

}
