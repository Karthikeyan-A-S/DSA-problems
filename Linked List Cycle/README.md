# 141. Linked List Cycle

**Difficulty:** Easy  
**Topics:** Linked List, Two Pointers

---

## Problem Statement

Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer.

Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to. **Note that `pos` is not passed as a parameter.**

Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

---

## Examples

### Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

shell
Copy
Edit

### Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

shell
Copy
Edit

### Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

markdown
Copy
Edit

---

## Constraints

- The number of nodes in the list is in the range `[0, 10⁴]`.
- `-10⁵ <= Node.val <= 10⁵`
- `pos` is `-1` or a valid index in the linked-list.

---

## Approach

We use **Floyd’s Cycle Detection Algorithm**, also known as the **Tortoise and Hare Algorithm**.

### Idea:
- Use two pointers: `turtle` (moves one step) and `rabbit` (moves two steps).
- If the list has a cycle, they will eventually meet.
- If there's no cycle, the fast pointer will reach the end (`null`) first.

### Time Complexity:
- `O(n)` where `n` is the number of nodes.

### Space Complexity:
- `O(1)` — constant space.
