package leetcode.a01_arrays_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate
 * Easy
 * <a href="https://leetcode.com/problems/contains-duplicate/description/">Link</a>
 * <p>
 * Description:
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class L0217_Contains_Duplicate {
    public static void main(String[] args) {
        int[] nums = {1,1,1,3,3,4,3,2,4,2};
        System.out.println(containsDuplicate1(nums));
    }

    /**
     * Approach #3 (Hash Table)
     * Runtime12 ms
     * 53.73%
     * Memory53.7 MB
     * 93.67%
     * <p>
     * Intuition
     * Utilize a dynamic data structure that supports fast search and insert operations.
     * <p>
     * Algorithm
     * From Approach #1 we know that search operations is O(n) in an unsorted array and we did so repeatedly.
     * Utilizing a data structure with faster search time will speed up the entire algorithm.
     * There are many data structures commonly used as dynamic sets such as Binary Search Tree and Hash Table.
     * The operations we need to support here are search() and insert().
     * For a self-balancing Binary Search Tree (TreeSet or TreeMap in Java), search() and insert() are both O(log n) time.
     * For a Hash Table (HashSet or HashMap in Java), search() and insert() are both O(1) on average.
     * Therefore, by using hash table, we can achieve linear time complexity for finding the duplicate in an unsorted array.
     * <p>
     * Complexity Analysis
     * Time complexity: O(n). We do search() and insert() for nnn times and each operation takes constant time.
     * Space complexity: O(n). The space used by a hash table is linear with the number of elements in it.
     * <p>
     * Note
     * For certain test cases with not very large nnn, the runtime of this method can be slower than Approach #2.
     * The reason is hash table has some overhead in maintaining its property.
     * One should keep in mind that real world performance can be different from what the Big-O notation says.
     * The Big-O notation only tells us that for sufficiently large input, one will be faster than the other.
     * Therefore, when nnn is not sufficiently large, an O(n) algorithm can be slower than an O(n log n) algorithm.
     * <p>
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> pool = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int thisNum = nums[i];
            if (pool.contains(thisNum)) return true;
            pool.add(thisNum);
        }
        return false;
    }

    /**
     * Approach #2 (Sorting)
     * Intuition:
     * If there are any duplicate integers, they will be consecutive after sorting.
     * <p>
     * Runtime21 ms
     * 22.57%
     * Memory59.1 MB
     * 19.17%
     * <p>
     * Algorithm:
     * This approach employs sorting algorithm.
     * Since comparison sorting algorithm like heapsort is known to provide O(n log n) worst-case performance,
     * sorting is often a good preprocessing step.
     * After sorting, we can sweep the sorted array to find if there are any two consecutive duplicate elements.
     * <p>
     * Complexity Analysis:
     * Time complexity: O(n log n).
     * Sorting is O(n log n) and the sweeping is O(n).
     * The entire algorithm is dominated by the sorting step, which is O(nlogn).
     * <p>
     * Space complexity:
     * O(1).
     * Space depends on the sorting implementation which, usually, costs O(1) auxiliary space if heapsort is used.
     * <p>
     * Note:
     * The implementation here modifies the original array by sorting it. In general,
     * it is not a good practice to modify the input unless it is clear to the caller that the input will be modified.
     * One may make a copy of nums and operate on the copy instead.
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i + 1 < nums.length; i++) {
            int numA = nums[i];
            int numB = nums[i + 1];
            if (numA == numB) return true;
        }
        return false;
    }

}