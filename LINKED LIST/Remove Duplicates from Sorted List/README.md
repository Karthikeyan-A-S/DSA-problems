# `README.md` for LeetCode Problem: Remove Duplicates from Sorted List

This repository contains a Java solution for LeetCode problem 83: "Remove Duplicates from Sorted List".

---

## 📝 Problem Description

Given the `head` of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,1,2]`
**Output:** `[1,2]`

### Example 2:

**Input:** `head = [1,1,2,3,3]`
**Output:** `[1,2,3]`

---

## 🚫 Constraints

* The number of nodes in the list is in the range `[0, 300]`.
* `-100 <= Node.val <= 100`
* The list is guaranteed to be **sorted** in ascending order.

---

## 🧠 Approach

### Algorithm: Iterative Traversal with Dummy Node

This problem is a simpler version of "Remove Duplicates from Sorted List II". Here, we only need to ensure each unique element appears once, meaning if `1,1,1` exists, it becomes `1`.

1.  **Initialize Pointers:**
    * Create a `dummy` node (named `t` initially) and point its `next` to the `head` of the input list. This dummy node simplifies operations, especially if the original `head` itself is a duplicate.
    * Initialize `th` to `t`. This `th` pointer will store the reference to the dummy node to return the actual head of the modified list.
    * `t` will act as a "current" pointer, and its `next` will be the node being examined.

2.  **Traverse and Remove Duplicates:**
    * The `while (t.next != null)` loop iterates through the list. `t.next` is the node whose duplicates we are currently checking.
    * **`h` as Previous Pointer:**
        * A temporary `h` pointer is set to `t`. `h` always points to the last *unique* node found so far, or the dummy node if no unique nodes have been established yet.
        * `t` is then advanced to `t.next`. Now, `t` points to the current node whose value `v` we are interested in.
    * **Find End of Duplicate Sequence:**
        * An inner `while (t.next != null && t.next.val == v)` loop efficiently skips all consecutive nodes that have the same value as `t.val`.
        * `t` keeps advancing until it reaches a node that is either `null` or has a different value.
    * **Link to Next Unique Node:**
        * After the inner loop, `t` now points to the *last occurrence* of the value `v`.
        * `h.next = t;` : This is the crucial step. It links the `h` node (the last unique node found so far) directly to `t` (the last node of the current unique value `v`). This effectively removes all intermediate duplicate nodes.
    * **Advance `t` for Next Iteration:**
        * The outer loop's `while(t.next != null)` condition naturally handles moving `t` forward to `t.next` in the next iteration. (The `h=null;` line is unnecessary here as `h` is a local variable and its purpose for this iteration is done).

3.  **Return Result:**
    * After the outer loop finishes, `th.next` points to the head of the list with all duplicates removed. Return `th.next`.

### Time Complexity

The time complexity is **O(N)**, where $N$ is the number of nodes in the linked list. Each node is visited at most once by the inner `while` loop (if it's a duplicate) and once by the outer loop's `t` advancement.

### Space Complexity

The space complexity is **O(1)**, as we only use a few constant extra pointers (dummy node, `t`, `h`).

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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode t = new ListNode();
        t.next= head;
        ListNode th= t;
        while(t.next!=null){
            ListNode h = t;
            t=t.next;
            int v = t.val;
            while(t.next !=null && t.next.val == v )
                t =t.next;
            h.next =t;
            h=null;
        }
        th =th.next;
        return th;
    }
}