package leetcode.a01_arrays_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 001. Two Sum
 * Easy
 * <a href="https://leetcode.com/problems/valid-anagram/">Link</a>
 * <p>
 * Companies
 *
 *
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * <p>
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class L0001_Two_Sum {
    public static void main(String[] args) {
        int[] from = {3, 2, 4};
        int target = 6;
        int[] result = twoSum2(from, target);
        System.out.println(result[0] + "   " + result[1]);
    }

    /**
     * Solution 1: Two-pass Hash Table
     * Runtime 5 ms
     * Beats * 60.75%
     * Memory 44 MB
     * Beats * 16.94%
     * <p>
     * Intuition:
     * To improve our runtime complexity, we need a more efficient way to check if the complement exists in the array.
     * If the complement exists, we need to get its index.
     * What is the best way to maintain a mapping of each element in the array to its index? A hash table.
     * <p>
     * We can reduce the lookup time from O(n) to O(1) by trading space for speed.
     * A hash table is well suited for this purpose because it supports fast lookup in near constant time.
     * I say "near" because if a collision occurred, a lookup could degenerate to O(n) time.
     * However, lookup in a hash table should be amortized O(1) time as long as the hash function was chosen carefully.
     * <p>
     * Algorithm
     * <p>
     * A simple implementation uses two iterations.
     * In the first iteration, we add each element's value as a key and its index as a value to the hash table.
     * Then, in the second iteration, we check if each element's complement (target−nums[i]target - nums[i]target−nums[i]) exists in the hash table.
     * If it does exist, we return current element's index and its complement's index.
     * Beware that the complement must not be nums[i]nums[i]nums[i] itself!
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = null;
        //Create a map to map the val and index
        Map<Integer, Integer> valIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            valIndexMap.put(val, i);
        }

        //Loop it to find the nums
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int valTarget = target - val;
            Integer targetIndex = valIndexMap.get(valTarget);
            //if the val is the same,
            if (val == valTarget) {
                //Scenario 1: itself
                if (targetIndex == i) {
                    continue;
                } else {
                    //Scenario 2: RESULT!
                    return new int[]{i, targetIndex};
                }
            }

            //Scenario 3: RESULT!
            if (valIndexMap.containsKey(valTarget)) {
                return new int[]{i, targetIndex};
            }
        }
        return result;
    }

    /**
     * Solution 2: One-pass Hash Table
     * Runtime 5 ms
     * Beats * 60.75%
     * Memory 44 MB
     * Beats * 16.94%
     * <p>
     * <p>
     * Algorithm
     * It turns out we can do it in one-pass.
     * While we are iterating and inserting elements into the hash table,
     * we also look back to check if current element's complement already exists in the hash table.
     * If it exists, we have found a solution and return the indices immediately.
     *
     *
     *Time complexity: O(n).
     * We traverse the list containing nnn elements only once. Each lookup in the table costs only O(1) time.
     *Space complexity: O(n).
     * The extra space required depends on the number of items stored in the hash table,
     * which stores at most nnn elements.
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = null;
        //Create a map to map the val and index
        Map<Integer, Integer> valIndexMap = new HashMap<>();
        //Loop it to find the nums
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int valTarget = target - val;
            if (valIndexMap.containsKey(valTarget)) {
                Integer targetIndex = valIndexMap.get(valTarget);
                return new int[]{targetIndex, i};
            } else {
                valIndexMap.put(val, i);
            }
        }
        return result;
    }

}
