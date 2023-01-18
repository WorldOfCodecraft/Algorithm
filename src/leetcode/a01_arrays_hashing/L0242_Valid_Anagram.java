package leetcode.a01_arrays_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 242. Valid Anagram
 * Easy
 * <a href="https://leetcode.com/problems/valid-anagram/">Link</a>
 * <p>
 * Companies
 * Bloomberg  * 14
 * Amazon  * 12
 * Google  * 2
 * Goldman Sachs  * 2
 * <p>
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 * <p>
 * <p>
 * <p>
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class L0242_Valid_Anagram {
    public static void main(String[] args) {
        String s = "abcdefg";
        String t = "abcdgfe";
        System.out.println(isAnagram2(s, t));
    }

    /**
     * Approach 1: Sorting
     *
     * Runtime 2 ms
     * Beats * 95.50%
     * Memory 42.3 MB
     * Beats * 73.51%
     *
     * Algorithm
     * An anagram is produced by rearranging the letters of sss into ttt.
     * Therefore, if ttt is an anagram of sss, sorting both strings will result in two identical strings.
     * Furthermore, if sss and ttt have different lengths, ttt must not be an anagram of sss and we can return early.
     *
     * Complexity Analysis
     *
     *     Time complexity: O(n log n). Assume that nnn is the length of sss, sorting costs O(n log n) and comparing two strings costs O(n).
     *     Sorting time dominates and the overall time complexity is O(n log n).
     *
     *     Space complexity: O(1). Space depends on the sorting implementation which, usually, costs O(1) auxiliary space if heapsort is used.
     *     Note that in Java, toCharArray() makes a copy of the string so it costs O(n)extra space, but we ignore this for complexity analysis because:
     *         It is a language dependent detail.
     *         It depends on how the function is designed. For example, the function parameter types can be changed to char[].
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        Arrays.sort(charsS);
        Arrays.sort(charsT);
        for (int i = 0; i < charsS.length; i++) {
            if (charsS[i] != charsT[i]) {
                return false;
            }
        }
        Arrays.equals(charsS, charsT);
        return true;
    }

    /**
     * Approach N: Map Counter
     *
     * Runtime 10 ms
     * Beats * 36.95%
     * Memory 43.4 MB
     * Beats * 16.53%
     *
     * Algorithm
     * TODO Used a map as counter, does not work very fast, should use arrays to replace this solution
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < charsS.length; i++) {
            char charS = charsS[i];
            updateAnagramSet2(countMap, charS);
        }
        for (int i = 0; i < charsT.length; i++) {
            char charT = charsT[i];
            if (!removeAnagramSet2(countMap, charT)) {
                return false;
            }
        }
        return true;
    }

    public static void updateAnagramSet2(Map<Character, Integer> countMap, char toUpdate) {
        if (countMap.containsKey(toUpdate)) {
            Integer count = countMap.get(toUpdate);
            countMap.put(toUpdate, ++count);
        } else {
            countMap.put(toUpdate, 1);
        }
    }

    public static boolean removeAnagramSet2(Map<Character, Integer> countMap, char toRemove) {
        if (countMap.containsKey(toRemove)) {
            Integer count = countMap.get(toRemove);
            if (count == 0) {
                return false;
            }
            countMap.put(toRemove, --count);
            return true;
        } else {
            return false;
        }
    }
}
