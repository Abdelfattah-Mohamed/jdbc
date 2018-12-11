package eg.edu.alexu.csd.oop.jdbc.cs23.GUI;

import eg.edu.alexu.csd.oop.jdbc.cs23.MyConnection;
import eg.edu.alexu.csd.oop.jdbc.cs23.MyDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Driver driver = new MyDriver();
        Connection connection = null;
        Properties info = new Properties();
        File dbDir = new File("gui_databases");
        info.put("path", dbDir.getAbsoluteFile());
        try {
            connection = (MyConnection) driver.connect("jdbc:xmldb://localhost", info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////////////////////
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        /////////////////////////////////////////////////////////
        contoller_one c = fxmlLoader.getController();
        c.settings( (MyConnection) connection);
        /////////////////////////////////////////////////////////
        primaryStage.setTitle("JDBC");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 1067, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
