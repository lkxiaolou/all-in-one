package org.newboo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/submissions/
 * 执行用时 :1 ms, 在所有 Java 提交中击败了97.48%的用户
 * 内存消耗 :39.6 MB, 在所有 Java 提交中击败了5.26%的用户
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        // ["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()",/*"(())(())"*/,"(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
        System.out.println(generateParenthesis(4));
    }

    /**
     * f(i+1) = ()f(i) + f(i)() - 1 + (f(i))   f(i)合法
     *                                         f(i)不合法
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate0(n, n, "", res);
        return res;
    }

    /**
     * 深度遍历
     *             (
     *        (         )
     *     (     )   (    )
     *  (    )
     */

    public static void generate0(int left, int right, String cur, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(cur);
            return;
        }
        if (left > 0) {
            generate0(left - 1, right, cur + "(", res);
        }
        if (right > left) {
            generate0(left, right - 1, cur + ")", res);
        }
    }

}
