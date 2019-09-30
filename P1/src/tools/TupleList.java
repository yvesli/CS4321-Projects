package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TupleList {
    private List<Object> data = new ArrayList<>();

    public TupleList(Object... members) {
        this.data.addAll(Arrays.asList(members));
    }

    public Object get(int index) {
        return data.get(index);
    }
}
