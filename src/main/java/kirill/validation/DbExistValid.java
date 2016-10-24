package kirill.validation;

import kirill.dao.DbDao;
import kirill.model.DatabaseProperties;
import kirill.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;

/**
 * Created by kirill on 14/10/16.
 */
@Component
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
