package test;

import operators.ScanOperator;
import org.junit.jupiter.api.Test;
import tools.Tuple;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ScanOperatorTest {
    @Test
    void getNextTupleTest() throws FileNotFoundException {
        String path = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/db/data/Sailors";
        File table = new File("/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/db/data/Sailors");
        Scanner scanner = new Scanner(table);
        ScanOperator scanOperator = new ScanOperator(path);
        while (true) {
            Tuple t = scanOperator.getNextTuple();
            if (t == null){
                break;
            }
            System.out.println(t.toString());
        }
    }

    @Test
    void resetTest() throws FileNotFoundException {
        String path = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/db/data/Sailors";
        File table = new File("/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/db/data/Sailors");
        Scanner scanner = new Scanner(table);
        ScanOperator scanOperator = new ScanOperator(path);
        System.out.println(scanOperator.getNextTuple().toString());
        System.out.println(scanOperator.getNextTuple().toString());
        scanOperator.reset();
        System.out.println(scanOperator.getNextTuple().toString());
        }
}