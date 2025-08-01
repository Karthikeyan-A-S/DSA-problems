# `README.md` for LeetCode Problem: Partition List

This repository contains a Java solution for LeetCode problem 86: "Partition List".

---

## 📝 Problem Description

Given the `head` of a linked list and a value `x`, partition it such that all nodes **less than** `x` come before nodes **greater than or equal to** `x`.

You should **preserve** the original relative order of the nodes in each of the two partitions.

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,4,3,2,5,2], x = 3`
**Output:** `[1,2,2,4,3,5]`

### Example 2:

**Input:** `head = [2,1], x = 2`
**Output:** `[1,2]`

---

## 🚫 Constraints

* The number of nodes in the list is in the range `[0, 200]`.
* `-100 <= Node.val <= 100`
* `-200 <= x <= 200`

---

## 🧠 Approach

### Algorithm: Two Pointers with Two Separate Lists

The key to solving this problem efficiently while preserving the relative order is to create two separate, new linked lists: one for nodes less than `x` and one for nodes greater than or equal to `x`. We then merge these two lists at the end.

1.  **Initialize:**
    * Handle the edge case where the `head` is `null`.
    * Create a `dummy` node for the "before" list (nodes `< x`), named `before`.
    * Create a `dummy` node for the "after" list (nodes `>= x`), named `after`.
    * Initialize `bh` and `ah` to `before` and `after`, respectively. These pointers will hold the heads of the new lists so we can return them later.
    * `before` and `after` will act as a "tail" pointer for each new list, building them node by node.

2.  **Traverse and Partition:**
    * Iterate through the original linked list using the `head` pointer.
    * For each node:
        * If `head.val` is less than `x`, append the current node to the `before` list.
            * `before.next = head;`
            * `before = head;` (move the `before` tail pointer forward).
        * If `head.val` is greater than or equal to `x`, append the current node to the `after` list.
            * `after.next = head;`
            * `after = head;` (move the `after` tail pointer forward).
    * Advance the original `head` pointer: `head = head.next;`.

3.  **Merge the Two Partitions:**
    * After the loop finishes, all nodes from the original list have been moved to either the `before` or `after` list.
    * The `after` list is terminated by setting its tail's `next` to `null`: `after.next = null;`. This is an important step to prevent a cycle.
    * The `before` list is terminated by linking its tail to the head of the `after` list: `before.next = ah.next;`.
    * Note that `ah.next` is the actual head of the `after` partition, as `ah` itself is a dummy node.

4.  **Return Result:**
    * The head of the final partitioned list is `bh.next` (the node after the dummy `before` node). Return `bh.next`.

### Time Complexity

The time complexity is **O(N)**, where $N$ is the number of nodes in the linked list. We traverse the original list exactly once.

### Space Complexity

The space complexity is **O(1)**. We are not creating new nodes, but rather re-linking existing nodes. We use a constant number of extra pointers, regardless of the list's size.

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
    public ListNode partition(ListNode head, int x) {
        if(head == null)
            return null;
       ListNode  before = new ListNode(0);
       ListNode bh=  before;
       ListNode  after =  new ListNode(0);
       ListNode ah=  after;
        while(head != null){
            if(head.val<x){
                before.next = head;
                before = head;
            }
            else{
                after.next = head;
                after = head;
            }
            head = head.next;
        }
        after.next = null;
        before.next = ah.next;
        return bh.next;
    }
    
}