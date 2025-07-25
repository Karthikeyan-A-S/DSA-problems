# `README.md` for LeetCode Problem: Merge k Sorted Lists

This repository contains a Java solution for LeetCode problem 23: "Merge k Sorted Lists".

---

## 📝 Problem Description

You are given an array of `k` linked-lists `lists`, where each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

---

## 💡 Examples

### Example 1:

**Input:** `lists = [[1,4,5],[1,3,4],[2,6]]`
**Output:** `[1,1,2,3,4,4,5,6]`
**Explanation:** The linked-lists are:
[
1->4->5,
1->3->4,
2->6
]

merging them into one sorted linked list:
`1->1->2->3->4->4->5->6`

### Example 2:

**Input:** `lists = []`
**Output:** `[]`

### Example 3:

**Input:** `lists = [[]]`
**Output:** `[]`

---

## 🚫 Constraints

* `k == lists.length`
* `0 <= k <= 10^4`
* `0 <= lists[i].length <= 500`
* `-10^4 <= lists[i][j] <= 10^4`
* `lists[i]` is sorted in ascending order.
* The sum of `lists[i].length` will not exceed `10^4`.

---

## 🧠 Approach

### Algorithm: Divide and Conquer (Merge Sort Style)

This solution employs a **Divide and Conquer** strategy, similar to Merge Sort, to efficiently merge `k` sorted linked lists. The core idea is to recursively merge pairs of lists until only one sorted list remains.

The approach breaks down as follows:

1.  **Base Cases for `mergeSort`:**
    * If the range `[l, r]` is invalid (`l > r`), it means there are no lists to merge in this segment, so return `null`.
    * If the range contains only one list (`l == r`), simply return that list (`lists[l]`). This is the base case for the recursion.

2.  **Recursive Step for `mergeSort`:**
    * Calculate the middle index `m = l + (r - l) / 2`.
    * Recursively call `mergeSort` on the left half of the array of lists: `mergeSort(lists, l, m)`. This will return a single merged and sorted linked list for the left segment.
    * Recursively call `mergeSort` on the right half of the array of lists: `mergeSort(lists, m + 1, r)`. This will return a single merged and sorted linked list for the right segment.
    * Once you have the two sorted lists (one from the left half and one from the right half), use a helper function (`mergeTwoLists`) to merge these two lists into a single sorted list.

3.  **Helper Function `mergeTwoLists`:**
    * This function takes two sorted linked lists (`list1`, `list2`) as input.
    * It creates a dummy head node (`l`) and a `head` pointer to `l`.
    * It iteratively compares the current nodes of `list1` and `list2`. The node with the smaller value is appended to the merged list, and its respective pointer is advanced.
    * This process continues until one of the lists becomes empty.
    * Finally, any remaining nodes from the non-empty list are appended to the merged list.
    * The function returns `head.next` (to skip the dummy node).

4.  **Main `mergeKLists` Function:**
    * It initiates the divide and conquer process by calling `mergeSort` on the entire array of lists: `mergeSort(lists, 0, lists.length - 1)`.

### Time Complexity

The time complexity is **O(N log k)**, where $N$ is the total number of nodes across all `k` linked lists (sum of `lists[i].length`).
* The `log k` factor comes from the depth of the merge sort tree (dividing `k` lists into halves).
* At each level of the recursion, the `mergeTwoLists` operations collectively process all `N` nodes. For instance, at the bottom level, `k/2` merges of two single-node lists happen, then `k/4` merges of two 2-node lists, and so on. The total work at each level is proportional to `N`.
* Therefore, `log k` levels * `N` work per level = `O(N log k)`.

This is significantly more efficient than the O(N * k) brute-force approach, especially when `k` is large.

### Space Complexity

The space complexity is **O(log k)** due to the recursion stack space used by the `mergeSort` function. The depth of the recursion tree is `log k`.

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
    public ListNode mergeSort(ListNode[] lists,int l,int r){
        if(l < r)
        {
            int m = l +(r-l)/2;
            return mergeTwoLists(mergeSort(lists,l,m),mergeSort(lists,m+1,r));
        }
        else if (l==r)
            return lists[l];
        else
            return null;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeSort(lists , 0 ,lists.length -1);
    }
}