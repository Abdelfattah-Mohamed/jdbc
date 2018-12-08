package eg.edu.alexu.csd.oop.jdbc.cs23;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs04.MyDatabase;
import eg.edu.alexu.csd.oop.db.cs04.XML.DTDGenerator;
import eg.edu.alexu.csd.oop.db.cs04.XML.SQLOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

public class MyStatement implements Statement {

	private ArrayList<String> batch = new ArrayList<>();
	// TODO lazem n3ml l depenency injection hena ,,
	private Database database;
	private Connection connection;
	private ResultSet currentResultSet;
	private String path;
	private int timeLimit;
	// TODO hn3delha bel path walla la2
	private boolean closed;

	// TODO m7tagen n3del 7war l 2 constructors da lama nersa 3ala tre2a
	MyStatement(Connection connection) {
		this.connection = connection;
	}

	MyStatement(Connection connection, String path, Database mdb) {
		this.connection = connection;
		this.path = path;
		this.database = mdb;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addBatch(String arg0) throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		batch.add(arg0);

	}

	@Override
	public void cancel() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void clearBatch() throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		batch = new ArrayList<>();

	}

	@Override
	public void clearWarnings() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void close() throws SQLException {
		// TODO ?
		this.closed = true;
	}

	@Override
	public void closeOnCompletion() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0) throws SQLException {
		if (!closed) {
			PathParser pp = new PathParser();
			arg0 = pp.Parser(arg0, this.path);
			String[] str = arg0.split(" ");
			if (str[0].toLowerCase().equals("insert") || str[0].toLowerCase().equals("update")
					|| str[0].toLowerCase().equals("delete")) {
				int x = executeUpdate(arg0);
				if (x > 0) {
					return true;
				} else {
					return false;
				}
			} else if (str[0].toLowerCase().equals("select")) {
				if (executeQuery(arg0).next() || executeQuery(arg0).previous()) {
					return true;
				} else {
					return false;
				}
			}
			return database.executeStructureQuery(arg0);
		}
		throw new SQLException("The statement has been closed.");
	}

	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		Splitter split = new Splitter();
		int num = 0;
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		int[] updates = new int[batch.size()];
		int i = 0;
		for (final String batch : batch) {
			num = split.QuerySplitter(batch);
			if (num == 2) {// it's not create nor drop) {
				updates[i] = executeUpdate(batch);
			} else if (num == 1) {
				// TODO mmkn a5leh yrg3 0 l true w arg3 -1 lw false 34an atb3 ?
				execute(batch);
				updates[i] = 0;
			} else if (num == 3) {
				// TODO elmfrod a5lyh ytb3 l 7agat y3ny walla eh ?
				executeQuery(batch);
				updates[i] = 0;
			}
			i++;
		}
		return updates;

	}

	@Override
	public ResultSet executeQuery(String arg0) throws SQLException {
		// if(!closed){
		// throw ?
		// }
		Object[][] table = database.executeQuery(arg0);
		String x = SQLOrder.getInstance().getTable_head();
		x = x.replace(".xml", ".dtd");
		String[] strings = DTDGenerator.getDTDColumns(x);
		// String tableName = database.
		// currentResultSet =new Resultset(table,columns,tableName,this);

		MyResultset rs = new MyResultset();
		rs.MyResultSet(table, this, strings);
		return rs;
	}

	@Override
	public int executeUpdate(String arg0) throws SQLException {
		if (!closed) {
			return database.executeUpdateQuery(arg0);
		}

		throw new SQLException("The statement has been closed.");
	}

	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Connection getConnection() throws SQLException {

		return this.connection;
	}

	@Override
	public int getFetchDirection() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getFetchSize() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxRows() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getQueryTimeout() throws SQLException {

		return timeLimit;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetHoldability() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetType() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getUpdateCount() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isClosed() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPoolable() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setCursorName(String arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setEscapeProcessing(boolean arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxFieldSize(int arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxRows(int arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setQueryTimeout(int arg0) throws SQLException {
		this.timeLimit = arg0;

	}

}
