package Interpreter;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;


public class interpretor {
    private Statement query;
    private FromItem fromItem;
    private PlainSelect plainSelect;

    public interpretor(Statement query) {
        this.query = query;
        getFromItem();
    }

    private void getFromItem() {
        Select select = (Select) this.query;
        this.plainSelect = (PlainSelect) select.getSelectBody();
        this.fromItem = this.plainSelect.getFromItem();
    }

    public String getFromQuery(){
        return ((Table) fromItem).getName();
    }

    public PlainSelect getPlainSelect(){
        return this.plainSelect;
    }

}
