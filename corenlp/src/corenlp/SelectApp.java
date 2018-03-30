package corenlp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SelectApp {
	
	public static String verbe;
	public static String [] tab = new String [100];
	public BasicPipelineExample test;
 
	public SelectApp(BasicPipelineExample test) {
		this.test = test;
		//tab = test.tabverb;
	}
	
    public static void main(String[] args) {

    }
    
    /**
     * select all rows in the warehouses table
     */
    public void selectAll(String verbe){
    	String url = "jdbc:sqlite:/home/leo/eclipse-workspace/corenlp/corenlp";

    	verbe = "'" + verbe + "'";
    	
		String sql = "SELECT name FROM verb WHERE verb = " + verbe + ";";


		try (
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt  = conn.prepareStatement(sql)){
//				int i =0;
//				System.out.println(tab[i]);
//			while(i!=tab.length) {
//				verbe = tab[i];
//				// set the value
				//stmt.setString(1,verbe);
				
				ResultSet rs    = pstmt.executeQuery();
				
				// loop through the result set
				while (rs.next()) {
					System.out.println("Le verbe " + verbe + " correspond à la commande " + rs.getString("name") + "\t");
				}
				//i++;
			//} 
		}
		
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

    
   
    /**
     * @param args the command line arguments
     */

 
}
