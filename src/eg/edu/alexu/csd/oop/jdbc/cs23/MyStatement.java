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
	private DBLogger logger;

	// TODO m7tagen n3del 7war l 2 constructors da lama nersa 3ala tre2a
	MyStatement(Connection connection) {
		logger = DBLogger.getInstance();
		logger.log.info("Statement generated.");
		this.connection = connection;
	}

	MyStatement(Connection connection, String path, Database mdb) {
		logger = DBLogger.getInstance();
		logger.log.info("Statement generated..");
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
			logger.log.warning("Commands unsupported.");
			throw new SQLException("The statement has been closed.");
		}
		logger.log.info("Adding accepted batch of commands..");
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
		logger.log.warning("Clearing batch of commands.");
		batch = new ArrayList<>();

	}

	@Override
	public void clearWarnings() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void close() throws SQLException {
		logger.log.warning("Closing connection to Statement!");
        currentResultSet=null;
		this.closed = true;
	}

	@Override
	public void closeOnCompletion() throws SQLException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0) throws SQLException {
		if (!closed) {
			logger.log.info("Executing command..");
			arg0 = arg0.toLowerCase();
			Splitter split = new Splitter();
			int num = split.QuerySplitter(arg0);
			if (num == 3) {
				int x = this.executeUpdate(arg0);
				if (x > 0) {
					return true;
				} else {
					return false;
				}
			} else if (num == 2) {
				if (executeQuery(arg0).next() || executeQuery(arg0).previous()) {
					logger.log.info("Generating result of select query..");
					return true;
				} else {
					return false;
				}
			} else if (num == 1) {
				PathParser pp = new PathParser();
				arg0 = pp.Parser(arg0, this.path);
				this.path = /* "dbs"+System.getProperty("file.separator")+ */pp.newPath();
				return database.executeStructureQuery(arg0);
			} else {// = 0
				throw new SQLException("Syntax Error.");
			}
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
		logger.log.info("Executing batch of commands..");
		int[] updates = new int[batch.size()];
		int i = 0;
		for (final String batch : batch) {
			num = split.QuerySplitter(batch);
			if (num == 2) {// it's not create nor drop) {
				updates[i] = executeUpdate(batch);
			} else if (num == 1) {
				execute(batch);
				updates[i] = 0;
			} else if (num == 3) {
				executeQuery(batch);
				updates[i] = 0;
			} else {// = 0 hal da sa7 keda wlla eh ? 34an mn8er exception y3ny.
				System.out.println("Syntax Error.");
			}
			i++;
		}
		return updates;

	}

	@Override
	public ResultSet executeQuery(String arg0) throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		logger.log.info("Fetching ResultSet data..");
		Object[][] table = database.executeQuery(arg0);
		Database db = new MyDatabase();
		String[] strings = db.getSelectedColoumnsNames();
		PathParser pp = new PathParser();
		String tableName = pp.getTableName(arg0);
		MyResultset rs = new MyResultset(table, this, strings, tableName);
		return rs;
	}

	@Override
	public int executeUpdate(String arg0) throws SQLException {
		if (!closed) {
			logger.log.info("Executing given update query..");
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
        if (closed) {
            throw new SQLException("The statement has been closed.");
        }
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
		logger.log.warning("Setting query timeout to " + arg0 + " seconds");
		this.timeLimit = arg0;

	}

}
