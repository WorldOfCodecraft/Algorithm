package leetcode.a01_arrays_hashing;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 49. Group Anagrams
 * Medium
 * <a href="https://leetcode.com/problems/group-anagrams/description/">Link</a>
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class L0049_Group_Anagram {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs2 = {"bdddddddddd", "bbbbbbbbbbc"};
        List<List<String>> lists = groupAnagrams2(strs2);
        System.out.println("");
    }

    /**
     * Approach 1: Categorize by Sorted String
     *
     * Runtime7 ms
     * 82.59%
     * Memory45.4 MB
     * 67.16%
     *
     * Intuition
     * Two strings are anagrams if and only if their sorted strings are equal.
     *
     * Algorithm
     * Maintain a map ans : {String -> List} where each key K\text{K}K is a sorted string,
     * and each value is the list of strings from the initial input that when sorted, are equal to K\text{K}K.
     * In Java, we will store the key as a string, eg. code.
     *
     * Complexity Analysis
     *     Time Complexity: O(NKlogK), where N is the length of strs, and K is the maximum length of a string in strs.
     *     The outer loop has complexity O(N) as we iterate through each string.
     *     Then, we sort each string in O(KlogK) time.
     *
     *     Space Complexity: O(NK), the total information content stored in ans.
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        //Def: map storing sorted val as key, original val as val
        Map<String, List<String>> sortedOri = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String oriStr = strs[i];
            char[] oriArrayChar = oriStr.toCharArray();
            Arrays.sort(oriArrayChar);
            //MNote: convert back
            String sorted = new String(oriArrayChar);

            //MNote: It can be shortened as this, it's more clear
            if(!sortedOri.containsKey(sorted)) sortedOri.put(sorted, new ArrayList<>());
            sortedOri.get(sorted).add(sorted);
//            if (sortedOri.containsKey(sorted)) {
//                sortedOri.get(sorted).add(oriStr);
//            } else {
//                List<String> valueList = new ArrayList<>();
//                valueList.add(oriStr);
//                sortedOri.put(sorted, valueList);
//            }

        }
        List<List<String>> result = convertResult(sortedOri);
        return result;
    }

    private static List<List<String>> convertResult(Map<String, List<String>> from) {
        List<List<String>> result = from.values().stream().collect(Collectors.toList());
        return result;
    }

    /**
     * Approach 2: Categorize by Count
     * Runtime19 ms
     * 27.52%
     * Memory47 MB
     * 25.38%
     *
     * Intuition
     * Two strings are anagrams if and only if their character counts (respective number of occurrences of each character) are the same.
     *
     * Algorithm
     * We can transform each string s\text{s}s into a character count, count\text{count}count,
     * consisting of 26 non-negative integers representing the number of a\text{a}a's, b\text{b}b's, c\text{c}c's, etc.
     * We use these counts as the basis for our hash map.
     *
     * In Java, the hashable representation of our count will be a string delimited with '#' characters.
     * For example, abbccc will be #1#2#3#0#0#0...#0 where there are 26 entries total.
     * In python, the representation will be a tuple of the counts.
     * For example, abbccc will be (1, 2, 3, 0, 0, ..., 0), where again there are 26 entries total.
     *
     * Anagrams
     *
     * Complexity Analysis
     *
     *     Time Complexity: O(NK), where NNN is the length of strs, and K is the maximum length of a string in strs.
     *     Counting each string is linear in the size of the string, and we count every string.
     *
     *     Space Complexity: O(NK), the total information content stored in ans.
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                //This is a must, or some numbers confused with each other, like 110, can be 110, or 1 10
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            //Equals to, but stream is slower
//            String key = Arrays.stream(count).mapToObj(num -> num + "#").collect(Collectors.joining());
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

}
