package creatures;
import huglife.*;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug
 */
public class Plip extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    private double energy;

    /** creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 63 + (int) (96 * e);
        b = 76;
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    public Color color() {
        g = Math.max(0, Math.min(63 + (int) (96 * energy), 255));
        return color(r, g, b);
    }

    /** Do nothing with C, Plips are pacifists. */
    public void attack(Creature c) {
    }

    /** Plips should lose 0.15 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.5;

    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    public void stay() {
        energy += 0.2;
        if (energy > 2) energy = 2;
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    public Plip replicate() {
        Plip baby = new Plip(energy / 2);
        this.energy = energy / 2;
        return baby;
    }

    /** Plips take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        boolean flag1 = true, flag2 = false, flag3 = false, flag4 = true;
        LinkedList<Direction> emptyDirection = new LinkedList<>();
        for (Direction dir : neighbors.keySet()) {
            Occupant neighbour = neighbors.get(dir);
            if (neighbour.name().equals("empty")) {
                flag1 = false;
                emptyDirection.add(dir);
            }
            if (energy >= 1) flag2 = true;
            if (neighbour.name().equals("clorus")) flag3 = true;
        }
        boolean[] actionFlag = {flag1, flag2, flag3, flag4};
        int n = 0;
        for (boolean flag : actionFlag) {
            n += 1;
            if (flag) break;
        }
        if (n == 1) return new Action(Action.ActionType.STAY);
        if (n == 2) return new Action(Action.ActionType.REPLICATE,
                        HugLifeUtils.randomEntry(emptyDirection));
        if (n == 3 && HugLifeUtils.randomInt(0, 1) == 0)
                return new Action(Action.ActionType.MOVE,
                HugLifeUtils.randomEntry(emptyDirection));
        return new Action(Action.ActionType.STAY);
    }

}
