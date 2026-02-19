package pompompurin.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application class.
 */
public class GuiApp extends Application {
    /**
     * Starts the JavaFX application.
     *
     * @param stage The primary stage.
     * @throws Exception If the FXML fails to load.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiApp.class.getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        scene.getStylesheets().add(GuiApp.class.getResource("/view/Style.css").toExternalForm());
        stage.setTitle("Pompompurin");
        stage.setMinWidth(400);
        stage.setMinHeight(500);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
}
