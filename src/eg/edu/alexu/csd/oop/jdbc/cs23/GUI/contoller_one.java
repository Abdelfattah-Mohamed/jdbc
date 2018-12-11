package eg.edu.alexu.csd.oop.jdbc.cs23.GUI;

import eg.edu.alexu.csd.oop.jdbc.cs23.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class contoller_one {

	@FXML
	MenuBar menu;
	@FXML
	TableView<String[]> table = new TableView<>();
	@FXML
	TextField input;
	@FXML
	MenuItem connection;
	@FXML
	MenuItem log;
	@FXML
	Button enter;
	@FXML
	Button addBatch;
	@FXML
	Button ex;
	@FXML
	Button clear;
	@FXML
	ListView list;
	@FXML
	Label lbl;

	//private Stage stage;
	private Connection myConnection;
	//private boolean ava = false;
	//private String query;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private Statement myStatement;

	@FXML
	void initialize() {
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//ava = true;
		input.setDisable(false);
	}

	public void settings( Connection connection) {
		this.myConnection = connection;
		//this.stage = stage;
	}

	public void newConnection(ActionEvent event) {
		// DirectoryChooser dc =new DirectoryChooser();
		// File f = dc.showDialog(stage);
		// if(f!=null) {
		// TextInputDialog textInputDialog = new TextInputDialog();
		// textInputDialog.setTitle("Enter Database Name:");
		// textInputDialog.setHeaderText("Enter Database Name:");
		// textInputDialog.showAndWait();
		// String databaseName = null;
		// if(textInputDialog.getResult() != null && textInputDialog.getResult()!= ""){
		// databaseName = textInputDialog.getResult();
		// }
		// Properties info = new Properties();
		// info.put("path", f.getAbsoluteFile());
		// myConnection = null;
		// try {
		// myConnection = (MyConnection) myDriver.connect("jdbc:xmldb://localhost",
		// info);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// Statement statement = null;
		// try {
		// statement = myConnection.createStatement();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// try {
		// statement.execute("CREATE DATABASE " + databaseName);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// try {
		// statement.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		//
		// }

	}

	public void typing(KeyEvent e) throws SQLException {
		// fillTable();
		if (input.getText().length() > 0) {
			addBatch.setDisable(false);
			enter.setDisable(false);
		} else {
			enter.setDisable(true);
			addBatch.setDisable(true);
		}
		if (list.getItems().size() > 0) {
			ex.setDisable(false);
			clear.setDisable(false);
		} else {
			ex.setDisable(true);
			clear.setDisable(true);
		}
	}

	public void en(ActionEvent event) {
		try {
			Splitter split = new Splitter();
			Statement x = ((MyStatement) myConnection.createStatement());
			if (split.QuerySplitter(input.getText()) != 2) {
				x.execute(input.getText());
			} else {
				resultSet = x.executeQuery(input.getText());
				metaData = resultSet.getMetaData();
				fillTable();
				// table.getItems();
			}
			x.close();
			input.setText("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addbatch(ActionEvent e) {
		list.getItems().add(input.getText());
		try {
			myStatement = ((MyStatement) myConnection.createStatement());
			myStatement.addBatch(input.getText());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		input.setText("");
	}

	public void exec(ActionEvent e) {
		try {
			myStatement.executeBatch();
			myStatement.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ObservableList<String> observableList = list.getItems();
		int size = observableList.size();
		while (!observableList.isEmpty()) {
			observableList.remove(0);
		}

	}

	public void del(ActionEvent e) throws SQLException {
		myStatement.clearBatch();
		myStatement.close();
		ObservableList<String> observableList = list.getItems();
		while (!observableList.isEmpty()) {
			list.getItems().remove(observableList.remove(0));
		}
	}

	public void fillTable() throws SQLException {
		int metaCount = metaData.getColumnCount();
		resultSet.absolute(0);
		int count = 0;
		while (!resultSet.isLast()) {
			count++;
			resultSet.next();
		}
		String[][] x = new String[count + 1][metaCount];
		for (int i = 0; i < metaCount; i++) {
			x[0][i] = metaData.getColumnName(i + 1);
		}
		resultSet.absolute(0);
		for (int j = 1; !resultSet.isLast(); j++) {
			resultSet.next();
			for (int i = 0; i < metaCount; i++) {
				x[j][i] = resultSet.getString(i + 1);
			}
		}

		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(x));
		data.remove(0);// remove titles from data
		// TableView<String[]> table = new TableView<>();
		for (int i = 0; i < x[0].length; i++) {
			TableColumn tc = new TableColumn(x[0][i]);
			final int colNo = i;
			tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc.setPrefWidth(1025 / metaCount);
			table.getColumns().add(tc);
		}
		table.setItems(data);
	}

	public void makeColumns(int count, TableView<Row> tableView) {
		for (int m = 0; m < count; m++) {
			TableColumn<Row, String> column = new TableColumn<>(Integer.toString(m));
			column.setCellValueFactory(param -> {
				// int index = Integer.parseInt(param.getTableColumn().getText());
				int index = param.getTableView().getColumns().indexOf(param.getTableColumn());
				List<Cell> cells = param.getValue().getCells();
				return new SimpleStringProperty(cells.size() > index ? cells.get(index).toString() : null);
			});
			tableView.getColumns().add(column);
		}
	}

	public int getMaxCells(List<Row> rows) {
		int max = 0;
		for (Row row : rows)
			max = Math.max(max, row.getCells().size());
		return max;
	}

	public List<Row> makeSampleData() {
		Random random = new Random();
		List<Row> rows = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			Row e = new Row();
			int jMax = random.nextInt(6); // from 0 to 5
			for (int j = 0; j <= jMax; j++) {
				e.getCells().add(new Cell(Long.toHexString(random.nextLong())));
			}
			rows.add(e);
		}
		return rows;
	}

	static class Row {
		private final List<Cell> list = new ArrayList<>();

		public List<Cell> getCells() {
			return list;
		}
	}

	static class Cell {
		private final String value;

		public Cell(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

}
