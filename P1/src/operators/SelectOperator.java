package operators;

import DatabaseCatalog.DatabaseCatalog;
import Validators.expressionValidator;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.Statement;

import tools.Tuple;


public class SelectOperator extends Operator {
    private ScanOperator scanOperator;
    private DatabaseCatalog databaseCatalog;
    private Statement statement;

    public SelectOperator(String tableName, DatabaseCatalog databaseCatalog, Statement statement) {
        this.scanOperator = new ScanOperator(databaseCatalog.getFilePath(tableName));
        this.databaseCatalog = databaseCatalog;
        this.statement = statement;
    }

    @Override
    public Tuple getNextTuple() {
        while (scanOperator.hasNextTuple()) {
            Tuple tuple = scanOperator.getNextTuple();
            if (selectionCondition(tuple)) {
                return tuple;
            }
        }
        return null;
    }

    @Override
    public void reset() {
        scanOperator.reset();
    }

    @Override
    public void dump() {
        scanOperator.dump();
    }

    private Expression getWhere() {
        Select select = (Select) this.statement;
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return plainSelect.getWhere();
    }

    private boolean selectionCondition(Tuple tuple) {
        Expression where = getWhere();
        expressionValidator expressionValidator = new expressionValidator(tuple, databaseCatalog);
        where.accept(expressionValidator);
        return expressionValidator.isPass();
    }
}
