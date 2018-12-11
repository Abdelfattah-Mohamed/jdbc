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

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs04.MyDatabase;

import java.io.File;
import  java.lang.UnsupportedOperationException;
public class MyDriver implements Driver{

	 
	//m3ana
	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if(url == null) {
			throw new SQLException();
		}
		Database mdb = new MyDatabase();
		DBLogger.getInstance().log.info("Attempting to connect...");
		File file = (File)info.get("path");
		String path = file.getAbsolutePath();
		Connection con = new MyConnection(path , mdb);
		if(!acceptsURL(url)) {
			return null;
		}else {
			return con ;
		}
		
	}

	//m3ana
	@Override
	public boolean acceptsURL(String url) throws SQLException {
		// lazem a3ml condition bel url walla la2?
		// bt throw l exception bta3 l DBMS 3ady
		if(url == null) {
			throw new SQLException();
		}
		DBLogger.getInstance().log.info("Access to " + url + "has no security.");
		return true;
	}
	//m3ana
	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		DBLogger.getInstance().log.info("getting Property Info...");
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
