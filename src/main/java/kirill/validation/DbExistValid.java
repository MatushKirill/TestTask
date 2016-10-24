package kirill.validation;

import kirill.dao.DbDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by kirill on 14/10/16.
 */
public class DbExistValid implements ConstraintValidator<DbExist,String> {
    @Autowired
    DbDao dbDao;
    public void initialize(DbExist dbExist) {

    }

    public boolean isValid(String dbName, ConstraintValidatorContext ctx) {
        if (dbDao.getTables(dbName)==null){
            return false;
        }
        return true;

    }
}
