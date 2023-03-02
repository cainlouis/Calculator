package Calculator;

public class ArithmeticCalculator {
    private Stack valStack;
    private Stack opStack;

    public ArithmeticCalculator() {
        this.valStack = new Stack();
        this.opStack = new Stack();
    }

    public String calculate(String equation) {
        String[] tokens = equation.split(" ");
        for (String token : tokens) {
            if (token.equals("(")) {
                opStack.push(token);
            } else if (token.equals(")")) {
                while (!opStack.isEmpty() && !opStack.top().equals("(")) {
                    evaluateOp();
                }
                opStack.pop();
            } else if (isNum(token)) {
                valStack.push(token);
            } else if (isOp(token)) {
                while (!opStack.isEmpty() && precedence(token, opStack.top())) {
                    System.out.println("call");
                    evaluateOp();
                }
                opStack.push(token);
            }
        }
        while (!opStack.isEmpty()) {
            evaluateOp();
        }
        return valStack.pop();
    }

    private void evaluateOp() {
        System.out.println("op " + opStack.top());
        System.out.println("Top " + valStack.top());
        int num2 = Integer.parseInt(valStack.pop());
        int num1 = Integer.parseInt(valStack.pop());
        System.out.println("num1 " + num1);
        System.out.println("num2 " + num2);
        String op = opStack.pop();
        switch (op) {
            case "^":
                valStack.push(Double.toString(Math.pow(num1, num2)));
            case "*":
                valStack.push(Integer.toString(num1 * num2));
                break;
            case "/":
                valStack.push(Integer.toString(num1 / num2));
                break;
            case "+":
                valStack.push(Integer.toString(num1 + num2));
                break;
            case "-":
                valStack.push(Integer.toString(num1 - num2));
                break;
            case ">":
                valStack.push(num1 > num2 ? "True" : "False");
                break;
            case ">=":
                valStack.push(num1 >= num2 ? "True" : "False");
                break;
            case "<=":
                valStack.push(num1 <= num2 ? "True" : "False");
                break;
            case "<":
                valStack.push(num1 < num2 ? "True" : "False");
                break;
            case "==":
                valStack.push(num1 == num2 ? "True" : "False");
                break;
            case "!=":
                valStack.push(num1 != num2 ? "True" : "False");
                break;  
        }
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
        System.out.println("Token " + token);
        int o = assignValueToOp(op);
        return tok >= o;
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
        }
        return 0;
    }
}
