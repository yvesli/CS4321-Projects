package tools;

import java.util.Arrays;

public class Tuple {
    private int[] data;

    public Tuple(int... members) {
        this.data = members;
    }

    public int get(int index) {
        try {
            return this.data[index];
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Tuple index out of bound and returned -1");
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String toString() {
        String result = Arrays.toString(this.data);
        return result.substring(1, result.length() - 1);
    }

    public int length() {
        return this.data.length;
    }

    public int[] toArray() {
        return this.data;
    }

}
