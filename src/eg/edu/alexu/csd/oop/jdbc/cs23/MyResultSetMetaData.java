package eg.edu.alexu.csd.oop.jdbc.cs23;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import eg.edu.alexu.csd.oop.db.cs04.XML.DTDGenerator;

public class MyResultSetMetaData implements ResultSetMetaData {

	private MyResultset rs = new MyResultset();
	private Object[][] result;
	private String[] columnNames;
	private Statement statement;

	public MyResultSetMetaData(MyResultset myResultset, Object[][] result, String[] columnNames, Statement statement) {
		rs = myResultset;
		this.result = result;
		this.columnNames = columnNames;
		this.statement = statement;
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

		return result[0].length;
	}

	@Override
	public int getColumnDisplaySize(int arg0) throws SQLException {

		throw new UnsupportedOperationException();

	}

	@Override
	public String getColumnLabel(int arg0) throws SQLException {

		return columnNames[rs.getInt(arg0)];
	}

	@Override
	public String getColumnName(int arg0) throws SQLException {

		return columnNames[rs.getInt(arg0)];
	}

	@Override
	public int getColumnType(int arg0) throws SQLException {
		return colType(result, arg0);
	}

	private static int colType(Object[][] given, int colNo) {
		if ((given[1][colNo].toString().contains("\'"))) {
			return Types.VARCHAR;
		} else if (!(given[1][colNo].toString().contains("\'"))) {
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
		// TODO Auto-generated method stub
		/* After edit the dbms interface */
		return null;
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
