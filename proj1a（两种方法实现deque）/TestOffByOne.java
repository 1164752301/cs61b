import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void TestEqualChars(){
        assertFalse(offByOne.equalChars('b', 'k'));
        assertTrue(offByOne.equalChars('a', 'b'));
    }


}
