package pompompurin.gui;

import java.io.IOException;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A dialog box that displays a text bubble.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, double size) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load DialogBox.fxml", e);
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setFitWidth(size);
        displayPicture.setFitHeight(size);
        displayPicture.setPreserveRatio(true);
    }

    /**
     * Creates a dialog box for user messages.
     *
     * @param text The message text.
     * @return A styled DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img, double size) {
        DialogBox box = new DialogBox(text.toLowerCase(Locale.ROOT), img, size);
        box.getStyleClass().add("user-dialog");
        box.setAlignment(Pos.TOP_RIGHT);
        box.getChildren().setAll(box.dialog, box.displayPicture);
        return box;
    }

    /**
     * Creates a dialog box for bot messages.
     *
     * @param text The message text.
     * @return A styled DialogBox.
     */
    public static DialogBox getBotDialog(String text, Image img, double size) {
        DialogBox box = new DialogBox(text, img, size);
        box.getStyleClass().add("bot-dialog");
        return box;
    }
}
