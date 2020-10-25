package hw3.hash;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < oomages.size(); i++){
            int key = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, 1);
            } else {
                hashMap.put(key, hashMap.get(key) + 1);
            }
        }
        if (Collections.max(hashMap.values()) >= oomages.size() / 2.5) {
            return false;
        }
        if (Collections.min(hashMap.values()) <= oomages.size() / 50) {
            return false;
        }
        return true;
    }
}
