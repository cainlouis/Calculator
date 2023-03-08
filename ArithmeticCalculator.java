package Calculator;

public class ArithmeticCalculator {
    //Stack for the numbers
    private Stack valStack;
    // Stack for the operators
    private Stack opStack;

    // Initialize the variables as a stack object
    public ArithmeticCalculator() {
        this.valStack = new Stack();
        this.opStack = new Stack();
    }

    //First function that calls all others
    public String evalExp(String equation) {
        //Split the equation by the spaces to get each token of the expression
        String[] tokens = equation.split(" ");
        // For all token in the equation
        for (String token : tokens) {
            // If the token is a open parenthesis
            if (token.equals("(")) {
                // Push it to the stack
                opStack.push(token);
                // If it's a close parenthesis
            } else if (token.equals(")")) {
                // Call repeatOps and remove the last element in the opStack
                repeatOps(token);
                opStack.pop();
                // If opStack is not empty call repeatOps again with the last element in opStack 
                if (!opStack.isEmpty()) {
                    repeatOps(opStack.top());
                }
                //If it's a number push to the valStack
            } else if (isNum(token)) {
                valStack.push(token);
                // Else call repeatOps and push the token to the opStack
            } else {
                repeatOps(token);
                opStack.push(token);
            }
        }
        // Call repeatOps with $ to signify the end of the equation
        repeatOps("$");
        // Reset the stacks for the next equation
        String result = valStack.top();
        valStack = new Stack();
        opStack = new Stack();
        // Return the result of the equation
        return result;
    }

    //Call the doOp which will do the operation for the numbers 
    public void repeatOps(String refOp) {
        // While the valStack has more than 1 element and the precedence is true or the op is the closed parenthesis
        while (valStack.size() > 1 && precedence(opStack.top(), refOp) || refOp.equals(")")) {
            //If the last element of the opStack is the open parenthesis get out of the while loop
            if (opStack.top().equals("(")) {
                break;
            }
            doOp();
        }
    }

    //Do the operation and push the result to the valStack
    public void doOp() {
        //Parse the string with the numbers
        int num1 = Integer.parseInt(valStack.pop());
        int num2 = Integer.parseInt(valStack.pop());
        //Get the last element of the opStack
        String op = opStack.pop();
        //Push the result of the operation to the stack
        valStack.push(evaluateOp(num2, num1, op));
    }

    //Do the calculation for the equation
    private String evaluateOp(int num1, int num2, String op) {
        switch (op) {
            //Calculate the equation
            case "^":
                return Integer.toString((int) Math.pow(num1, num2));
            case "*":
                return Integer.toString(num1 * num2);
            case "/":
                return Integer.toString(num1 / num2);
            case "+":
                return Integer.toString(num1 + num2);
            case "-":
                return Integer.toString(num1 - num2);
            //for the next if the statement return true then return the string "True" else return "False"
            case ">":
                return num1 > num2 ? "True" : "False";
            case ">=":
                return num1 >= num2 ? "True" : "False";
            case "<=":
                return num1 <= num2 ? "True" : "False";
            case "<":
                return num1 < num2 ? "True" : "False";
            case "==":
                return num1 == num2 ? "True" : "False";
            case "!=":
                return num1 != num2 ? "True" : "False";
        }
        //Else return the operator, will be the $ only
        return op;
    }

    //Check if the string is a number
    private boolean isNum(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            //Parse the string and if it throw an exception then return false
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        //Else return true
        return true;
    }

    // Get the precedence of the operator
    private boolean precedence(String token, String op) {
        //Get the number for the precedence
        int tok = assignValueToOp(token);
        int o = assignValueToOp(op);
        // Calculate is the token is of higher precendence and return boolean
        return tok <= o;
    }

    //Give the number to get the precedence of each operator
    private int assignValueToOp(String op) {
        switch (op) {
            case "(":
            case ")":
                return 1;
            case "^":
                return 2;
            case "*":
            case "/":
                return 3;
            case "+":
            case "-":
                return 4;
            case ">":
            case ">=":
            case "<=":
            case "<":
                return 5;
            case "==":
            case "!=":
                return 6;
            case "$":
                return 7;
        }
        return 0;
    }
}
