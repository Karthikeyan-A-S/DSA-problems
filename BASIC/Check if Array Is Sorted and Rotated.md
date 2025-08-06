# `README.md` for LeetCode Problem: Check if Array Is Sorted and Rotated

This repository contains a Java solution for LeetCode problem 1752: "Check if Array Is Sorted and Rotated".

---

## 📝 Problem Description

Given an array `nums`, return `true` if the array was originally sorted in non-decreasing order, then rotated **some** number of positions (including zero). Otherwise, return `false`.
There may be duplicates in the original array.

**Note:** An array `A` rotated by `x` positions results in an array `B` of the same length such that `B[i] == A[(i+x) % A.length]` for every valid index `i`.

---

## 💡 Examples

### Example 1:

**Input:** `nums = [3,4,5,1,2]`
**Output:** `true`
**Explanation:** `[1,2,3,4,5]` is the original sorted array. You can rotate the array by `x = 3` positions to begin on the element of value `3`: `[3,4,5,1,2]`.

### Example 2:

**Input:** `nums = [2,1,3,4]`
**Output:** `false`
**Explanation:** There is no sorted array once rotated that can make `nums`.

### Example 3:

**Input:** `nums = [1,2,3]`
**Output:** `true`
**Explanation:** `[1,2,3]` is the original sorted array. You can rotate the array by `x = 0` positions (i.e., no rotation) to make `nums`.

---

## 🚫 Constraints

* `1 <= nums.length <= 100`
* `1 <= nums[i] <= 100`

---

## 🧠 Approach

### Algorithm: Single Pass with Break Point Detection

A sorted and rotated array has a unique property: it will have **at most one "descent"** where `nums[i] > nums[i+1]`. If there are zero descents, the array is fully sorted (zero rotations). If there is exactly one descent, the array might be a valid rotated sorted array, provided the element after the descent is less than or equal to the first element of the array (handling the wrap-around).

The provided solution cleverly implements this check in a concise manner:

1.  **Identify Potential Break Point (`e`):**
    * The first `for` loop initializes `e` to the last index of the array (`nums.length - 1`).
    * It then attempts to find an index `e` (from right to left) such that `nums[0]` is less than `nums[e]`.
    * The condition `nums[p] >= nums[e]` (where `p` is effectively `nums[0]` in the condition check) combined with `e--` means that `e` will be decremented as long as `nums[0]` is greater than or equal to the element at index `e`.
    * This loop effectively finds the index `e` of the largest element in the first "sorted segment" of a potentially rotated array. If the array is not rotated (or `nums[0]` is the smallest), `e` will remain `nums.length - 1`.
    * **Example `[3,4,5,1,2]`:**
        * `e=4` (`nums[4]=2`). `nums[0]=3`. `3 >= 2` is true. `e` becomes `3`.
        * `e=3` (`nums[3]=1`). `nums[0]=3`. `3 >= 1` is true. `e` becomes `2`.
        * `e=2` (`nums[2]=5`). `nums[0]=3`. `3 >= 5` is false. Loop terminates. `e` is `2`.
        * Here, `e=2` correctly identifies `5` as the end of the first sorted segment `[3,4,5]`.

2.  **Verify Sortedness of the First Segment:**
    * The second `for` loop iterates from `s=0` up to `e-1`.
    * It checks if `nums[s] > nums[s+1]` within this segment.
    * If any such "descent" is found in this segment, it means the array is not sorted even in its first part, so it cannot be a rotated sorted array. In this case, `false` is returned.
    * **Example `[3,4,5,1,2]` (with `e=2`):** This loop checks `nums[0]` vs `nums[1]` (`3 < 4`) and `nums[1]` vs `nums[2]` (`4 < 5`). Both are sorted.

3.  **Implicit Wrap-Around and Second Segment Check:**
    * If the second loop completes without returning `false`, it means the segment from `nums[0]` to `nums[e]` is sorted.
    * The initial `for` loop's termination condition (`nums[0] < nums[e]`) or `e` reaching 0, combined with the second loop's success, implicitly handles the overall sorted and rotated property.
    * If the array *is* a valid rotated sorted array, there will be at most one point where `nums[i] > nums[i+1]`. The first loop ensures that `e` is positioned such that if such a break exists, it's immediately after `nums[e]`. The second loop then confirms the sortedness before that break. The overall structure guarantees that if these conditions hold, the array is valid.

### Time Complexity

The time complexity is **O(N)**, where $N$ is the length of the `nums` array. Both loops iterate through the array (or a part of it) at most once.

### Space Complexity

The space complexity is **O(1)**, as only a few constant extra variables are used.

---

## 💻 Complete Java Code

```java
class Solution {
    public boolean check(int[] nums) {
        // 'e' is initialized to the last index of the array.
        int e = nums.length - 1;

        // This loop attempts to find the "pivot" or "break point" from the right.
        // It decrements 'e' as long as 'e' is greater than 0 AND
        // the first element (nums[0]) is greater than or equal to the element at 'e'.
        // The 'p' variable in the loop signature is effectively unused in the condition
        // after the first iteration, as 'nums[0]' is always used.
        // It effectively finds the largest 'e' such that nums[0] < nums[e] (if a rotation occurred)
        // or 'e' becomes 0.
        // After this loop, 'e' points to the last element of the first sorted segment
        // if a rotation occurred, or the last element of the array if it's fully sorted.
        for (int p = 0; 0 < e && nums[p] >= nums[e]; p = e, e--);

        // This loop checks if the segment from index 0 up to 'e' is sorted in non-decreasing order.
        // If any element is greater than its successor in this segment, it's not a valid
        // rotated sorted array.
        for (int s = 0; s < e; s++) {
            if (nums[s] > nums[s + 1])
                return false;
        }

        // If both loops complete without returning false, it means the array is
        // either fully sorted, or it has exactly one "break" point and
        // the elements are correctly ordered around that break, satisfying the
        // rotated sorted array property.
        return true;
    }
}