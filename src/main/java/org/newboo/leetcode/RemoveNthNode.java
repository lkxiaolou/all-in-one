package org.newboo.leetcode;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNode {

    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        ListNode cur = h;
        for (int i = 2; i <= 2; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        removeNthFromEnd(h, 1);
        System.out.println(h);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode h1 = head;
        ListNode h2 = head;
        ListNode pre = null;
        for (int i = 0; i < n - 1; i++) {
            h1 = h1.next;
        }
        while (h1.next != null) {
            pre = h2;
            h1 = h1.next;
            h2 = h2.next;
        }

        if (pre == null) {
            // 删除第一个元素
            head = head.next;
            return head;
        } else {
            pre.next = h2.next;
            return head;
        }
    }

}
