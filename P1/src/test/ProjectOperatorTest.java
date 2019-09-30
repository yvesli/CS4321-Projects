package test;

import DatabaseCatalog.DatabaseCatalog;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.Statement;
import operators.ProjectOperator;
import operators.SelectOperator;
import org.junit.jupiter.api.Test;
import tools.Tuple;

import java.io.FileNotFoundException;
import java.io.StringReader;

public class ProjectOperatorTest {

    @Test
    void projectOperatorTest() throws JSQLParserException {

        String filePath = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input";
        String query = "SELECT Sailors.A, Sailor.B FROM Sailors WHERE Sailors.A < Sailors.B";
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement = parserManager.parse(new StringReader(query));
        DatabaseCatalog databaseCatalog = DatabaseCatalog.getDatabaseCatalog(filePath);
        ProjectOperator projectOperator = new ProjectOperator("Sailors", databaseCatalog, statement);
        Tuple t;
        while ((t = projectOperator.getNextTuple()) != null) {
            System.out.println(t.toString());
        }

    }
}

