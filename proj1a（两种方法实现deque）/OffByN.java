public class OffByN implements CharacterComparator{
    /* Construct a comparator which returns true if the two characters are different by n */
    public int diff;
    public OffByN(int N){
        diff = N;
    }

    /* returns true if the two characters are different by n */
    public boolean equalChars(char x, char y){
        if ((x - y == diff) || (x - y == -diff)){
            return true;
        } else {
            return false;
        }
    }
}
