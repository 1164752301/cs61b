package lab14;

import lab14lib.Generator;
import java.lang.Math;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private double acceleration;
    private int state;

    public  AcceleratingSawToothGenerator(int period, double acceleration) {
        this.period = period;
        this.acceleration = acceleration;
        state = 0;
    }

    @Override
    public double next() {
        if (state == period) {
            state = 0;
            period = (int) Math.floor(period * acceleration);
        }
        return (double) state++ / period;
    }
}
