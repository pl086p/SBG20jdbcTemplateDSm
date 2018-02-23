package db.jdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class DatabaseOperations {

    @Autowired
    private JdbcTemplate jdbcAutowired;

    // get data source configuration "spring.datasource...." from application.properties
    @Value("${spring.dsA.driver-class-name}")   private String dsA_driver;  
    @Value("${spring.dsA.url}")					private String dsA_url; 
    @Value("${spring.dsA.username}")			private String dsA_username; 
    @Value("${spring.dsA.password}")		    private String dsA_password; 

    @Value("${spring.dsB.driver-class-name}")   private String dsB_driver;  
    @Value("${spring.dsB.url}")					private String dsB_url; 
    @Value("${spring.dsB.username}")			private String dsB_username; 
    @Value("${spring.dsB.password}")		    private String dsB_password; 
    
    JdbcTemplate jdbcA;
    JdbcTemplate jdbcB;
    
  
    // use autowired (default) jdbcTemplate on spring.datasource...
    public SqlRowSet getRs_jdbcAutowired_table() {
        String sql = "SELECT * from product";
        SqlRowSet rs = jdbcAutowired.queryForRowSet(sql);
        return rs;
    }  
    
    // set ds from dsA properties and assign to jdbcA
    public void set_jdbcA() {
    	
       	DriverManagerDataSource ds = new DriverManagerDataSource();
    	ds.setDriverClassName(dsA_driver);
    	ds.setUrl(dsA_url);
    	ds.setUsername(dsA_username);
    	ds.setPassword(dsA_password);
    	
    	this.jdbcA = new JdbcTemplate(ds);
    }  
    
    public SqlRowSet getRs_jdbcA() {
    	
        String sql = "select * from customer";
        SqlRowSet rs = jdbcA.queryForRowSet(sql);
        return rs;   
    }    
    
 // set ds from dsB properties and assign to jdbcB
    public void set_jdbcB() {
    	
       	DriverManagerDataSource ds = new DriverManagerDataSource();
    	ds.setDriverClassName(dsB_driver);
    	ds.setUrl(dsB_url);
    	ds.setUsername(dsB_username);
    	ds.setPassword(dsB_password);
    	
    	this.jdbcB = new JdbcTemplate(ds);
    }  
    
    public SqlRowSet getRs_jdbcB() {
    	
        String sql = "select * from parent";
        SqlRowSet rs = jdbcB.queryForRowSet(sql);
        return rs;   
    }          
   
    public List<Map<String, Object>> getMapList_jdbcB() {
    	
        String sql = "select * from parent";
        
        System.out.println(" "); 
        List<Map<String, Object>> list = jdbcB.queryForList(sql);
        return list;

    }           

    
    public SqlRowSet queryViewPivot() {
        String sql = "SELECT * from vw_student_score_pivot";
        SqlRowSet rs = jdbcB.queryForRowSet(sql);
        return rs;
    }  
    
    public SqlRowSet queryViewUnpivot() {
        String sql = "SELECT * from vw_student_score_unpivot";
        SqlRowSet rs = jdbcB.queryForRowSet(sql);
        return rs;
    }    
}
