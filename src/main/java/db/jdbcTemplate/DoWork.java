package db.jdbcTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class DoWork {
	
	DatabaseOperations dbOperation;

	public DoWork(ConfigurableApplicationContext context) {
	
		DatabaseOperations databaseOperations = (DatabaseOperations) context
                .getBean("databaseOperations");
		
		this.dbOperation = databaseOperations;

	}

	public void showDataFrom_defaultDatasource() {
		
	    SqlRowSet rs;
	    
	    rs = dbOperation.getRs_jdbcAutowired_table(); 
	    System.out.println("\n = getRs_jdbcAutowired_table: ");   
        while (rs.next()) {
        	System.out.println("    - " + rs.getString("name"));   
        }
        
	    rs = dbOperation.getRs_jdbcAutowired_table(); 
	    System.out.println("\n = getRs_jdbcAutowired_view(): ");   
        while (rs.next()) {
        	System.out.println("    - " + rs.getString("name"));   
        }
    
	}	    
 
	// schema1.customer
	public void showDataFrom_dsA() {
		
 		dbOperation.set_jdbcA();
 		
	    SqlRowSet rs = dbOperation.getRs_jdbcA(); 
        
	    System.out.println("\n - showDataFrom_dsA - getRs_jdbcA(): ");   
        
        while (rs.next()) {
        	System.out.println(" - " + rs.getString("customer_name"));   
        }

        rs.last();
	    System.out.println("\n - showDataFrom_dsA - getRow(): " + rs.getRow() + "\n");
	    
	}

	// schema2.book 
	public void showDataFrom_dsB() {
		
 		dbOperation.set_jdbcB();
 		
	    SqlRowSet rs = dbOperation.getRs_jdbcB(); 

        System.out.println("\n - showDataFrom_dsB - getRs_jdbcB(): ");   
        while (rs.next()) {
        	System.out.println(" - " + rs.getString("name"));   
        }
 	    rs.last();
	    System.out.println("\n - showDataFrom_dsB - getRow(): " + rs.getRow() + "\n\n");
	    
	    
	    System.out.println("\n - showDataFrom_dsB - getMapList_jdbcB(): ");
        List<Map<String, Object>> list = dbOperation.getMapList_jdbcB();
        for (Map<String, Object> row : list) {
            System.out.println(" - " + row.get("name"));
        }	    
	    
	}

}

   
