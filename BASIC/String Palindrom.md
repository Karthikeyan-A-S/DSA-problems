# `README.md` for LeetCode Problem: Valid Palindrome

This repository contains a Java solution for LeetCode problem 125: "Valid Palindrome".

---

## 📝 Problem Description

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

---

## 💡 Examples

### Example 1:

**Input:** `s = "A man, a plan, a canal: Panama"`
**Output:** `true`
**Explanation:** `"amanaplanacanalpanama"` is a palindrome.

### Example 2:

**Input:** `s = "race a car"`
**Output:** `false`
**Explanation:** `"raceacar"` is not a palindrome.

### Example 3:

**Input:** `s = " "`
**Output:** `true`
**Explanation:** `s` is an empty string `""` after removing non-alphanumeric characters. Since an empty string reads the same forward and backward, it is a palindrome.

---

## 🚫 Constraints

* `1 <= s.length <= 2 * 10^5`
* `s` consists only of printable ASCII characters.

---

## 🧠 Approach

### Algorithm: Two Pointers with Character Validation

The problem requires us to check for palindromes while ignoring non-alphanumeric characters and case. A two-pointer approach is ideal for this, where one pointer starts from the beginning and the other from the end, moving inwards.

1.  **Initialize Pointers:**
    * Initialize `start` to `0` (beginning of the string).
    * Initialize `end` to `s.length() - 1` (end of the string).

2.  **Iterate and Validate Characters:**
    * Use a `while` loop that continues as long as `start < end`.
    * **Process `start` pointer:**
        * Get the character at `s.charAt(start)`.
        * Convert it to lowercase if it's an uppercase letter. The provided code uses ASCII values (`>=65 && <=90` for uppercase, adding `32` to convert to lowercase).
        * Check if the character is alphanumeric (lowercase letter `a-z` or digit `0-9`). The provided code uses ASCII values (`>=97 && <=122` for lowercase letters, `>=48 && <=57` for digits).
        * If it's **not** alphanumeric, increment `start` and `continue` to the next iteration (skip comparison for this character).
    * **Process `end` pointer:**
        * Get the character at `s.charAt(end)`.
        * Convert it to lowercase if it's an uppercase letter (same logic as `startc`).
        * Check if the character is alphanumeric (same logic as `startc`).
        * If it's **not** alphanumeric, decrement `end` and `continue` to the next iteration.
    * **Compare Alphanumeric Characters:**
        * If both `startc` and `endc` are alphanumeric, compare them.
        * If `startc == endc`, they match. Increment `start` and decrement `end` to move inwards.
        * If `startc != endc`, they don't match, so the string is not a palindrome. Return `false`.

3.  **Return Result:**
    * If the loop completes without returning `false`, it means all compared alphanumeric characters matched. Therefore, the string is a palindrome. Return `true`.

### Time Complexity

The time complexity is **O(N)**, where $N$ is the length of the string `s`. In the worst case, both pointers traverse the entire string once.

### Space Complexity

The space complexity is **O(1)**, as we only use a few constant extra variables for pointers and character storage. No additional data structures are used that scale with the input size.

---

## 💻 Complete Java Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        // 'cond' variable is not strictly necessary as we return true/false directly.
        // boolean cond = true; 
        
        // Initialize two pointers: 'start' at the beginning and 'end' at the end of the string.
        int start = 0;
        int end = s.length() - 1;

        // Loop as long as the 'start' pointer is before the 'end' pointer.
        while (start < end) {
            // Get the character at the 'start' pointer.
            char startc = s.charAt(start);

            // Convert uppercase letters to lowercase.
            // ASCII values: 'A' (65) to 'Z' (90). Adding 32 converts to lowercase.
            if (startc >= 65 && startc <= 90) {
                startc = (char) (startc + 32);
            }

            // Check if 'startc' is NOT an alphanumeric character (lowercase letter or digit).
            // ASCII values: 'a' (97) to 'z' (122), '0' (48) to '9' (57).
            if (!((startc >= 97 && startc <= 122) || (startc >= 48 && startc <= 57))) {
                start++; // If not alphanumeric, move 'start' pointer forward.
                continue; // Skip to the next iteration.
            }

            // Get the character at the 'end' pointer.
            char endc = s.charAt(end);

            // Convert uppercase letters to lowercase (same logic as 'startc').
            if (endc >= 65 && endc <= 90) {
                endc = (char) (endc + 32);
            }

            // Check if 'endc' is NOT an alphanumeric character.
            if (!((endc >= 97 && endc <= 122) || (endc >= 48 && endc <= 57))) {
                end--; // If not alphanumeric, move 'end' pointer backward.
                continue; // Skip to the next iteration.
            }

            // If both 'startc' and 'endc' are alphanumeric, compare them.
            if (startc == endc) {
                start++; // If they match, move both pointers inwards.
                end--;
            } else {
                // If they don't match, the string is not a palindrome.
                return false;
            }
        }
        // If the loop completes, it means all compared alphanumeric characters matched,
        // so the string is a palindrome.
        return true;
    }
}