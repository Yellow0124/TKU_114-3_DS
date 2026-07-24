import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidationSystem {

    public static boolean isValid(String expression) {
        if (expression == null) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (isLeftBracket(ch)) {
                stack.push(ch);
            } else if (isRightBracket(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }

                char topLeft = stack.pop();

                if (!isMatchingPair(topLeft, ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')')
                || (left == '[' && right == ']')
                || (left == '{' && right == '}');
    }

    public static void main(String[] args) {

        String[] testCases = {
                "([]{})",
                "if (a[0] == {v: 1}) { return; }",
                "([)]",
                ")(",
                "((()",
                "System.out.println(\"Hello\");",
                "]",
                "{[("
        };

        for (int i = 0; i < testCases.length; i++) {
            String expr = testCases[i];
            boolean result = isValid(expr);
            System.out.printf("測試 %d: %-35s -> 結果: %s\n",
                    (i + 1),
                    "\"" + expr + "\"",
                    (result ? "驗證通過 (Valid)" : "語法錯誤 (Invalid)"));
        }
    }
}