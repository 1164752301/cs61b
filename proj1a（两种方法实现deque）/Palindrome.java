public class Palindrome {
    /* Change the word from a string to a deque */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> s = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            s.addLast(word.charAt(i));
        }
        return s;
    }

    /* Check if the string is a palindrome */
    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        return RecursiveHelper(d);
    }

    private boolean RecursiveHelper(Deque<Character> d){
        if ((d.size() == 1) || (d.size() == 0)) {
            return true;
        } else {
            Character first = d.removeFirst();
            Character last = d.removeLast();
            if (first != last){
                return false;
            } else {
                return RecursiveHelper(d);
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        return RecursiveHelperObo(d, cc);
    }

    private boolean RecursiveHelperObo(Deque<Character> d, CharacterComparator cc) {
        if ((d.size() == 1) || (d.size() == 0)) {
            return true;
        } else {
            Character first = d.removeFirst();
            Character last = d.removeLast();
            if (cc.equalChars(first, last) == false) {
                return false;
            } else {
                return RecursiveHelperObo(d, cc);
            }
        }
    }
}
