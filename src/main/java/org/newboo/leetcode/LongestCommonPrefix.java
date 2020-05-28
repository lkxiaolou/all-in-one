package org.newboo.leetcode;

/**
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        // ["flower","flow","flight"] => "fl"
        String[] strs = new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
        // ["dog","racecar","car"] => ""
        strs = new String[]{"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs));
        // ["aa","a"] => a
        strs = new String[]{"aa","a"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            for (int j = 0; j < Math.min(commonPrefix.length(), strs[i].length()); j++) {
                if (commonPrefix.charAt(j) != strs[i].charAt(j)) {
                    if (j > 0) {
                        commonPrefix = commonPrefix.substring(0, j);
                    } else {
                        return "";
                    }
                }
            }
            if (commonPrefix.length() > strs[i].length()) {
                commonPrefix = strs[i];
            }
        }
        return commonPrefix;
    }
}
