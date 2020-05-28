package org.newboo.leetcode;

import java.util.ArrayList;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        // 121 true
        System.out.println(isPalindrome(121));
        // -121 false
        System.out.println(isPalindrome(-121));
        // 10 false
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(1));
    }

    public static boolean isPalindrome(int x) {
        // 首先负数肯定不是
        if (x < 0) {
            return false;
        }
        ArrayList<Integer> nums = new ArrayList<>(20);
        int yu;
        while (true) {
            yu = x % 10;
            x = x / 10;
            nums.add(yu);
            if (x < 10 && x > 0) {
                nums.add(x);
                break;
            } else if (x == 0) {
                break;
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!nums.get(i).equals(nums.get(nums.size() - i - 1))) {
                return false;
            }
        }
        return true;
    }

}
