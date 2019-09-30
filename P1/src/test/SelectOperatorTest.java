package test;

import DatabaseCatalog.DatabaseCatalog;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.Statement;
import operators.SelectOperator;
import org.junit.jupiter.api.Test;
import tools.Tuple;

import java.io.FileNotFoundException;
import java.io.StringReader;


class SelectOperatorTest {

    @Test
    void getNextTupleTest() throws FileNotFoundException, ParseException, JSQLParserException {
        String filePath = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input";
        String query = "SELECT * FROM Sailors WHERE 1 < 2 AND 3 = 17";
//        String query = "SELECT * FROM Sailors WHERE Sailors.A + Sailors.B > Sailors.C OR Sailors.A > Sailors.C";
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement = parserManager.parse(new StringReader(query));
        DatabaseCatalog databaseCatalog = DatabaseCatalog.getDatabaseCatalog(filePath);
        SelectOperator selectOperator = new SelectOperator("Sailors", databaseCatalog, statement);
        Tuple t;
        while ((t = selectOperator.getNextTuple()) != null) {
            System.out.println("time");
            System.out.println(t.toString());
        }

    }
}
//        String filePath = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input";
//        DatabaseCatalog databaseCatalog = DatabaseCatalog.getDatabaseCatalog(filePath);
//        System.out.println(databaseCatalog.getFilePath("Boats"));
//        System.out.println(databaseCatalog.getItemColumn("Boats", "D"));
//
//        FileReader query = new FileReader(databaseCatalog.getFilePath("queries"));
//        CCJSqlParser parser = new CCJSqlParser(query);
//        Interpretor interpretor = new Interpretor(parser.Statement());
//
//        SelectOperator selectOperator = new SelectOperator("Boats", interpretor.getPlainSelect(), databaseCatalog);

