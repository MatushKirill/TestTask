package kirill.model;

import javax.persistence.Table;

/**
 * Created by kirill on 24/10/16.
 */
public class TablesAndColums {
    private String tableName;
    private String columnName;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
