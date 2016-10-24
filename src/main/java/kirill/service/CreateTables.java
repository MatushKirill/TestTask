package kirill.service;

import kirill.dao.DbDao;
import kirill.model.TablesAndColums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 24/10/16.
 */
@Service
public class CreateTables {
    @Autowired
    DbDao dbDao;
    public Map<String,List<String>> create(List<String> list,String dbName){
        Map<String,List<String>> tables=new HashMap<String, List<String>>();
        String currentName="";
        for (String s:list){
            if (s!=currentName) {
                List<String> columns = dbDao.getColumns(dbName, s);
                currentName = s;
                tables.put(s, columns);
            }else {
                continue;
            }
        }
        return tables;
    }
}
