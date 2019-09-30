package operators;

import tools.Tuple;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScanOperator extends Operator {
    private Scanner scanner;
    private File table;

    public ScanOperator(String tablePath) {
        try {
            this.table = new File(tablePath);
            this.scanner = new Scanner(table);
        } catch (FileNotFoundException e) {
            System.err.println("File not found in scanOperator Constructor");
            e.printStackTrace();
        }
    }

    private Tuple parseIntoTuple(String lineEntry) {
        String[] intString = lineEntry.split(",");
        int[] t = new int[intString.length];
        for (int i = 0; i < intString.length; i++) {
            t[i] = Integer.parseInt(intString[i]);
        }
        return new Tuple(t);
    }

    @Override
    /*
    get next available tuple, return null if there is none
     */
    public Tuple getNextTuple() {
        if (scanner.hasNextLine()) {
            return parseIntoTuple(scanner.nextLine());
        }
        return null;
    }

    @Override
    /*
    reset the scanOperator to the beginning
     */
    public void reset() {
        try {
            this.scanner = new Scanner(table);
        } catch (FileNotFoundException e) {
            System.err.println("File not found in reset method");
            e.printStackTrace();
        }
    }

    @Override
    /*
    repeatedly calls getNextTuple() until next tuple is null
     */
    public void dump() {
        while (scanner.hasNextLine()) {
            System.out.println(getNextTuple().toString());
        }
    }

    public boolean hasNextTuple() {
        return scanner.hasNextLine();
    }
}