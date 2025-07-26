# `README.md` for LeetCode Problem: Reverse Nodes in k-Group

This repository contains a Java solution for LeetCode problem 25: "Reverse Nodes in k-Group".

---

## 📝 Problem Description

Given the `head` of a linked list, reverse the nodes of the list `k` at a time, and return the modified list.

`k` is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of `k` then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,2,3,4,5], k = 2`
**Output:** `[2,1,4,3,5]`

### Example 2:

**Input:** `head = [1,2,3,4,5], k = 3`
**Output:** `[3,2,1,4,5]`

---

## 🚫 Constraints

* The number of nodes in the list is `n`.
* `1 <= k <= n <= 5000`
* `0 <= Node.val <= 1000`

---

## 🧠 Approach

### Algorithm: Iterative Group Reversal

The solution involves a combination of iterative traversal and a helper function to reverse a sub-list. The main idea is to identify `k`-sized groups, reverse them, and then connect them back to the main list.

The solution uses two overloaded `reverse` methods:

1.  **`reverse(ListNode h, ListNode e)` (Helper for reversing a segment):**
    This private helper function is responsible for reversing a segment of the linked list.
    * `h`: The node *before* the segment to be reversed (its `next` is the start of the segment).
    * `e`: The node *after* the segment to be reversed (the segment runs from `h.next` up to, but not including, `e`).
    * It performs a standard linked list reversal within the specified bounds.
    * `prev` is initialized to `e` (the end of the segment becomes the new head's next).
    * `current` starts at `h.next` (the beginning of the segment).
    * `next` stores the `current.next` before `current.next` is changed.
    * It iterates until `current` reaches `e`. In each step, `current.next` is set to `prev`, `prev` becomes `current`, and `current` advances to `next`.
    * Crucially, after the reversal, the `next_head` (original `h.next`) becomes the *new end* of the reversed segment. The `h.next` (the node before the segment) is updated to point to the `prev` (the new head of the reversed segment).
    * It returns `next_head`, which is the original start of the segment, now the end of the reversed segment.

2.  **`reverse(ListNode head, int i)` (Main logic to reverse in k-groups):**
    This is the main function that orchestrates the `k`-group reversal. It reuses the `reverseKGroup` name in the provided code structure.
    * A `dummy` node `t` (value 0) is created and points to the `head` of the input list. This simplifies handling the modification of the original head.
    * `traverse` pointer starts at `t`. This pointer moves `k` steps to find the end of each group.
    * `th` pointer also starts at `t`. This pointer marks the node *before* the current `k`-group that needs to be reversed.
    * `count` tracks the number of nodes traversed within a potential `k`-group.
    * The `while(traverse != null)` loop iterates through the list.
    * Inside the loop, `count` is incremented.
    * When `count` reaches `i` (which is `k`), it means a potential `k`-group has been identified (from `th.next` to `traverse.next`).
    * The `reverse(th, traverse.next)` helper is called to reverse this `k`-group. The helper returns the *new tail* of the just-reversed group (which was the original head of that group). This returned node is assigned back to `th`. This correctly positions `th` for the next group.
    * The `traverse` pointer is also reset to `th` to start counting for the next `k`-group from the new tail of the previous group.
    * `count` is reset to 0.
    * If `count` does not reach `k` by the time `traverse` hits `null`, it means the remaining nodes are fewer than `k`, and they are left as is, as per the problem statement.
    * Finally, `t.next` is returned, which is the head of the entirely modified list.

### Time Complexity

The time complexity is **O(N)**, where $N$ is the total number of nodes in the linked list. Each node is visited a constant number of times (once by `traverse` in the main loop, and then a constant number of times during the reversal of its `k`-group).

### Space Complexity

The space complexity is **O(1)**. The solution uses a few constant extra pointers, regardless of the list's size or `k`. No extra data structures that grow with `N` or `k` are used (excluding the recursion stack for helper function calls, which is shallow for individual group reversals).

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
    public ListNode reverse(ListNode h,ListNode e){
        ListNode prev = e;
        ListNode next=e;
        ListNode current = h.next;
        while(current!= e){
            next = current.next;
            current.next = prev;
            prev =current;
            current = next;
        }
        ListNode next_head = h.next;
        h.next =prev;
        return next_head;
    }
    public ListNode reverse(ListNode head,int i){
        ListNode t = new ListNode(0,head);
        ListNode traverse= t;
        ListNode th = t;
        int count=0;
        while(traverse !=null){
            if(count==i){
                th=reverse(th,traverse.next);
                traverse=th;
                count =0;
            }
            traverse =traverse.next;
            count++;
        }
        t=t.next;
        return t;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        return reverse(head,k);
    }
}