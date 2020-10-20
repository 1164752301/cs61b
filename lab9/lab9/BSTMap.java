package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Tianyu Tan
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private final K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        Node node = new Node(null, null);
        if (p == null) {
            return null;
        } else if (p.key.equals(key)) {
            return p.value;
        } else if (p.key.compareTo(key) < 0) {
            node = p.right;
        } else if (p.key.compareTo(key) > 0) {
            node = p.left;
        }
        return getHelper(key, node);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
       if (p == null) {
           size ++;
           return new Node(key, value);
       } else if (key.compareTo(p.key) > 0) {
           p.right = putHelper(key, value, p.right);
       } else if (key.compareTo(p.key) < 0) {
           p.left = putHelper(key, value, p.left);
       } else if (key.compareTo(p.key) == 0) {
           p.value = value;
       }
       return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        this.root = putHelper(key, value, root);
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
        return keySetHelper(root);
    }

    public Set<K> keySetHelper(Node current){
        Set<K> mergedSet = new HashSet<>();
        if (current == null){
            return mergedSet;
        } else {
            mergedSet.addAll(keySetHelper(current.left));
            mergedSet.addAll(keySetHelper(current.right));
        }
        return mergedSet;
    }

    private  boolean isEmpty(Node node) {
        return node == null;
    }

    private Node toTheEnd(Node node, Node prev, String direction) {
        if (isEmpty(node)) {
            return prev;
        } else if (direction.equals("right")) {
            return toTheEnd(node.right, node, "right");
        } else {
            return toTheEnd(node.left, node, "left");
        }
    }

    private void remover(Node prev, Node current, String s) {
        size--;
        if (s.equals("right")){
            if(!isEmpty(current.left)) {
                prev.right = current.left;
                toTheEnd(prev, prev.right, "right").right = current.right;
            } else {
                prev.right = current.right;
            }
        } else if (s.equals("")) {
            if (!isEmpty(current.left)) {
                root = current.left;
                toTheEnd(current.left, current.left.right, "right").right = current.right;
            } else {
                root = current.right;
            }
        } else {
            if (!isEmpty(current.left)) {
                prev.left = current.left;
                toTheEnd(prev, prev.right, "right").right = current.right;
            } else {
                prev.left = current.right;
            }
        }

    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        return removeHelper(key, root, root, "");
    }

    private V removeHelper(K key, Node prev, Node current, String s) {
        if (isEmpty(current)){
            return null;
        } else if (key.compareTo(current.key) > 0) {
            return removeHelper(key, current, current.right, "right");
        } else if (key.compareTo(current.key) < 0) {
            return removeHelper(key, current, current.left, "left");
        } else if (key.compareTo(current.key) == 0) {
            remover(prev, current, s);
        }
        return current.value;
    }

    private V removeHelper(K key, V value, Node prev, Node current, String s) {
        if (isEmpty(current)){
            return null;
        } else if (key.compareTo(current.key) > 0) {
            return removeHelper(key, value, current, current.right, "right");
        } else if (key.compareTo(current.key) < 0) {
            return removeHelper(key, value, current, current.left, "left");
        } else if (key.compareTo(current.key) == 0) {
            if (!value.equals(current.value)) {
                return null;
            } else {
                remover(prev, current, s);
            }
        }
        return current.value;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        return removeHelper(key, value, root, root, "");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
