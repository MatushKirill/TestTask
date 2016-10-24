package kirill.dao;

import kirill.model.DateBaseProperties;
import kirill.model.TablesAndColums;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
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
                String sql="SELECT TABLE_NAME\n" +
                        "FROM information_schema.COLUMNS \n" +
                        "WHERE TABLE_SCHEMA NOT IN(\"information_schema\", \"mysql\", \"performance_schema\") AND TABLE_SCHEMA=?\n" +
                        "ORDER BY TABLE_SCHEMA, TABLE_NAME, ORDINAL_POSITION";
        return jdbcTemplate.queryForList(sql,new Object[]{dbName},String.class);
    }

    public List<String> getColumns(String dbName,String tableName){
        String sql="SELECT  \n" +
                "       COLUMN_NAME\n" +
                "FROM information_schema.COLUMNS \n" +
                "WHERE TABLE_SCHEMA NOT IN(\"information_schema\", \"mysql\", \"performance_schema\") AND TABLE_SCHEMA=? and table_name=?\n" +
                "ORDER BY TABLE_SCHEMA, TABLE_NAME, ORDINAL_POSITION";
        return jdbcTemplate.queryForList(sql,new Object[]{dbName,tableName},String.class);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DateBaseProperties properties) {
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

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final class dbMap implements RowMapper<TablesAndColums> {

        public TablesAndColums mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            TablesAndColums tablesAndColums=new TablesAndColums();
            tablesAndColums.setTableName(resultSet.getString("TABLE_NAME"));
            tablesAndColums.setColumnName(resultSet.getString("COLUMN_NAME"));


            return tablesAndColums;


        }
    }
}
