# `README.md` for LeetCode Problem: Palindrome Number

This repository contains a Java solution for LeetCode problem 9: "Palindrome Number".

---

## 📝 Problem Description

Given an integer `x`, return `true` if `x` is a palindrome, and `false` otherwise.

---

## 💡 Examples

### Example 1:

**Input:** `x = 121`
**Output:** `true`
**Explanation:** `121` reads as `121` from left to right and from right to left.

### Example 2:

**Input:** `x = -121`
**Output:** `false`
**Explanation:** From left to right, it reads `-121`. From right to left, it becomes `121-`. Therefore it is not a palindrome.

### Example 3:

**Input:** `x = 10`
**Output:** `false`
**Explanation:** Reads `01` from right to left. Therefore it is not a palindrome.

---

## 🚫 Constraints

* `-2^31 <= x <= 2^31 - 1` (This is the range for a signed 32-bit integer, i.e., `Integer.MIN_VALUE` to `Integer.MAX_VALUE`).

---

## 🧠 Approach

### Algorithm: Reversing Half the Number

The most straightforward approach to check if a number is a palindrome is to reverse it and then compare it with the original number. However, to avoid potential integer overflow when reversing very large numbers, a more robust method involves reversing only *half* of the number.

1.  **Handle Edge Cases:**
    * **Negative Numbers:** Any negative number cannot be a palindrome because of the leading minus sign. For example, `-121` reversed is `121-`, which is not the same. So, if `x < 0`, return `false`.
    * **Numbers Ending in Zero (except 0 itself):** If a number ends in `0` (e.g., `10`, `100`), for it to be a palindrome, it would also have to start with `0`. The only number that satisfies this is `0` itself. Any other number ending in `0` will not be a palindrome when reversed (e.g., `10` reversed is `1`). So, if `x % 10 == 0` and `x != 0`, return `false`.

2.  **Reverse Half the Number:**
    * Initialize a variable `reversedHalf` to `0`. This will store the reversed second half of the number.
    * Use a `while` loop that continues as long as `x` (the original number, which is progressively shortened) is greater than `reversedHalf`. This condition ensures we only reverse up to the middle of the number.
    * Inside the loop:
        * Extract the last digit of `x`: `x % 10`.
        * Append this digit to `reversedHalf`: `reversedHalf = reversedHalf * 10 + x % 10;`.
        * Remove the last digit from `x`: `x /= 10;`.

3.  **Compare for Palindrome:**
    * After the loop, we have two scenarios:
        * **Even Number of Digits:** If the original number had an even number of digits, `x` will be equal to `reversedHalf`. For example, `1221`: `x` becomes `12`, `reversedHalf` becomes `12`.
        * **Odd Number of Digits:** If the original number had an odd number of digits, `x` will be equal to `reversedHalf` divided by 10 (because the middle digit doesn't need to be compared). For example, `12321`: `x` becomes `12`, `reversedHalf` becomes `123`. We compare `x` (`12`) with `reversedHalf / 10` (`123 / 10 = 12`).
    * Therefore, return `true` if `x == reversedHalf` or `x == reversedHalf / 10`.

The provided solution, however, reverses the *entire* number and then compares it. This works for the given constraints (32-bit integer range) because the reversed number will also fit within a 32-bit integer if the original number fits, and the problem statement explicitly allows for returning 0 if overflow occurs. For this specific problem, the simpler full reversal is acceptable.

### Algorithm (Based on provided code): Full Reversal

The provided code reverses the entire number and compares it with the original.

1.  **Initialize Reversed Number:**
    * `r` (reversed number) is initialized to `0`.
    * `t` stores the original value of `x` for comparison.

2.  **Handle Negative Numbers:**
    * The `while(x > 0)` loop implicitly handles negative numbers: if `x` is negative, the loop condition `x > 0` is immediately false, `r` remains `0`, and the comparison `0 == t` (original negative `x`) will correctly return `false`.

3.  **Reverse Digits:**
    * The `while(x > 0)` loop extracts digits from `x` one by one from right to left.
    * `r = r * 10 + x % 10;` builds the reversed number.
    * `x /= 10;` removes the last digit from `x`.

4.  **Compare:**
    * After the loop, `r` contains the reversed number, and `t` contains the original number.
    * The function returns `r == t`.

This approach is simpler to implement but could theoretically lead to overflow if the reversed number exceeds `Integer.MAX_VALUE`. However, for the constraints of this problem, if `x` is a 32-bit integer, its reversal will also fit within a 32-bit integer, so overflow is not a concern here (unlike in "Reverse Integer" problem where it explicitly asks to return 0 on overflow).

### Time Complexity

The time complexity is **O(log |x|)**, where $|x|$ is the absolute value of the input integer. This is because the number of iterations in the `while` loop is proportional to the number of digits in `x`.

### Space Complexity

The space complexity is **O(1)**, as only a few constant extra variables are used (`r`, `t`).

---

## 💻 Complete Java Code

```java
class Solution {
    public boolean isPalindrome(int x) {
        int r=0;
        int t =x;
        while(x>0){
            r = r*10 + x%10;
            x /=10;
        }
        return r == t;
    }
}