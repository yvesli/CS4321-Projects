package DatabaseCatalog;

import tools.FileSearcher;
import tools.Tuple;
import tools.TupleString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class DatabaseCatalog {
    private static DatabaseCatalog databaseCatalog = null;
    private HashMap<String, String> filePath = new HashMap<>();
    private HashMap<String, HashMap<String, Integer>> schemaQuery = new HashMap<>();
    private String dir;

    private DatabaseCatalog(String dir) {
        this.dir = dir;
        storeQueryPath();
        storeTablePath();
        storeSchemaInfo();
    }

    public String getFilePath(String filename) {
        if (filePath.containsKey(filename)) {
            return filePath.get(filename);
        } else {
            System.err.println("FILE " + filename + " NOT FOUND !!");
            System.exit(-1);
            return null;
        }
    }


    public static DatabaseCatalog getDatabaseCatalog(String dirPath) {
        if (databaseCatalog == null) {
            databaseCatalog = new DatabaseCatalog(dirPath);
        }
        return databaseCatalog;
    }

    public int getItemColumn(String tableName, String columnName) {
        try {
            return schemaQuery.get(tableName).get(columnName);
        } catch (NullPointerException e) {
            System.err.println("CANNOT FIND " + tableName);
            e.getStackTrace();
            return -1;
        }
    }

    private void storeQueryPath() {
        filePath.put("queries", this.dir + "/queries.sql");
    }

    private void storeTablePath() {
        String dataPath = this.dir + "/db/data";
        File folder = new File(dataPath);
        for (String filename : Objects.requireNonNull(folder.list())) {
            filePath.put(FileSearcher.removeExtension(filename), dataPath + "/" + filename);
        }
    }

    private void storeSchemaInfo() {
        String schemaPath = this.dir + "/db/schema.txt";
        try {
            File schema = new File(schemaPath);
            Scanner scanner = new Scanner(schema);
            while (scanner.hasNextLine()) {
                TupleString tuple = parseIntoTuple(scanner.nextLine());
                HashMap<String, Integer> line = new HashMap<>();
                for (int i = 1; i < tuple.length(); i++) {
                    line.put(tuple.get(i), i - 1);
                }
                schemaQuery.put(tuple.get(0), line);

            }


        } catch (FileNotFoundException e) {
            System.err.println("CANNOT FIND schema.txt");
            e.printStackTrace();
        }
    }

    public int getColNumber(String tableName) {
        return schemaQuery.get(tableName).size();
    }

    private TupleString parseIntoTuple(String lineEntry) {
        return new TupleString(lineEntry.split(" "));
    }


}
