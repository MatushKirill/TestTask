package kirill.model;

import java.util.List;

/**
 * Created by kirill on 24/10/16.
 */
public class TableInfo {
    private String name;
    private List <String> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
}
