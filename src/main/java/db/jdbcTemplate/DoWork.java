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
	    SqlRowSet rs = dbOperation.getRs_jdbcAutowired(); 
	    
	    System.out.println("\n - showDataFrom_defaultDatasource(): autowired to default data source (schema public) \n");   
        while (rs.next()) {
        	System.out.println(" - " + rs.getString("name"));   
        }
        
	    rs.last();
	    System.out.println("\n - showDataFrom_defaultDatasource - row count: " + rs.getRow() + "\n");
	}	    
 
	// schema1.customer
	public void showDataFrom_dsA() {
		
 		dbOperation.set_jdbcA();
	    SqlRowSet rs = dbOperation.getRs_jdbcA(); 
	    
        System.out.println("\n - showDataFrom_dsA (mySchema1) - getRs_jdbcA():" + "\n");   
        
        while (rs.next()) {
        	System.out.println(" - " + rs.getString("customer_name"));   
        }
        
	    rs.last();
	    System.out.println("\n - showDataFrom_dsA (mySchema1) - row count: " + rs.getRow() + "\n");
	}

	// schema2.book 
	public void showDataFrom_dsB() {
		
 		dbOperation.set_jdbcB();
 		
	    SqlRowSet rs = dbOperation.getRs_jdbcB(); 
        System.out.println("\n - showDataFrom_dsB (mySchema2) - getRs_jdbcB(): ");   
        while (rs.next()) {
        	System.out.println(" - " + rs.getString("name"));   
        }
 	    rs.last();
	    System.out.println("\n - showDataFrom_dsB (mySchema2) - row count = " + rs.getRow() + "\n\n");
	    
	    
	    System.out.println("\n - showDataFrom_dsB (mySchema2) - getMapList_jdbcB(): \n");
        List<Map<String, Object>> list = dbOperation.getMapList_jdbcB();
        for (Map<String, Object> row : list) {
            System.out.println(" - " + row.get("name"));
        }	    
        
	    SqlRowSet rsPivot = dbOperation.queryViewPivot(); 
        System.out.println("\n - showDataFrom_dsB (mySchema2) - queryViewPivot(): \n");   
        while (rsPivot.next()) {
        	System.out.println(" - " + rsPivot.getString("student_name"));   
        }   
        
	    SqlRowSet rsUnpivot = dbOperation.queryViewUnpivot(); 
        System.out.println("\n - showDataFrom_dsB (mySchema2) - queryViewUnpivot(): \n");   
        while (rsUnpivot.next()) {
        	System.out.println(" - " + rsUnpivot.getString("student_name") + "  "  + rsUnpivot.getString("course"));   
        }           
	    
	}

}

   
