package GUI;

import Controller.Ctrl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Model.Statements.IStmt;

public class MainExecutionWindow {
    private static Ctrl controller;

    public static void launch(IStmt program) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(MainExecutionWindow.class.getResource("/resources_gui/View.fxml"));
            AnchorPane root = loader.load();

            GUIController controller = loader.getController();
            controller.initializeExecution(program);

            Scene scene = new Scene(root);
            stage.setTitle("Program Execution");
            stage.setScene(scene);

            // Handle cleanup
            stage.setOnCloseRequest((WindowEvent event) -> Platform.exit());

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}