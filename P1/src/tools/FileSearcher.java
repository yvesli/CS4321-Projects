package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FileSearcher {

    public FileSearcher() {
    }

    public static File search(String filename, String dir) {
        File folder = new File(dir);
        try {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (removeExtension(file.getName()).equals(filename)) {
                    return file;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("No file in directory");
            e.getStackTrace();
        }
        return null;
    }

    public static String removeExtension(String filename) {
        int pos = filename.lastIndexOf('.');
        if (pos == -1)
            return filename;
        return filename.substring(0, pos);
    }

    public static File getQueryFile(String dir) {
        return new File(dir + "/queries.sql");
    }


}
