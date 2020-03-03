package algorithms.a_sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class S01_Bubble {
    public static void main(String[] args) {
        int[] from = {20, 35, -15, 7, 55, 1, -22};
        /**
         * 1. We compare one ele to the next
         * 2. If the one is greater, we swap them
         * 3. After one loop, we do step 1 and 2 again, but not to the last sorted element
         */
//        for (int i = 0; i < from.length; i++) {
//            int unsortedIndex = from.length - 1;
//            for (int j = 0; j < unsortedIndex; j++) {
//                if (from[j] > from[j + 1]) {
//                    swap(from, j, j + 1);
//                }
//            }
//            Arrays.stream(from).boxed().collect(Collectors.toList()).forEach(item -> System.out.print(item + " "));
//            System.out.println();
//        }

        //Improved version, more intuitive
        for (int unsortedIndex = from.length - 1; unsortedIndex > 0; unsortedIndex--) {
            for (int j = 0; j < unsortedIndex; j++) {
                if (from[j] > from[j + 1]) {
                    swap(from, j, j + 1);
                }
            }
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
