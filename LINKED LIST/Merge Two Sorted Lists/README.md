# `README.md` for LeetCode Problem: Merge Two Sorted Lists

This repository contains a Java solution for LeetCode problem 21: "Merge Two Sorted Lists".

---

## 📝 Problem Description

You are given the heads of two sorted linked lists `list1` and `list2`.
Merge the two lists into one **sorted** list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

---

## 💡 Examples

### Example 1:

**Input:** `list1 = [1,2,4]`, `list2 = [1,3,4]`
**Output:** `[1,1,2,3,4,4]`

### Example 2:

**Input:** `list1 = []`, `list2 = []`
**Output:** `[]`

### Example 3:

**Input:** `list1 = []`, `list2 = [0]`
**Output:** `[0]`

---

## 🚫 Constraints

* The number of nodes in both lists is in the range `[0, 50]`.
* `-100 <= Node.val <= 100`
* Both `list1` and `list2` are sorted in **non-decreasing** order.

---

## 🧠 Approach

### Algorithm

The problem can be solved by iteratively comparing the nodes of the two input lists and building a new merged list.

1.  **Initialize:** Create a dummy head node (named `l` in the provided code) for the merged list. Also, initialize a `head` pointer to this dummy node. This `head` pointer will ultimately be used to return the merged list's actual head.
2.  **Iterative Merging:** Use a `while` loop that continues as long as both `list1` and `list2` have nodes.
    * **Compare Values:** Inside the loop, compare the `val` of the current nodes of `list1` and `list2`.
    * **Append Smaller Node:**
        * If `list1.val` is less than or equal to `list2.val`, append `list1`'s current node to the `next` of the current `l` pointer, and then advance `list1` to its next node.
        * Otherwise (if `list2.val` is smaller), append `list2`'s current node to the `next` of the current `l` pointer, and then advance `list2` to its next node.
    * **Advance `l`:** In either case, move the `l` pointer to the newly appended node. This ensures `l` always points to the last node added to the merged list.
3.  **Append Remaining Nodes:** After the loop, one of the lists (or both, if they ended at the same time) might still have remaining nodes. Since both original lists were sorted, any remaining nodes in either `list1` or `list2` are already sorted. Append the remaining portion of whichever list is not null to the end of the merged list.
4.  **Return Head:** Finally, return `head.next`. This skips the initial dummy node and gives the actual head of the sorted merged list.

### Time Complexity

The time complexity is **O(m + n)**, where $m$ and $n$ are the number of nodes in `list1` and `list2` respectively. This is because we visit each node in both lists at most once.

### Space Complexity

The space complexity is **O(1)** if we are modifying the original list nodes (splicing them). We only use a few extra pointers (dummy node, `l`, `head`), regardless of the input list sizes. If new nodes were created, it would be O(m+n). In this solution, we are re-pointing existing nodes.

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l = new ListNode();
        ListNode head =l;
        while(list1 != null && list2 !=null){
            if(list1.val <= list2.val)
            {   l.next = list1;
                list1 = list1.next;
            }
            else{
                l.next = list2;
                list2 = list2.next;
            }
            l= l.next;
        }
        if(list1 !=null)
            l.next = list1;
        if(list2 !=null)
            l.next =list2;
        return head.next;
    }
}