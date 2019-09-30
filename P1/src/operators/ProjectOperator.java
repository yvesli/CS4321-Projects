package operators;

import DatabaseCatalog.DatabaseCatalog;
import Validators.SelectItemValidator;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import tools.Tuple;

import java.util.ArrayList;
import java.util.List;

public class ProjectOperator extends Operator {
    private DatabaseCatalog databaseCatalog;
    private Statement statement;
    private Operator operator;
    private boolean hasWhere;
    private List<Integer> colNumber = new ArrayList<>();


    public ProjectOperator(String tableName, DatabaseCatalog databaseCatalog, Statement statement) {
        this.databaseCatalog = databaseCatalog;
        this.statement = statement;
        Select select = (Select) this.statement;
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        if (plainSelect.getWhere() != null) {
            this.hasWhere = true;
            this.operator = new SelectOperator(tableName, databaseCatalog, statement);
        } else {
            this.hasWhere = false;
            this.operator = new ScanOperator(databaseCatalog.getFilePath(tableName));
        }
        List<SelectItem> selectItem = plainSelect.getSelectItems();
        for (SelectItem item : selectItem) {
            SelectItemValidator selectItemValidator = new SelectItemValidator(databaseCatalog, tableName);
            item.accept(selectItemValidator);
            if (selectItemValidator.isAllCol()) {
                for (int i = 0; i < databaseCatalog.getColNumber(tableName); i++) {
                    this.colNumber.add(i);
                }
            } else {
                this.colNumber.add(selectItemValidator.getColNumber());
            }
        }
    }

    @Override
    public Tuple getNextTuple() {
        Tuple tuple = operator.getNextTuple();
        if (tuple == null) {
            return null;
        }
        int[] t = new int[this.colNumber.size()];
        for (int i = 0; i < this.colNumber.size(); i++) {
            t[i] = tuple.get(colNumber.get((i)));
        }
        return new Tuple(t);
    }

    @Override
    public void reset() {
        operator.reset();
    }

    @Override
    public void dump() {
        operator.dump();
    }
}
