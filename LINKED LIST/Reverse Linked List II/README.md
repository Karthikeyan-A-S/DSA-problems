# `README.md` for LeetCode Problem: Reverse Linked List II

This repository contains a Java solution for LeetCode problem 92: "Reverse Linked List II".

---

## 📝 Problem Description

Given the `head` of a singly linked list and two integers `left` and `right` where `left <= right`, reverse the nodes of the list from position `left` to position `right`, and return the reversed list.

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,2,3,4,5], left = 2, right = 4`
**Output:** `[1,4,3,2,5]`
**Explanation:** The sublist from position 2 (`2`) to 4 (`4`) is reversed.

### Example 2:

**Input:** `head = [5], left = 1, right = 1`
**Output:** `[5]`

---

## 🚫 Constraints

* The number of nodes in the list is `n`.
* `1 <= n <= 500`
* `-500 <= Node.val <= 500`
* `1 <= left <= right <= n`

---

## 🧠 Approach

### Algorithm: Iterative Reversal with Pointers

This solution uses an iterative approach to reverse a specified segment of the linked list. It's an extension of the standard linked list reversal algorithm, carefully managing pointers to isolate, reverse, and re-connect the sub-list.

1.  **Handle Edge Cases:**
    * If `left` and `right` are the same, or the list is empty, no reversal is needed, so the original `head` is returned.

2.  **Initialize Pointers:**
    * A `dummy` node is created, pointing to the original `head`. This simplifies handling the case where the reversal starts at the very first node (`left = 1`).
    * We need a pointer to the node *before* the `left` segment. Let's call this `before`. The code uses `dummyhead` for this purpose. It's initially set to `dummy`.
    * We also need a pointer to the *first node* of the segment to be reversed. This is the `left`-th node.
    * A `before` pointer is used in the main loop to facilitate the reversal.

3.  **Traverse to the Start of Reversal:**
    * The main `while` loop iterates from `count = 1` up to `right`.
    * The code finds the node at `left-1` and stores it in `dummyhead`. This `dummyhead` will be used to re-link the list after the reversal.

4.  **Reverse the Sub-list:**
    * Inside the main loop, for nodes from `left+1` to `right`, the pointers are reversed one by one.
    * The `head` pointer moves forward, and its `next` pointer is updated to point to the `before` node.
    * The `before` pointer is also updated in each step to be the `head` of the previous iteration. This effectively reverses the links within the `[left, right]` segment.

5.  **Re-connect the List:**
    * After the loop, the pointers `dummyhead`, `before`, and `head` are in key positions:
        * `dummyhead`: The node *before* the reversed segment. Its `next` should become the new head of the reversed segment.
        * `before`: The original `right` node, now the new head of the reversed segment.
        * `head`: The node *after* the original `right` node.
        * `dummyhead.next`: The original `left` node, now the new tail of the reversed segment.
    * We perform two crucial re-linking steps:
        * `dummyhead.next.next = head;`: The original `left` node (now the tail of the reversed segment) is linked to the node *after* the `right` segment.
        * `dummyhead.next = before;`: The node *before* the `left` segment is linked to the new head of the reversed segment (the original `right` node).

6.  **Return Head:**
    * The head of the final modified list is `dummy.next`.

### Time Complexity

The time complexity is **O(N)**, where $N$ is the number of nodes in the linked list. This is because we traverse the list exactly once.

### Space Complexity

The space complexity is **O(1)**, as we only use a few extra pointers for traversal and re-linking, irrespective of the list's size.

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left ==right || head == null)
            return head;
        ListNode dummy =new ListNode(0,head);
        ListNode dummyhead =dummy;
        ListNode before =dummy;
        
        int count=1;
        while(count <= right){
            ListNode next = head.next;
            if(count == left-1){
                dummyhead =head;
            }
            else if (count >left && count <= right){
                head.next = before;
            }
            before = head;
            head =next;
            count++;
        }
        dummyhead.next.next = head;
        dummyhead.next = before;
        return dummy.next;
    }
}