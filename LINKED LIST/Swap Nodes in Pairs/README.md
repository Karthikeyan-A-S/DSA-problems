# `README.md` for LeetCode Problem: Swap Nodes in Pairs

This repository contains a Java solution for LeetCode problem 24: "Swap Nodes in Pairs".

---

## 📝 Problem Description

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,2,3,4]`
**Output:** `[2,1,4,3]`
**Explanation:** The pairs (1,2) and (3,4) are swapped.

### Example 2:

**Input:** `head = []`
**Output:** `[]`

### Example 3:

**Input:** `head = [1]`
**Output:** `[1]`

### Example 4:

**Input:** `head = [1,2,3]`
**Output:** `[2,1,3]`
**Explanation:** The pair (1,2) is swapped. The node `3` remains as it doesn't have a pair.

---

## 🚫 Constraints

* The number of nodes in the list is in the range `[0, 100]`.
* `0 <= Node.val <= 100`

---

## 🧠 Approach

### Algorithm: Iterative Swapping with Dummy Node

This solution iteratively traverses the linked list, swapping adjacent pairs of nodes. A dummy node is used to simplify the handling of the head of the list and the initial linking.

1.  **Initialize:**
    * Create a `dummy` node (named `l` in the code) and set its `next` to the `head` of the input list. This dummy node acts as a placeholder for the beginning of the modified list and simplifies edge cases (like swapping the first pair).
    * Initialize a `current` pointer (named `l` in your code, which is initially the dummy node) to this dummy node. This `current` pointer will always point to the node *before* the pair that is about to be swapped.
    * Initialize `h` to the `dummy` node. This `h` pointer will be used to return the head of the final list.

2.  **Iterative Swapping:**
    * Use a `while` loop that continues as long as there are at least two nodes remaining to be swapped after the `current` pointer. That is, `l.next` (first node of the pair) and `l.next.next` (second node of the pair) must both be non-null.
    * **Identify Nodes:**
        * `firstNode` (conceptually `l.next`): The first node of the current pair.
        * `secondNode` (conceptually `l.next.next`, named `t` in your code): The second node of the current pair.
    * **Perform Swap (Re-linking):**
        * `firstNode.next = secondNode.next;` : The first node (e.g., `1`) now points to the node after the `secondNode` (e.g., `3`).
        * `secondNode.next = firstNode;` : The `secondNode` (e.g., `2`) now points to the `firstNode` (e.g., `1`). This completes the internal swap of the pair.
        * `l.next = secondNode;` : The node *before* the pair (e.g., the dummy node, or `0` in `[0,1,2,3,4]`) now points to the `secondNode` (e.g., `2`), effectively linking the swapped pair into the main list.
    * **Advance `current` Pointer:**
        * `l = t.next;` : Move `l` (which represents `current` in the general case) to the *original* `firstNode` (e.g., `1`). This is because `t` (the `secondNode`) is now at the beginning of the swapped pair, and `t.next` (now the `firstNode`) is the new `current` node *before* the next potential pair. This correctly positions `l` to point before the next pair to be swapped.

3.  **Return Head:** After the loop finishes (when there are fewer than two nodes remaining to swap), return `h.next`, which is the head of the modified list (skipping the dummy node).

### Time Complexity

The time complexity is **O(N)**, where $N$ is the number of nodes in the linked list. This is because we traverse the list exactly once, performing constant-time operations for each pair.

### Space Complexity

The space complexity is **O(1)** as we only use a few constant extra pointers, irrespective of the list's size.

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
    public ListNode swapPairs(ListNode head) {
        ListNode l = new ListNode(0,head);
        ListNode h = l;
        while(l.next !=null && l.next.next !=null){
            ListNode t = l.next.next;
            l.next.next = t.next;
            t.next = l.next;
            l.next=t;
            l=t.next;
        }
        return h.next;
    }
}