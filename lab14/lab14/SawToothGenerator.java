package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private final int period;
    private double state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    public double next(){
        return -1 + state++ % period / period;
    }
}
