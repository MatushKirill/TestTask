package kirill.validation;

import kirill.dao.DbDao;
import kirill.model.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by kirill on 25/10/16.
 */
@Component
public class DbValidator implements Validator {
    @Autowired
    DbDao dbDao;
    @Override
    public boolean supports(Class<?> clazz) {
        return DatabaseProperties.class.equals(clazz);

    }

    @Override
    public void validate(Object o, Errors errors) {
        DatabaseProperties databaseProperties= (DatabaseProperties) o;
        if (dbDao.getTables(databaseProperties.getDbName())==null){
            errors.rejectValue("dbName","dbIsExist", new Object[]{"dbName"},"UnKnown Db");
        }
    }
}
