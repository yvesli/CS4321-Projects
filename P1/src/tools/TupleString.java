package tools;

import java.util.Arrays;

public class TupleString {
    private String[] data;

    public TupleString(String... members) {
        this.data = members;
    }

    public String get(int index) {
        try {
            return this.data[index];
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Tuple index out of bound and returned -1");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.data);
    }

    public int length() {
        return this.data.length;
    }


}