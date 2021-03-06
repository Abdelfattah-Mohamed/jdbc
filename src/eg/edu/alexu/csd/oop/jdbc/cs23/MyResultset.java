package eg.edu.alexu.csd.oop.jdbc.cs23;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.lang.UnsupportedOperationException;
import java.util.logging.Level;

public class MyResultset implements ResultSet {

	private Object[][] result;
	private int row = 0;
	private boolean close = false;
	private Statement statement;
	private String[] columnNames;
	private String tableName;

	public MyResultset(Object[][] result, Statement statement, String[] columnNames, String tableName) {
		DBLogger.getInstance().log.log(Level.INFO, "ResultSet created Successfully");
		this.result = result;
		close = false;
		this.statement = statement;
		this.columnNames = columnNames;
		this.tableName = tableName;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
    public boolean next() throws SQLException {
        if (!isClosed()) {
            if (row < result.length) {
                DBLogger.getInstance().log.log(Level.INFO, "ResultSet curser moved forward");
                row++;
                return true;
            }
            DBLogger.getInstance().log.log(Level.INFO, "ResultSet curser can't moved forward");
            return false;
        }
        throw new SQLException("result set is closed");
    }

	@Override
	public void close() throws SQLException {
		DBLogger.getInstance().log.log(Level.INFO, "ResultSet closed Successfully");
		close = true;
	}

	@Override
	public boolean wasNull() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public String getString(int columnIndex) throws SQLException {

		if (!isClosed()) {
			if (columnIndex > columnNames.length || columnIndex < 1) {
				DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet Can't Get Column Value!");
				throw new SQLException("Can't Get Column Value!");
			}
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet got Column value Successfully");
			return (String) result[row - 1][columnIndex - 1].toString();
		}
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		if (!isClosed()) {
			if (columnIndex > columnNames.length || columnIndex < 1) {
				DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet Can't Get Column Value!");
				throw new SQLException("Can't Get Column Value!");
			}
			int x = Integer.parseInt(null);
			try {
				x = Integer.parseInt(((String) result[row - 1][columnIndex - 1]));
			} catch (Exception e) {
				DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet type isn't an Integer", e);
				throw new SQLException("type isn't an Integer");
			}
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet got Column value Successfully");
			return x;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Date getDate(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Time getTime(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		if (!isClosed()) {
			for (int i = 0; i < columnNames.length; i++) {
				if (columnLabel.equals(columnNames[i])) {
					DBLogger.getInstance().log.log(Level.INFO, "ResultSet got Column value Successfully");
					return (String) result[row - 1][i].toString();
				}
			}
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "Can't Get Column Value!");
		throw new SQLException("Can't Get Column Value!");

	}

	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public byte getByte(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public short getShort(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		if (!isClosed()) {
			for (int i = 0; i < columnNames.length; i++) {
				if (columnLabel.equals(columnNames[i])) {
					int x = 0;
					try {
						if (((String) result[row - 1][i]).equals("null")) {
							return 0;
						} else {
							x = Integer.parseInt(((String) result[row - 1][i]));
						}
					} catch (Exception e) {
						DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet type isn't an int", e);
						throw new SQLException();
					}
					DBLogger.getInstance().log.log(Level.INFO, "ResultSet got Column value Successfully");
					return x;
				}
			}
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet Can't Get Column Value!");
		throw new SQLException("Can't Get Column Value!");
	}

	@Override
	public long getLong(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public float getFloat(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public double getDouble(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public byte[] getBytes(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Date getDate(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Time getTime(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public InputStream getUnicodeStream(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public String getCursorName() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		if (!isClosed()) {
			MyResultSetMetaData rsm = new MyResultSetMetaData(result, columnNames, tableName);
			DBLogger.getInstance().log.log(Level.INFO, "ResultSetMetaData returned successfully");
			return rsm;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		if (!isClosed()) {
			if (columnIndex > columnNames.length || columnIndex < 1) {
				DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet Can't Get Column Value!");
				throw new SQLException("Can't Get Column Value!");
			}
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet got column value successfully");
			return result[row - 1][columnIndex - 1];
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public Object getObject(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		if (!isClosed()) {
			for (int i = 0; i < columnNames.length; i++) {
				if (columnLabel.equals(columnNames[i])) {
					DBLogger.getInstance().log.log(Level.INFO, "ResultSet got column value successfully");
					return i + 1;
				}
			}
			DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet Can't Get Column Value!");
			throw new SQLException("Can't Get Column Index!");
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet isBeforeLast : " + (row < 1));
			return row < 1;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public boolean isAfterLast() throws SQLException {

		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet isAfterLast : " + (row > result.length));
			return row > result.length;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public boolean isFirst() throws SQLException {
		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet isFirst : " + (row == 1));
			return row == 1;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public boolean isLast() throws SQLException {
		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet isLast : " + (row == result.length));
			return row == result.length;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public void beforeFirst() throws SQLException {

		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet set beforeFirst : ");
			row = 0;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public void afterLast() throws SQLException {

		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet set afterLast : ");
			row = result.length + 1;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public boolean first() throws SQLException {

		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet returned first");
			return absolute(1);
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public boolean last() throws SQLException {

		if (!isClosed()) {
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet returned last");
			return absolute(-1);

		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public int getRow() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean absolute(int row) throws SQLException {

		if (!isClosed()) {
			if (row >= result.length * -1 && row <= result.length && row != 0) {
				DBLogger.getInstance().log.log(Level.INFO, "ResultSet cursor set to row: " + row);
				if (row < 0) {
					this.row = row + result.length+1;
				} else {
					this.row = row;
				}

				return true;
			} else {
				if (row > 0) {
					this.row = result.length+1;
				} else {
					this.row = 0;
				}
			}
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet can't find row: " + row);
			return false;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override
	public boolean relative(int rows) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isClosed() throws SQLException {
		return close;
	}

	@Override
	public boolean previous() throws SQLException {
		if (!isClosed()) {
			if (row >= 1) {
				row--;
				DBLogger.getInstance().log.log(Level.INFO, "ResultSet moved backwards");
				return true;
			}
			DBLogger.getInstance().log.log(Level.INFO, "ResultSet can't move backwards");
			return false;
		}
		DBLogger.getInstance().log.log(Level.SEVERE, "ResultSet is closed");
		throw new SQLException("ResultSet is Closed");

	}

	@Override

	public Statement getStatement() throws SQLException {
		DBLogger.getInstance().log.log(Level.INFO, "ResultSet returned it's statement successfully");
		return statement;
	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
		return null;
	}

	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Array getArray(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
		return null;
	}

	@Override
	public Ref getRef(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public Blob getBlob(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public Clob getClob(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public Array getArray(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		return null;
	}

	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException {
		return null;
	}

	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		return null;
	}

	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException {
		return null;
	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		return null;
	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
		return null;
	}

	@Override
	public URL getURL(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public URL getURL(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public void updateRef(int columnIndex, Ref x) throws SQLException {

	}

	@Override
	public void updateRef(String columnLabel, Ref x) throws SQLException {

	}

	@Override
	public void updateBlob(int columnIndex, Blob x) throws SQLException {

	}

	@Override
	public void updateBlob(String columnLabel, Blob x) throws SQLException {

	}

	@Override
	public void updateClob(int columnIndex, Clob x) throws SQLException {

	}

	@Override
	public void updateClob(String columnLabel, Clob x) throws SQLException {

	}

	@Override
	public void updateArray(int columnIndex, Array x) throws SQLException {

	}

	@Override
	public void updateArray(String columnLabel, Array x) throws SQLException {

	}

	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException {

	}

	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException {

	}

	@Override
	public int getHoldability() throws SQLException {
		return 0;
	}

	@Override
	public void updateNString(int columnIndex, String nString) throws SQLException {

	}

	@Override
	public void updateNString(String columnLabel, String nString) throws SQLException {

	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {

	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {

	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {

	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {

	}

	@Override
	public String getNString(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public String getNString(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		return null;
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {

	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {

	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {

	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {

	}

	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException {

	}

	@Override
	public void updateClob(String columnLabel, Reader reader) throws SQLException {

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException {

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader) throws SQLException {

	}

	@Override
	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		return null;
	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		return null;
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getType() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getConcurrency() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean rowUpdated() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean rowInserted() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean rowDeleted() throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateNull(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateByte(int columnIndex, byte x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateShort(int columnIndex, short x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateInt(int columnIndex, int x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateLong(int columnIndex, long x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateFloat(int columnIndex, float x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateDouble(int columnIndex, double x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateString(int columnIndex, String x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateDate(int columnIndex, Date x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateTime(int columnIndex, Time x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateObject(int columnIndex, Object x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateNull(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBoolean(String columnLabel, boolean x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateByte(String columnLabel, byte x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateShort(String columnLabel, short x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateInt(String columnLabel, int x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateLong(String columnLabel, long x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateFloat(String columnLabel, float x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateString(String columnLabel, String x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBytes(String columnLabel, byte[] x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateTime(String columnLabel, Time x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {

	}

	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {

	}

	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException {

	}

	@Override
	public void insertRow() throws SQLException {

	}

	@Override
	public void updateRow() throws SQLException {

	}

	@Override
	public void deleteRow() throws SQLException {

	}

	@Override
	public void refreshRow() throws SQLException {

	}

	@Override
	public void cancelRowUpdates() throws SQLException {

	}

	@Override
	public void moveToInsertRow() throws SQLException {

	}

	@Override
	public void moveToCurrentRow() throws SQLException {

	}

}
