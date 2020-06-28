package org.newboo.leetcode;

/**
 * 执行用时：5 ms, 在所有 Java 提交中击败了74.26%的用户
 * 内存消耗：39.4 MB, 在所有 Java 提交中击败了100.00%的用户
 * https://leetcode-cn.com/problems/compress-string-lcci/
 */
public class CompressString {

    public static void main(String[] args) {
        System.out.println(compressString("aabcccccaa"));
        System.out.println(compressString("IIIIeeeeOODDDssppppooQQQQppplllhU"));
    }

    public static String compressString(String S) {
        if (S == null || S.length() <= 1) {
            return S;
        }
        char last = S.charAt(0);
        int lastCount = 0;
        StringBuilder sb = new StringBuilder(S.length());

        for (int i = 0; i < S.length(); i++) {
            if (i == 0) {
                sb.append(S.charAt(0));
                lastCount++;
            } else if (last != S.charAt(i)) {
                last = S.charAt(i);
                sb.append(lastCount);
                sb.append(S.charAt(i));
                lastCount = 1;
            } else {
                lastCount++;
            }
        }
        sb.append(lastCount);

        if (sb.length() >= S.length()) {
            return S;
        }
        return sb.toString();
    }

}
