package Validators;

import DatabaseCatalog.DatabaseCatalog;
import Validators.ProjectValidator;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.*;

import java.util.ArrayList;
import java.util.List;

public class SelectItemValidator implements SelectItemVisitor {
    private List<String> colName = new ArrayList<>();
    private int colNumber;
    private DatabaseCatalog databaseCatalog;
    private String tableName;
    private boolean allCol = false;

    public SelectItemValidator(DatabaseCatalog databaseCatalog, String tableName) {
        this.databaseCatalog = databaseCatalog;
        this.tableName = tableName;
    }

    public int getColNumber() {
        return colNumber;
    }

    public boolean isAllCol() {
        return this.allCol;
    }

    @Override
    public void visit(AllColumns allColumns) {
        this.allCol = true;
    }

    @Override
    public void visit(AllTableColumns allTableColumns) {
        System.out.println("allTableColumns");
    }

    @Override
    public void visit(SelectExpressionItem selectExpressionItem) {
        Expression expression = selectExpressionItem.getExpression();
        ProjectValidator projectValidator = new ProjectValidator(tableName, databaseCatalog);
        expression.accept(projectValidator);
        this.colNumber = projectValidator.getColNumber();
    }
}
