package org.newboo.leetcode;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 * 执行用时 :4 ms, 在所有 Java 提交中击败了72.66%的用户
 * 内存消耗 :40.3 MB, 在所有 Java 提交中击败了7.14%的用户
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] heights1 = {1, 1};
        int[] heights2 = {2, 3, 4, 5, 18, 17, 6};
        System.out.println(maxArea(heights));
        System.out.println(maxArea(heights1));
        System.out.println(maxArea(heights2));
    }

    /**
     * S = x * y
     * （1）初始以最长x开始， S = max(x) * min(y1, y2)
     * （2）y往内（y1向右、y2向左）移动一格，如果移动的y较大，则 S1 < S，所以必须移动较小的y
     * （3）计算所有S，求出max(S)
     */
    public static int maxArea(int[] height) {
        int y1 = 0;
        int y2 = height.length - 1;
        int max = Integer.MIN_VALUE;

        while (true) {
            int s = (y2 - y1) * Math.min(height[y1], height[y2]);
            if (s > max) {
                max = s;
            }
            if (y2 - y1 <= 1) {
                break;
            }
            if (height[y1] > height[y2]) {
                y2 = y2 -1;
            } else {
                y1 = y1 + 1;
            }
        }
        return max;
    }

}
