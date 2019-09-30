package operators;

import tools.Tuple;

public abstract class Operator {
    public abstract Tuple getNextTuple();

    public abstract void reset();

    public abstract void dump();
}
