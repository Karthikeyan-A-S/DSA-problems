# `README.md` for LeetCode Problem: Add Two Numbers

This repository contains a Java solution for LeetCode problem 2: "Add Two Numbers".

---

## 📝 Problem Description

You're given two **non-empty** linked lists, each representing a non-negative integer. The digits are stored in **reverse order**, with each node containing a single digit. Your task is to add these two numbers and return their sum as a new linked list.

You can assume that the numbers won't have any leading zeros, except for the number 0 itself.

---

## 💡 Examples

### Example 1:

**Input:** `l1 = [2,4,3]`, `l2 = [5,6,4]`
**Output:** `[7,0,8]`
**Explanation:** This represents the addition `342 + 465 = 807`.

### Example 2:

**Input:** `l1 = [0]`, `l2 = [0]`
**Output:** `[0]`

### Example 3:

**Input:** `l1 = [9,9,9,9,9,9,9]`, `l2 = [9,9,9,9]`
**Output:** `[8,9,9,9,0,0,0,1]`

---

## 🚫 Constraints

* The number of nodes in each linked list will be between 1 and 100, inclusive.
* Each node's value (`Node.val`) will be between 0 and 9, inclusive.
* It's guaranteed that the lists represent numbers without leading zeros (except for the number 0 itself).

---

## 🧠 Approach

### Algorithm

The problem can be solved by iterating through both linked lists simultaneously, simulating manual addition. We'll maintain a `carry` variable to handle cases where the sum of digits exceeds 9.

1.  **Initialize:** Create a dummy head node (`l` in the provided code) for the result list to simplify handling the first node. Also, initialize a `dummy` pointer to this dummy node and a `carry` variable to `0`.
2.  **Iterate and Sum:** Loop as long as either `l1` or `l2` has remaining nodes, or if there's a `carry` of `1`.
    * Initialize `sum` with the current `carry`.
    * If `l1` is not null, add `l1.val` to `sum` and advance `l1` to its next node.
    * If `l2` is not null, add `l2.val` to `sum` and advance `l2` to its next node.
    * Update the `carry` to `sum / 10`.
    * Create a new node with the value `sum % 10` (the unit digit of the sum) and append it to the `dummy` node of the result list.
    * Move `dummy` to the newly added node.
3.  **Return Result:** The result list starts from `l.next` since `l` was just a placeholder.

### Time Complexity

The time complexity is **O(max(m, n))**, where $m$ and $n$ are the number of nodes in `l1` and `l2` respectively. This is because we traverse each linked list at most once.

### Space Complexity

The space complexity is **O(max(m, n))** because, in the worst case, the length of the new linked list can be `max(m, n) + 1` (if there's a final carry). We create a new node for each digit in the sum.

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l  = new ListNode(0);
        ListNode dummy = l;
        int carry=0;
        int sum ;
        while(l1 != null || l2 !=null || carry == 1){
            sum=carry;
            if(l1 !=null){
                sum +=l1.val;
                l1  = l1.next;
            }
            if(l2 !=null){
                sum += l2.val;
                l2  = l2.next;
            }
            carry = sum/10;
            dummy.next = new ListNode(sum%10,null);
            dummy = dummy.next;
        }
        return l.next;
    }
}