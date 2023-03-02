package Calculator;
public class testing {

    public static void main(String[] args) {
        System.out.println(precedence(">=", "-"));
    }

    private static boolean precedence(String token, String op) {
        int tok = assignValueToOp(token);
        int o = assignValueToOp(op);
        return tok <= o;
    }

    private static int assignValueToOp(String op) {
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
