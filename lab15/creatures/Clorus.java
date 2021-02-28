package creatures;

import huglife.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;

public class Clorus extends Creature {
    private int r = 34;
    private int g = 0;
    private int b = 231;

    private double energy;

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public Clorus() {this(1);}

    @Override
    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.1;
    }

    public void stay() {
        energy -= 0.1;
    }

    public Clorus replicate() {
        Clorus baby = new Clorus(energy / 2);
        this.energy = energy / 2;
        return baby;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        boolean flag1 = true, flag2 = false, flag3 = false;
        LinkedList<Direction> emptyDirection = new LinkedList<>();
        LinkedList<Direction> plipDirection = new LinkedList<>();
        for (Direction dir : neighbors.keySet()) {
            Occupant neighbour = neighbors.get(dir);
            if (neighbour.name().equals("empty")) {
                flag1 = false;
                emptyDirection.add(dir);
            }
            if (neighbour.name().equals("plip")) {
                flag2 = true;
                plipDirection.add(dir);
            }
            if (energy >= 1) flag3 = true;
        }
        if (flag1) return new Action(Action.ActionType.STAY);
        else if (flag2) return new Action(Action.ActionType.ATTACK,
                HugLifeUtils.randomEntry(plipDirection));
        else if (flag3) return new Action(Action.ActionType.REPLICATE,
                HugLifeUtils.randomEntry(emptyDirection));
        else return new Action(Action.ActionType.MOVE,
                    HugLifeUtils.randomEntry(emptyDirection));
    }
}
