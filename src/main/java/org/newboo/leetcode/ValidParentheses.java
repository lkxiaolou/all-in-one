package org.newboo.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[' || s.charAt(i) == '(' || s.charAt(i) == '{') {
                stack.push(String.valueOf(s.charAt(i)));
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                String ch2 = stack.pop();

                if ((s.charAt(i) == ']' && '[' != ch2.charAt(0))
                || (s.charAt(i) == ')' && '(' != ch2.charAt(0))
                || (s.charAt(i) == '}' && '{' != ch2.charAt(0))) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

}
