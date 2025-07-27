# `README.md` for LeetCode Problem: Remove Duplicates from Sorted List II

This repository contains a Java solution for LeetCode problem 82: "Remove Duplicates from Sorted List II".

---

## 📝 Problem Description

Given the `head` of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,2,3,3,4,4,5]`
**Output:** `[1,2,5]`

### Example 2:

**Input:** `head = [1,1,1,2,3]`
**Output:** `[2,3]`

---

## 🚫 Constraints

* The number of nodes in the list is in the range `[0, 300]`.
* `-100 <= Node.val <= 100`
* The list is guaranteed to be **sorted** in ascending order.

---

## 🧠 Approach

### Algorithm: Iterative Two-Pointer with Dummy Node

This problem requires removing *all* occurrences of duplicate numbers, not just consecutive ones (though in a sorted list, duplicates will always be consecutive). A common pattern for modifying linked lists, especially at the head, is to use a dummy node.

1.  **Initialize Pointers:**
    * Create a `dummy` node (named `t` initially, and `th` to keep its reference) with `0` as value and pointing to the `head` of the input list. This simplifies handling cases where the original head might be a duplicate and needs to be removed.
    * `t` will serve as the `previous` pointer, pointing to the node *before* the current segment being examined.
    * `th` stores the reference to the `dummy` node, so we can return `th.next` as the final head.

2.  **Traverse and Delete Duplicates:**
    * The `while (t.next != null)` loop iterates through the list. `t.next` is effectively the current node being considered.
    * **Identify Potential Duplicates:**
        * Store `t` in a temporary `h` variable. This `h` is the node *before* `t.next` (the candidate node for processing).
        * Advance `t` to `t.next`. Now `t` points to the current node being examined.
        * Store `t.val` in `v`.
        * Initialize `boolean repeat = false;`.
    * **Check for Duplicates:**
        * An inner `while (t.next != null && t.next.val == v)` loop checks if there are consecutive nodes with the same value (`v`).
        * If duplicates are found, `t` keeps advancing, and `repeat` is set to `true`.
    * **Handle Duplication:**
        * After the inner loop, if `repeat` is `true`, it means `v` was a duplicate. In this case, `h.next` (the node that was before the start of the duplicate sequence) should bypass all nodes with value `v` and point directly to `t.next` (the node *after* the duplicate sequence).
        * Then, `t` is reset to `h` (the node *before* the segment just processed) to re-evaluate the next distinct segment correctly. This is crucial because `h.next` has changed, and we need to process from there.
    * **Advance `t` for Non-Duplicates:**
        * If `repeat` is `false`, it means `t.val` was a unique number in its immediate sequence. In this case, `t` naturally moves forward by the outer loop's `t=t.next` to continue processing. (The `h=null;` line is unnecessary here as `h` is a local variable and will go out of scope or be reassigned in the next iteration).

3.  **Return Result:**
    * After the outer loop completes, `th.next` points to the head of the list containing only distinct numbers. Return `th.next`.

### Time Complexity

The time complexity is **O(N)**, where $N$ is the number of nodes in the linked list. Each node is visited at most twice (once by the outer loop's `t` pointer, and once by the inner loop's `t` pointer if it's a duplicate).

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
        ListNode t = new ListNode(0,head);
        ListNode th= t;
        while(t.next!=null){
            ListNode h = t;
            t=t.next;
            boolean repeat =false;
            int v = t.val;
            while(t.next !=null && t.next.val == v ){
                t =t.next;
                repeat = true;
            }
            if(repeat)
                {h.next =t.next;
                t=h;}
            h=null;
        }
        th =th.next;
        return th;
    }
}