package kirill.service;

import kirill.dao.DbDao;
import kirill.model.DatabaseProperties;
import kirill.model.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 24/10/16.
 */
@Service
public class TablesService {
    @Autowired
    DbDao dbDao;
    public List<TableInfo> createTable( DatabaseProperties dateBaseProperties){

        List<TableInfo>tables=new ArrayList<TableInfo>();
        List<String> tablesNames=dbDao.getTables(dateBaseProperties.getDbName());
        for (String tableName :tablesNames){
            TableInfo table=new TableInfo();
                List<String> columns = dbDao.getColumns(dateBaseProperties.getDbName(), tableName);
                table.setName(tableName);
                table.setColumns(columns);
                tables.add(table);
        }
        return tables;
    }
    public void refreshDatabase(DatabaseProperties dateBaseProperties){
        dbDao.setDataSource(dateBaseProperties);
    }
}
