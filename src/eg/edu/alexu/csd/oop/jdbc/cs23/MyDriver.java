package eg.edu.alexu.csd.oop.jdbc.cs23;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import  java.lang.UnsupportedOperationException;
public class MyDriver implements Driver{
	
	//m3ana
	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		String path = info.getProperty("path");
		Connection con = new MyConnection(path);
		if(!acceptsURL(url)) {
			return null;
		}else {
		// throws exception ?
		return con ;
		}
	}
	
	//m3ana
	@Override
	public boolean acceptsURL(String url) throws SQLException {
		// lazem a3ml condition bel url walla la2? 
		return true;
	}
	//m3ana
	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		if(!acceptsURL(url)) {
			return null;
		}else {
		DriverPropertyInfo[] driverPropertyInfos = new DriverPropertyInfo[info.size()];
		ArrayList keys = (ArrayList) info.keySet();
		ArrayList values = (ArrayList) info.values();
		 int i=0;
	        for (Map.Entry<Object, Object> entry : info.entrySet()) {
	            driverPropertyInfos[i] = new DriverPropertyInfo(((String) entry.getKey()), ((String) entry.getValue()));
	            i++;
	        }
		return driverPropertyInfos;
		}
	}

	@Override
	public int getMajorVersion() {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public int getMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean jdbcCompliant() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}

}
