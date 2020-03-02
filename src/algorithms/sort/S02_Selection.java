package algorithms.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class S02_Selection {
    public static void main(String[] args) {
        int[] from = {20, 35, -15, 7, 55, 1, -22};

        /**
         * 1. Compare first ele to the rest
         * 2. Once we found a greater one, we record it's index
         * 3. After finished, we swap the first one to the largest one of their position
         * 4. We start again, but without doing the last unsorted index
         */
        for (int unsortedIndex = from.length - 1, largestIndex = 0; unsortedIndex > 0; unsortedIndex--, largestIndex = 0) {
            for (int j = 0; j < unsortedIndex; j++) {
                int largest = from[largestIndex];
                if (from[j] > largest) {
                    largestIndex = j;
                }
            }
            swap(from, largestIndex, unsortedIndex);
            Arrays.stream(from).boxed().collect(Collectors.toList()).forEach(item -> System.out.print(item + " "));
            System.out.println();
        }
    }

    public static void swap(int[] from, int i, int j) {
        int temp = from[i];
        from[i] = from[j];
        from[j] = temp;
    }
}
