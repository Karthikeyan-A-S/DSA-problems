# `README.md` for LeetCode Problem: Remove Nth Node From End of List

This repository contains a Java solution for LeetCode problem 19: "Remove Nth Node From End of List".

---

## 📝 Problem Description

Given the `head` of a linked list, remove the `nth` node from the end of the list and return its head.

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,2,3,4,5], n = 2`
**Output:** `[1,2,3,5]`
**Explanation:** The 2nd node from the end is `4`. Removing it results in `[1,2,3,5]`.

### Example 2:

**Input:** `head = [1], n = 1`
**Output:** `[]`
**Explanation:** The 1st node from the end is `1`. Removing it results in an empty list.

### Example 3:

**Input:** `head = [1,2], n = 1`
**Output:** `[1]`
**Explanation:** The 1st node from the end is `2`. Removing it results in `[1]`.

---

## 🚫 Constraints

* The number of nodes in the list is `sz`.
* `1 <= sz <= 30`
* `0 <= Node.val <= 100`
* `1 <= n <= sz`

---

## 🧠 Approach

### Algorithm

The problem can be efficiently solved using the **two-pointer approach**. We'll maintain two pointers, `traverser` (fast pointer) and `pre` (slow pointer, actually pointing to the node *before* the one to be removed).

1.  **Initialize Pointers:**
    * Create a `dummy` node (named `h` in the provided code) and point its `next` to the `head` of the list. This handles edge cases like removing the head node.
    * Initialize `traverser` to `head`.
    * Initialize `pre` to the `dummy` node (`h`).
2.  **Advance `traverser`:** Move `traverser` `n` steps forward from the beginning of the list. This creates a gap of `n` nodes between `traverser` and `pre`.
3.  **Simultaneous Traversal:** Move both `traverser` and `pre` one step at a time until `traverser` reaches the end of the list (`null`).
    * When `traverser` is `null`, `pre` will be pointing to the node *just before* the `nth` node from the end.
4.  **Remove Node:** Update `pre.next` to `pre.next.next`, effectively bypassing and removing the `nth` node from the end.
5.  **Return Head:** Return `h.next`, which is the head of the modified list.

### Time Complexity

The time complexity is **O(L)**, where $L$ is the length of the linked list. This is because both pointers traverse the list at most once.

### Space Complexity

The space complexity is **O(1)** as we only use a few extra pointers, irrespective of the list's size.

---

## 💻 Complete Java Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode traverser = head;
        ListNode pre = new ListNode(0,head);
        ListNode h = pre;
        int count =0;
        while(traverser !=null){
            count++;
            if(count > n)
                pre = pre.next;
            traverser = traverser.next;
        }
        pre.next = pre.next.next;
        return h.next;
    }
}