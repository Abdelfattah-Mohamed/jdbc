package eg.edu.alexu.csd.oop.jdbc.cs23;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class MyResultSetMetaData implements ResultSetMetaData {

	private Object[][] result;
	private String[] columnNames;
	private String tableName;

	public MyResultSetMetaData(Object[][] result, String[] columnNames, String tableName) {

		this.result = result;
		this.columnNames = columnNames;
		this.tableName = tableName;
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
	public String getCatalogName(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public String getColumnClassName(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public int getColumnCount() throws SQLException {
		// TODO Auto-generated method stub
		return columnNames.length;

	}

	@Override
	public int getColumnDisplaySize(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public String getColumnLabel(int arg0) throws SQLException {
		if (arg0 <= columnNames.length && arg0 > 0) {
			return columnNames[arg0];
		}
		throw new SQLException("Out of boundaries");
	}

	@Override
	public String getColumnName(int arg0) throws SQLException {
		if (arg0 <= columnNames.length && arg0 > 0) {
			return columnNames[arg0 - 1];
		}
		throw new SQLException("Out of boundaries");
	}

	@Override
	public int getColumnType(int arg0) throws SQLException {
		if (result != null) {
			return colType(result, arg0);
		}
		throw new SQLException();
	}

	private static int colType(Object[][] given, int colNo) {
		if ((given[0][colNo - 1].toString().contains("\'"))) {
			return Types.VARCHAR;
		} else if (!(given[0][colNo - 1].toString().contains("\'"))) {
			return Types.INTEGER;
		}
		return 0;
	}

	@Override
	public String getColumnTypeName(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public int getPrecision(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public int getScale(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public String getSchemaName(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public String getTableName(int arg0) throws SQLException {
		return tableName;
	}

	@Override
	public boolean isAutoIncrement(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isCaseSensitive(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isCurrency(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isDefinitelyWritable(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public int isNullable(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isReadOnly(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isSearchable(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isSigned(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public boolean isWritable(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

}
