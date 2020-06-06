package org.newboo.leetcode;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 * 执行用时 :3 ms, 在所有 Java 提交中击败了61.83%的用户
 * 内存消耗 :41 MB, 在所有 Java 提交中击败了100.00%的用户
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(findMedianSortedArrays(nums3, nums4));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int index = 0;
        boolean two = false;
        if ((nums1.length + nums2.length) % 2 == 1) {
            index = (nums1.length + nums2.length) / 2 + 1;
        } else {
            index = (nums1.length + nums2.length) / 2;
            two = true;
        }
        int i = 0;
        int j = 0;
        int cur = 0;
        int a;
        int b = 0;
        while (true) {

            if (i >= nums1.length) {
                a = nums2[j];
                j++;
            } else if (j >= nums2.length) {
                a = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                a = nums1[i];
                i++;
            } else {
                a = nums2[j];
                j++;
            }
            cur++;
            if (cur == index && !two) {
                return a;
            } else if (cur == index && two) {
                b = a;
            } else if ((cur == index + 1) && two) {
                return (a + b) / 2.0;
            }
        }
    }
}
