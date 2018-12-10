package eg.edu.alexu.csd.oop.jdbc.cs23;

public class Splitter {

	public static int QuerySplitter(String query) {
		int result = 0;
		query = query.toLowerCase();
		query = query.trim();
		String[] lines = query.split(" ");
		query = lines[0];
		if (query.equals("create") || query.equals("drop")) {
			result = 1;
		} else if (query.equals("select")) {
			result = 2;
		} else if (query.equals("update") || query.equals("insert") || query.equals("delete")) {
			result = 3;
		}

		return result;
	}
}
