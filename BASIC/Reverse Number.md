# `README.md` for LeetCode Problem: Reverse Integer

This repository contains a Java solution for LeetCode problem: "Reverse Integer".

---

## 📝 Problem Description

Given a signed 32-bit integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-2^31, 2^31 - 1]`, then return `0`.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

---

## 💡 Examples

### Example 1:

**Input:** `x = 123`
**Output:** `321`

### Example 2:

**Input:** `x = -123`
**Output:** `(-321)`

### Example 3:

**Input:** `x = 120`
**Output:** `21`
**Explanation:** Leading zeros are removed in the reversed number.

---

## 🚫 Constraints

* `-2^31 <= x <= 2^31 - 1` (This is the range for a signed 32-bit integer, i.e., `Integer.MIN_VALUE` to `Integer.MAX_VALUE`).

---

## 🧠 Approach

### Algorithm: Iterative Digit Extraction and Reconstruction with Overflow Check

The core idea is to extract digits from the input integer `x` one by one from right to left and reconstruct the reversed number. A critical aspect is to handle potential integer overflow without using 64-bit integers for intermediate calculations.

1.  **Initialize Reversed Number:**
    * A variable `r` (for reversed) is initialized to `0`. This variable will accumulate the reversed digits.

2.  **Iterative Reversal (Most Digits):**
    * A `while` loop continues as long as `x` has more than one digit (i.e., `x / 10 != 0`).
    * Inside the loop:
        * **Extract Last Digit:** `x % 10` gives the last digit of `x`.
        * **Build Reversed Number:** `r = r * 10 + x % 10;` This shifts the existing `r` by one decimal place to the left and adds the new last digit.
        * **Remove Last Digit:** `x /= 10;` removes the last digit from `x`, preparing for the next iteration.

3.  **Overflow Check (Before Final Digit):**
    * After the loop, `x` holds the last remaining digit of the original number (or `0` if the original number was a single digit or `0`).
    * The condition `if (r > Integer.MAX_VALUE / 10 || r < Integer.MIN_VALUE / 10)` is a crucial overflow check.
        * `Integer.MAX_VALUE / 10` is the largest integer `r` can be such that `r * 10` *might* not overflow.
        * `Integer.MIN_VALUE / 10` is the smallest integer `r` can be such that `r * 10` *might* not overflow.
        * If `r` (the reversed number accumulated so far, *without* the last digit of the original `x`) is already greater than `Integer.MAX_VALUE / 10` or less than `Integer.MIN_VALUE / 10`, it means that multiplying `r` by 10 (even before adding the final digit) would definitely cause an overflow. In this scenario, `0` is returned.

4.  **Add Final Digit and Return:**
    * If the overflow check passes, it means `r * 10` will not overflow, and adding the last digit `x` (which is `x % 10` from the original number) will also not cause an overflow.
    * The final reversed number is calculated as `r * 10 + x` and returned.

### Time Complexity

The time complexity is **O(log |x|)**, where $|x|$ is the absolute value of the input integer. This is because the number of iterations in the `while` loop is proportional to the number of digits in `x`, which is logarithmic with respect to the magnitude of `x`.

### Space Complexity

The space complexity is **O(1)**, as only a few constant extra variables are used.

---

## 💻 Complete Java Code

```java
class Solution {
    public int reverse(int x) {
        int r=0;
        while(x/10 != 0){
            r = r*10 + x%10;
            x /=10;
        }
        if(r>Integer.MAX_VALUE/10 || r<Integer.MIN_VALUE/10)
            return 0;
        return r*10 +x;
    }
}