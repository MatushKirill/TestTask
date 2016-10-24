package kirill.model;

import kirill.validation.DbExist;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by kirill on 24/10/16.
 */
public class DatabaseProperties {
//    @DbExist
    @NotEmpty
    private String dbName;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;


    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
