package test;

import DatabaseCatalog.DatabaseCatalog;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import Validators.expressionValidator;
import org.junit.jupiter.api.Test;
import tools.Tuple;

import java.io.FileNotFoundException;
import java.io.StringReader;

class visitorTest {

    @Test
    void ExpressionVisitorTest() throws FileNotFoundException, ParseException, JSQLParserException {
//        Expression greaterThan = new GreaterThan();
//
//        expressionValidator expressionValidator = new expressionValidator();
//
//        greaterThan.accept(expressionValidator);
//
//        String filePath = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/queries.sql";
//        CCJSqlParser parser = new CCJSqlParser(new FileReader(filePath));

//        Statement statement;
//        while((statement = parser.Statement()) != null) {
//            System.out.println("Read statement: " + statement);
//            Select select = (Select) statement;
//            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
//            Expression expression = plainSelect.getWhere();
//            if(expression != null)
//                expression.accept(expressionValidator);
//            System.out.println(expression instanceof MinorThan);
//
//        }
        String statement = "SELECT * FROM Sailors WHERE Sailors.A + Sailors.B > Sailors.C OR Sailors.A > Sailors.C";
        String filePath = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input";
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(statement));

        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        Tuple tuple = new Tuple(20, 31, 50);;
        DatabaseCatalog dbc = DatabaseCatalog.getDatabaseCatalog(filePath);
        System.out.println(dbc.getItemColumn("Sailors", "A"));
        System.out.println(dbc.getItemColumn("Sailors", "B"));
        expressionValidator expressionValidator = new expressionValidator(tuple, dbc);
        where.accept(expressionValidator);
        System.out.println(expressionValidator.isPass());

    }
}
