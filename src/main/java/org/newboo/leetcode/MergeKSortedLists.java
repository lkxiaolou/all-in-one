package org.newboo.leetcode;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 执行用时 :301 ms, 在所有 Java 提交中击败了11.81%的用户
 * 内存消耗 :41.9 MB, 在所有 Java 提交中击败了48.53%的用户
 */
public class MergeKSortedLists {

     static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
     }

    public ListNode mergeKLists(ListNode[] lists) {
         if (lists == null || lists.length == 0) {
             return null;
         }
         if (lists.length == 1) {
            return lists[0];
         }
         ListNode head = null;
         ListNode cur = null;
         while (true) {
             int minIndex = 0;
             int min = Integer.MAX_VALUE;
             int breakCount = 0;
             for (int i = 0; i < lists.length; i++) {
                 if (lists[i] != null) {
                     if (lists[i].val < min) {
                         minIndex = i;
                         min = lists[i].val;
                     }
                 } else {
                     breakCount++;
                 }
             }
             if (breakCount >= lists.length) {
                 break;
             }
             if (head == null) {
                 cur = head = lists[minIndex];
             } else {
                 cur.next = lists[minIndex];
                 cur = cur.next;
             }
             // 删掉第一个节点
             lists[minIndex] = lists[minIndex].next;
         }
         return head;
    }

}
