package test;

import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import tools.Tuple;

class TestParser {

    @Test
    void TestFileOpen(){
        File folder = new File("/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/db/data");
        String[] filename = folder.list();
        System.out.println(Arrays.toString(folder.listFiles()));
        Tuple t = new Tuple(33);
    }

    @Test
    void TestParser() throws FileNotFoundException, ParseException {
        String filePath = "/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/queries.sql";
//        CCJSqlParser parser = new CCJSqlParser(new FileInputStream(filePath));
        CCJSqlParser parser = new CCJSqlParser(new FileReader(filePath));

        Statement statement;
        while((statement = parser.Statement()) != null){
            System.out.println("Read statement: " + statement);
            Select select = (Select) statement;

//            System.out.println("Comparison expression is " + expression.toString());
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
            System.out.println(plainSelect.getFromItem());
        }
//        Expression expression;
//        while((expression = parser.ExistsExpression().))
//        Expression expression = parser.Expression();
//        ExpressionVisitor expressionVisitor = new expressionValidator();
////        expression.accept(expressionVisitor);
//        ExistsExpression existsExpression = new ExistsExpression();
//        existsExpression.accept(expressionVisitor);
//        System.out.println(existsExpression.isNot());


    }

    @Test
    void TestFile(){
        File folder = new File("/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/db/data");
        String[] filename = folder.list();
//        FileSearcher searcher = new FileSearcher("/Users/pro/Desktop/Courses/CS4321/CS4321-projects/P1/samples/input/db/data");
//        System.out.println(searcher.search("Boats"));
        System.out.println(Arrays.toString(filename));
    }
}
