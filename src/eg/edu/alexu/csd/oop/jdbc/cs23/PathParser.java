package eg.edu.alexu.csd.oop.jdbc.cs23;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathParser {
	
	public String Parser(String query,String path) {
		
		query = query.toLowerCase();
		Pattern pat = Pattern.compile("\\s*(drop|create)\\s*(database)\\s*(\\w)\\s*;?\\s*", Pattern.CASE_INSENSITIVE);
		Matcher match = pat.matcher(query);
		if(match.find()) {
		query=match.group(1)+match.group(2)+path+System.getProperty("file.separator")+match.group(3);
		}else {
			//TODO mmkn nst5dmha lw 3awzen n3rf ano me4 bymatch
			query = null;
		}
		return query;
		
	}
}
