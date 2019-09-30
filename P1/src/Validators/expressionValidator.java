package Validators;

import DatabaseCatalog.DatabaseCatalog;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;
import tools.Tuple;


public class expressionValidator implements ExpressionVisitor {
    private boolean pass = false;
    private Tuple tuple;
    private DatabaseCatalog databaseCatalog;
    private boolean isValue = false;
    private int value;

    //
    public expressionValidator(Tuple tuple, DatabaseCatalog databaseCatalog) {
        this.tuple = tuple;
        this.databaseCatalog = databaseCatalog;
    }

    //
    public boolean isPass() {
        return this.pass;
    }

    public boolean isValue() {
        return this.isValue;
    }

    public int getValue() {
        return this.value;
    }


    @Override
    public void visit(NullValue nullValue) {
        this.pass = true;
    }

    @Override
    public void visit(Function function) {
    }

    @Override
    public void visit(InverseExpression inverseExpression) {
    }

    @Override
    public void visit(JdbcParameter jdbcParameter) {
    }

    @Override
    public void visit(DoubleValue doubleValue) {
    }

    @Override
    public void visit(LongValue longValue) {
        this.isValue = true;
        this.value = (int) longValue.getValue();
    }

    @Override
    public void visit(DateValue dateValue) {
    }

    @Override
    public void visit(TimeValue timeValue) {
    }

    @Override
    public void visit(TimestampValue timestampValue) {
    }

    @Override
    public void visit(Parenthesis parenthesis) {
    }

    @Override
    public void visit(StringValue stringValue) {
    }

    @Override
    public void visit(Addition addition) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        addition.getLeftExpression().accept(leftExpression);
        addition.getRightExpression().accept(rightExpression);
        this.value = leftExpression.getValue() + rightExpression.getValue();
        this.isValue = true;
        this.pass = true;
    }

    @Override
    public void visit(Division division) {
        System.out.println(division.getStringExpression());
    }

    @Override
    public void visit(Multiplication multiplication) {
        System.out.println(multiplication.getStringExpression());
    }

    @Override
    public void visit(Subtraction subtraction) {
        System.out.println(subtraction.getStringExpression());
    }

    @Override
    public void visit(AndExpression andExpression) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        andExpression.getLeftExpression().accept(leftExpression);
        andExpression.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.isPass() && rightExpression.isPass());

    }

    @Override
    public void visit(OrExpression orExpression) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        orExpression.getLeftExpression().accept(leftExpression);
        orExpression.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.isPass() || rightExpression.isPass());
    }

    @Override
    public void visit(Between between) {
    }

    @Override
    public void visit(EqualsTo equalsTo) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        equalsTo.getLeftExpression().accept(leftExpression);
        equalsTo.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.getValue() == rightExpression.getValue());
    }

    @Override
    public void visit(GreaterThan greaterThan) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        greaterThan.getLeftExpression().accept(leftExpression);
        greaterThan.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.getValue() > rightExpression.getValue());
    }

    @Override
    public void visit(GreaterThanEquals greaterThanEquals) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        greaterThanEquals.getLeftExpression().accept(leftExpression);
        greaterThanEquals.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.getValue() >= rightExpression.getValue());
    }

    @Override
    public void visit(InExpression inExpression) {
        System.out.println(inExpression.toString());
    }

    @Override
    public void visit(IsNullExpression isNullExpression) {
        System.out.println(isNullExpression.toString());
    }

    @Override
    public void visit(LikeExpression likeExpression) {
        System.out.println(likeExpression.toString());
    }

    @Override
    public void visit(MinorThan minorThan) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        minorThan.getLeftExpression().accept(leftExpression);
        minorThan.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.getValue() < rightExpression.getValue());
    }

    @Override
    public void visit(MinorThanEquals minorThanEquals) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        minorThanEquals.getLeftExpression().accept(leftExpression);
        minorThanEquals.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.getValue() <= rightExpression.getValue());
    }

    @Override
    public void visit(NotEqualsTo notEqualsTo) {
        expressionValidator leftExpression = new expressionValidator(tuple, databaseCatalog);
        expressionValidator rightExpression = new expressionValidator(tuple, databaseCatalog);
        notEqualsTo.getLeftExpression().accept(leftExpression);
        notEqualsTo.getRightExpression().accept(rightExpression);
        this.pass = (leftExpression.getValue() != rightExpression.getValue());
    }

    @Override
    public void visit(Column column) {
        int colNumber = databaseCatalog.getItemColumn(column.getTable().getName(), column.getColumnName());
        this.value = tuple.get(colNumber);
        this.pass = true;
    }

    @Override
    public void visit(SubSelect subSelect) {
    }

    @Override
    public void visit(CaseExpression caseExpression) {
    }

    @Override
    public void visit(WhenClause whenClause) {
    }

    @Override
    public void visit(ExistsExpression existsExpression) {
    }

    @Override
    public void visit(AllComparisonExpression allComparisonExpression) {
    }

    @Override
    public void visit(AnyComparisonExpression anyComparisonExpression) {
    }

    @Override
    public void visit(Concat concat) {
    }

    @Override
    public void visit(Matches matches) {
    }

    @Override
    public void visit(BitwiseAnd bitwiseAnd) {
    }

    @Override
    public void visit(BitwiseOr bitwiseOr) {
    }

    @Override
    public void visit(BitwiseXor bitwiseXor) {
    }
}
