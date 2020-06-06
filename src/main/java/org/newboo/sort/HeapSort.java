package org.newboo.sort;

public class HeapSort {

    public static void main(String[] args) {
        // 2 4 1 3 2 9 6 7
        int[] nums = {2, 4, 1, 3, 2, 9, 6, 7};
        /**
         *           0            2
         *          1 2          4 1
         *         3 4 5 6     3 2 9 6
         */
        // 首次构建 >= 2
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            nums = adjust(nums, nums.length, i);
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
        System.out.println("");
        // 将最后一个和第一个交互
        for (int i = nums.length - 1; i >= 1; i--) {
            int tmp = nums[i];
            nums[i] = nums[0];
            nums[0] = tmp;
            nums = adjust(nums, i, 0);
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
    }

    /**
     * 调整第i个节点
     */
    private static int[] adjust(int[] nums, int length, int i) {
        // 左节点
        int left = 2 * i + 1;
        int right = left + 1;

        // 存在右节点且右节点大于根节点
        if (right < length && nums[right] > nums[i]) {
            int tmp = nums[i];
            nums[i] = nums[right];
            nums[right] = tmp;
        }

        // 存在左节点且左节点大于根节点
        if (left < length && nums[left] > nums[i]) {
            int tmp = nums[i];
            nums[i] = nums[left];
            nums[left] = tmp;
        }

        // 左节点
        if (left < length) {
            nums = adjust(nums, length, left);
        }
        // 右节点
        if (right < length) {
            nums = adjust(nums, length, right);
        }

        return nums;
    }

}
