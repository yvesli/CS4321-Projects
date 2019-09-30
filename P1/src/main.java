import DatabaseCatalog.DatabaseCatalog;
import Interpreter.interpretor;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.Statement;
import operators.ProjectOperator;
import tools.Tuple;

import java.io.*;

public class main {

    public static void main(String[] args) {
        String filePath = args[0];
        DatabaseCatalog databaseCatalog = DatabaseCatalog.getDatabaseCatalog(filePath);

        try {
            FileReader query = new FileReader(databaseCatalog.getFilePath("queries"));
            CCJSqlParser parser = new CCJSqlParser(query);
            Statement statement;
            try {
                int i = 1;
                while ((statement = parser.Statement()) != null) {
                    String outputPath = args[1];
                    File curQueryResult = new File(outputPath + "/query" + i + ".txt");
                    FileWriter fileWriter = new FileWriter(outputPath + "/query" + i + ".txt");
                    interpretor interpretor = new interpretor(statement);
                    String tableName = interpretor.getFromQuery();
                    ProjectOperator projectOperator = new ProjectOperator(tableName, databaseCatalog, statement);
                    Tuple tuple;
                    while ((tuple = projectOperator.getNextTuple()) != null) {
                        System.out.println(tuple.toString());
                        fileWriter.write(tuple.toString() + "\n");
                        fileWriter.flush();
                    }
                    fileWriter.close();
                    i += 1;
                }
            } catch (ParseException | IOException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
