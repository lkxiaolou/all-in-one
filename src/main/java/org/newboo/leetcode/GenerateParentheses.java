package org.newboo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        // ["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()",/*"(())(())"*/,"(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
        // [()()()(), (()())(), (()(())), ()()(()), (())()(), (((()))), ()((())), ()(())(), ()(()()), (()()()), ((()())), ((()))(), ((())())]
        System.out.println(generateParenthesis(4));
    }

    /**
     * f(i+1) = ()f(i) + f(i)() - 1 + (f(i))   f(i)合法
     *                                         f(i)不合法
     */
    public static List<String> generateParenthesis(int n) {
        Set<String> set = null;
        for (int i = 1; i <= n; i++) {
            set = generate0(i, set);
        }
        return new ArrayList<>(set);
    }

    public static Set<String> generate0(int n, Set<String> ls) {
        if (n == 1) {
            Set<String> l = new HashSet<>(1);
            l.add("()");
            return l;
        } else {
            Set<String> l1 = new HashSet<>(ls.size() * 3 - 1);
            for (String s : ls) {
                l1.add("()" + s);
                l1.add(s + "()");
                l1.add("(" + s + ")");
            }

            return l1;
        }
    }

}
