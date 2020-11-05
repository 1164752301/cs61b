package CouSD;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort

        sortHelperLSD(asciis, maxLength(asciis) - 1);
        return asciis;
    }

    public static int maxLength(String[] asciis) {
        int max = 0;
        for (String s : asciis) {
            max = Math.max(s.length(), max);
        }
        return max;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        // Corner case
        if (index == -1) return;
        int[] auxiliary = new int[asciis.length];
        HashMap<Integer, Integer> frequency = new HashMap<>();
        HashMap<String, Integer> value = new HashMap<>();
        int i = 0;
        // Translate the charater on the specified index to an int and Countsort the array
        for(String s : asciis) {
            if (index > s.length() - 1) auxiliary[i] = 0;
            else auxiliary[i] = s.charAt(index);
            // Correspond each String to the character value
            value.put(s, auxiliary[i]);
            if (!frequency.containsKey(auxiliary[i])) frequency.put(auxiliary[i], 1);
            else frequency.put(auxiliary[i], frequency.get(auxiliary[i]) + 1);
            i++;
        }
        int[] pos = new int[256];
        int posCount = 0;
        for (int j = 0; j < 256; j++) {
            if (value.values().contains(j)) {
                pos[j] = posCount;
                posCount += frequency.get(j);
            }
        }
        String[] copy = asciis.clone();
        for (String s : copy) {
            asciis[pos[value.get(s)]++] = s;
        }
        //execute the next digit
        sortHelperLSD(asciis, index - 1);
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] args) {
        String[] unsorted0 = {"blala", "asdasc", "fdsd", "eef", "e", "aaaaaaaaaaa"};
        String[] unsorted1 = {"b", "c", "f", "e", "d", "a"};
        String[] unsorted2 = {"ba", "ca", "fa", "ea", "da", "aa"};
        String[] unsorted3 = {"ab", "ac", "af", "ae", "ad", "aa"};
        String[] unsorted4 = {"abc", "ab", "aba", "ae", "ac", "aa"};
        String[] unsorted5 = {"ab", "abc", "aba", "eef", "ee", "ac", "aa"};
        String[] sorted0 = sort(unsorted0);
        String[] sorted1 = sort(unsorted1);
        String[] sorted2 = sort(unsorted2);
        String[] sorted3 = sort(unsorted3);
        String[] sorted4 = sort(unsorted4);
        String[] sorted5 = sort(unsorted5);
        System.out.println(Arrays.asList(sorted0));
        System.out.println(Arrays.asList(sorted1));
        System.out.println(Arrays.asList(sorted2));
        System.out.println(Arrays.asList(sorted3));
        System.out.println(Arrays.asList(sorted4));
        System.out.println(Arrays.asList(sorted5));
    }
}
