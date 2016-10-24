package kirill.model;

import kirill.validation.DbExist;

/**
 * Created by kirill on 24/10/16.
 */
public class DatabaseProperties {
    @DbExist
    private String dbName;
    private String userName;
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
