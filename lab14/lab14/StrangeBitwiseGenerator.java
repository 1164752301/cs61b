package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private final int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }

    public double next(){
        state = state + 1;
        int weirdState = state & (state >> 3) & (state >> 8) % period;
        return weirdState;
    }
}
