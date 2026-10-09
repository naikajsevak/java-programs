import java.util.*;

public class Main {
    public static boolean solve(String s, int ind, long prev) {
        if (ind == s.length()) {
            return true;
        }
        if (s.charAt(ind) == '0') {
            return false;
        }
        boolean ans = false;
        long num = 0;
        for (int i = ind; i < s.length() && !ans; i++) {
            num = 10 * num + (s.charAt(i) - '0');
            if (i!= s.length() - 1 && (prev == -1 || (num - prev) == 1)) {
                ans = ans || solve(s, i + 1, num);
            } else if (num > prev) {
                return false;
            }
        }
        
        return ans;
    }

    public static void separateNumbers(String s) {
        if (solve(s, 0, -1)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        separateNumbers("101103");
    }
}

