package Calculator;

public class ArithmeticCalculator {
    private Stack valStack;
    private Stack opStack;

    public ArithmeticCalculator() {
        this.valStack = new Stack();
        this.opStack = new Stack();
    }

    public String evalExp(String equation) {
        String[] tokens = equation.split(" ");
        for (String token : tokens) {
            if (isNum(token)) {
                valStack.push(token);
            } else {
                repeatOps(token);
                opStack.push(token);
            }
        }
        repeatOps("$");
        String result = valStack.top();
        valStack = new Stack();
        opStack = new Stack();
        return result;
    }

    public void repeatOps(String refOp) {
        while (valStack.size() > 1 && precedence(opStack.top(), refOp)) {
            doOp();
        }
    }

    public void doOp() {
        int num1 = Integer.parseInt(valStack.pop());
        int num2 = Integer.parseInt(valStack.pop());
        String op = opStack.pop();
        valStack.push(evaluateOp(num2, num1, op));
    }

    // public String calculate(String equation) {
    //     String[] tokens = equation.split(" ");
    //     for (String token : tokens) {
    //         if (token.equals("(")) {
    //             opStack.push(token);
    //         } else if (token.equals(")")) {
    //             while (!opStack.isEmpty() && !opStack.top().equals("(")) {
    //                 evaluateOp();
    //             }
    //             opStack.pop();
    //         } else if (isNum(token)) {
    //             valStack.push(token);
    //         } else if (isOp(token)) {
    //             while (!opStack.isEmpty() && precedence(token, opStack.top())) {
    //                 System.out.println("call");
    //                 evaluateOp();
    //             }
    //             opStack.push(token);
    //         }
    //     }
    //     while (!opStack.isEmpty()) {
    //         evaluateOp();
    //     }
    //     return valStack.pop();
    // }

    private String evaluateOp(int num1, int num2, String op) {
        switch (op) {
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
        return op;
    }

    private boolean isNum(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isOp(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    private boolean precedence(String token, String op) {
        int tok = assignValueToOp(token);
        int o = assignValueToOp(op);
        return tok <= o;
    }

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
