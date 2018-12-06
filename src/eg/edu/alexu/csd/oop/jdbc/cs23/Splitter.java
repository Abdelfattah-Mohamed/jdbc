package eg.edu.alexu.csd.oop.jdbc.cs23;

public class Splitter {

	public int QuerySplitter(String query) {
		int result =0;
		query = query.toLowerCase();
		if(query.contains("create") || query.contains("drop")) {
			result=1;
		}else if(query.contains("select")) {
			result=2;
		}else if(query.contains("update") || query.contains("insert") || query.contains("delete")) {
			result=3;
		}
		
		return result;
	}
}
