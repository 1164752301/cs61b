package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Tianyu Tan
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;
    private static final double MIN_LF = 0.125;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return buckets[hash(key)].get(key);
    }

    private void resize(String direction) {
        int factor;
        if (direction.equals("up")) {
            factor = 2;
        } else {
            factor = 1 / 2;
        }
        ArrayMap<K, V>[] temp = new ArrayMap[size * factor];
        int numtemp = temp.length;
        for (int i = 0; i < temp.length; i += 1) {
            temp[i] = new ArrayMap<>();
        }
        for (K key : keySet()) {
           V value = get(key);
           temp[Math.floorMod(key.hashCode(), numtemp)].put(key, value);
        }
        buckets = temp;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (loadFactor() >= MAX_LF) {
            resize("up");
        }
        ArrayMap<K, V> target = buckets[hash(key)];
        if (!target.containsKey(key)) {
            size++;
        }
        target.put(key, value);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < size; i += 1) {
            keyset.addAll(buckets[i].keySet());
        }
        return keyset;
    }



    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V result = buckets[hash(key)].remove(key);
        if (result != null) {
           size--;
        }
        if (loadFactor() <= MIN_LF && buckets.length >= 16) {
            resize("down");
        }
        return result;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        V result = buckets[hash(key)].remove(key, value);
        if (result != null) {
            size--;
        }
        if (loadFactor() <= MIN_LF && buckets.length >= 16) {
            resize("down");
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
