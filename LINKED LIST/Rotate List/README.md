# `README.md` for LeetCode Problem: Rotate List

This repository contains a Java solution for LeetCode problem 61: "Rotate List".

---

## 📝 Problem Description

Given the `head` of a linked list, rotate the list to the right by `k` places.

---

## 💡 Examples

### Example 1:

**Input:** `head = [1,2,3,4,5], k = 2`
**Output:** `[4,5,1,2,3]`
**Explanation:**
Rotate 1 step: `[5,1,2,3,4]`
Rotate 2 steps: `[4,5,1,2,3]`

### Example 2:

**Input:** `head = [0,1,2], k = 4`
**Output:** `[2,0,1]`
**Explanation:**
List is `[0,1,2]`
k = 4, length = 3.
Effective rotations `k % length = 4 % 3 = 1`.
Rotate 1 step: `[2,0,1]`

---

## 🚫 Constraints

* The number of nodes in the list is in the range `[0, 500]`.
* `-100 <= Node.val <= 100`
* `0 <= k <= 2 * 10^9`

---

## 🧠 Approach

### Algorithm: Two Pointers with Modulo Operation

The problem asks us to rotate a linked list to the right by `k` places. The key observations are:
1.  The rotation effectively moves the last `k` nodes (or `k % length` nodes) to the front.
2.  If `k` is greater than or equal to the list's length, the actual number of rotations needed is `k % length`.

The solution uses a two-pointer approach along with calculating the list's size.

1.  **Handle Edge Cases:**
    * If `k` is 0, or the list is empty (`head == null`), or the list has only one node (`head.next == null`), no rotation is needed. Return `head` directly.

2.  **Initialize Pointers and Count Size:**
    * Create a dummy node `t` (named `t` initially, then later used for traversal) and point its `next` to `head`.
    * Initialize `th` to `t` (this will remain pointing to the original dummy node).
    * Initialize `kh` to `t` (this pointer will help find the new tail of the rotated list).
    * `size` is initialized to `0` to count the number of nodes.

3.  **Traverse and Find New Tail/Head (Simultaneously):**
    * The `while` loop condition `k != 0 || t.next != null` ensures traversal continues until `k` becomes 0 and `t` reaches the end of the list.
    * In each iteration:
        * Advance `t` to `t.next` and increment `size`. `t` eventually reaches the end of the list.
        * If `k` is still greater than 0, decrement `k`. This uses `k` steps to establish the initial gap.
        * Once `k` becomes 0 *and* `t.next` is not null, it means `kh` has a head start of `k` nodes from `t`. So, `kh` should start moving forward to maintain the `k` distance. `kh = kh.next;`
        * **Handle `k` exceeding list length:** If `k` is still not 0 (meaning the initial `k` was larger than `size`) AND `t.next` becomes `null` (meaning `t` has reached the end of the original list), we calculate `k = k % size` to get the effective number of rotations. If this new `k` is not 0, `t` is reset to `th` (the dummy node) to start a new traversal for the effective `k` rotations.

4.  **Perform Rotation:**
    * After the loop, `t` will be pointing to the original last node of the list.
    * `kh` will be pointing to the node *before* the new head of the rotated list. The node `kh.next` is the new tail.
    * **Edge Case: No actual rotation needed after modulo:** If `kh == th`, it means `k % size` resulted in 0, so no rotation is actually needed, and the original list head (`th.next`) is returned.
    * Connect the original end of the list (`t`) to the original head (`th.next`). This forms a circle.
    * The new head of the list is `kh.next`. Store it in `th` temporarily (overloading the `th` variable for convenience).
    * Break the circle by setting `kh.next = null`. `kh.next` was the node that would become the new tail.
    * Return the new head (`th`).

### Time Complexity

The time complexity is **O(L)**, where $L$ is the length of the linked list. We traverse the list at most twice (once to find the length and first `k` nodes, and potentially a second time if `k` needs to be recalculated with modulo). The modulo operation takes constant time.

### Space Complexity

The space complexity is **O(1)**, as we only use a few constant extra pointers.

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
    public ListNode rotateRight(ListNode head, int k) {
        if(k==0 || head ==null || head.next == null)
            return head;
        ListNode t = new ListNode(0,head);
        ListNode th = t;
        ListNode kh = t;
        int size=0;
        while(k!=0 || t.next !=null){
            t = t.next;
            size++;
            if(k!=0)
                k--;
            if(k ==0 && t.next !=null)
                kh =kh.next;
            if(k!=0 && t.next ==null){
                k = k % size;
                if(k !=0)
                    t = th;
            }
        }
        if(kh == th)
            return th.next;
        t.next = th.next;
        th = kh.next;
        kh.next = null;
        return th;
    }
}