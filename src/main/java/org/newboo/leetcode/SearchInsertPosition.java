package org.newboo.leetcode;

/**
 * https://leetcode-cn.com/problems/search-insert-position/submissions/
 * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 :39.4 MB, 在所有 Java 提交中击败了5.55%的用户
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 6};
        System.out.println(searchInsert(nums1, 5));
        System.out.println(searchInsert(nums1, 2));
        System.out.println(searchInsert(nums1, 7));
        System.out.println(searchInsert(nums1, 0));
    }

    public static int searchInsert(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (true) {
            if (nums[startIndex] == target) {
                return startIndex;
            } else if (nums[endIndex] == target) {
                return endIndex;
            }
            if (endIndex - startIndex <= 1) {
                if (nums[startIndex] > target) {
                    return Math.max(0, startIndex - 1);
                } else if (nums[endIndex] < target) {
                    return endIndex + 1;
                } else {
                    return startIndex + 1;
                }
            }

            int midIndex = (endIndex - startIndex) / 2 + startIndex;
            if (nums[midIndex]  == target) {
                return midIndex;
            } else if (nums[midIndex]  < target) {
                startIndex = midIndex;
            } else if (nums[midIndex] > target) {
                endIndex = midIndex;
            }
        }
    }

}
