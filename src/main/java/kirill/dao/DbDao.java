package kirill.dao;

import kirill.model.DatabaseProperties;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kirill on 24/10/16.
 */
@Repository
public class DbDao {
    private BasicDataSource dataSource ;
    private JdbcTemplate jdbcTemplate;

    public List<String> getTables(String dbName){
        try {
            String sql="SELECT DISTINCT TABLE_NAME\n" +
                    "FROM information_schema.COLUMNS \n" +
                    "WHERE TABLE_SCHEMA NOT IN(\"information_schema\", \"mysql\", \"performance_schema\") AND TABLE_SCHEMA=?\n";
            return jdbcTemplate.queryForList(sql,new Object[]{dbName},String.class);
        }catch (Exception e) {
            System.out.println("error");
        }
        return null;
    }

    public List<String> getColumns(String dbName,String tableName){
        String sql="SELECT DISTINCT  \n" +
                "       COLUMN_NAME\n" +
                "FROM information_schema.COLUMNS \n" +
                "WHERE TABLE_SCHEMA NOT IN(\"information_schema\", \"mysql\", \"performance_schema\") AND TABLE_SCHEMA=? and table_name=?\n";
        return jdbcTemplate.queryForList(sql,new Object[]{dbName,tableName},String.class);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DatabaseProperties properties) {
        dataSource=new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());
        dataSource.setUrl("jdbc:mysql://localhost:3306/"+properties.getDbName());
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
