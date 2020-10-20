package byog.lab5;
import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static int HexSize = 5;
    private Random rand;

    public static TETile addHexagon(int x, int y){

    }

    public static String Hexagon(int HexSize){
        String s = new String();
        s += firstHalf(HexSize);
        s += lastHalf(HexSize);
        return s;
    }

    public  static String firstHalf(int HexSize){
        String s = new String();
        int largestSize = HexSize + 2 * (HexSize - 1);
        for(int i = HexSize; i <= largestSize; i += 2){
            s += line(i, largestSize);
        }
        return s;
    }

    public  static String lastHalf(int HexSize){
        String s = new String();
        int largestSize = HexSize + 2 * (HexSize - 1);
        for(int i = largestSize; i >= HexSize; i -= 2){
            s += line(i, largestSize);
        }
        return s.substring(0, s.length() - 1);
    }

    public static String line(int n, int largestSize){
        String s = new String();
        int i;
        int blanks = (largestSize - n) / 2;
        s += duplication(blanks, " ");
        s += duplication(blanks, "a");
        s += duplication(blanks, " ");
        return s + "\n";
    }

    public static String duplication(int n, String s){
        String result = s;
        for (int i = 0; i < n; i ++){
            result += s;
        }
        return result;
    }
}
