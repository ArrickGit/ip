package pompompurin.gui;

import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pompompurin.ui.Pompompurin;

/**
 * Controller for the main chat window.
 */
public class MainWindow {
    private static final double BOT_AVATAR_SIZE = 100;
    private static final double USER_AVATAR_SIZE = 150;

    @FXML
    private StackPane rootPane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;
    private final Pompompurin pom = new Pompompurin("./data/pompompurin.txt");
    private final Image botImage = new Image(Paths.get("image/pompompurin_transparent.png").toUri().toString());
    private final Image userImage = new Image(Paths.get("image/optimus_transparent.png").toUri().toString());
    private final Image bgImage = new Image(Paths.get("image/background.png").toUri().toString());

    /**
     * Initializes the chat window with a welcome message.
     */
    @FXML
    public void initialize() {
        backgroundImage.setImage(bgImage);
        backgroundImage.setOpacity(0.25);
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setMouseTransparent(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        appendBotText(pom.getWelcomeMessage());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        appendUserText(input);
        String response = pom.getResponse(input);
        appendBotText(response);

        userInput.clear();
        if (pom.isExitCommand(input)) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            Platform.exit();
        }
    }

    private void appendUserText(String text) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(text, userImage, USER_AVATAR_SIZE));
    }

    private void appendBotText(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getBotDialog(text, botImage, BOT_AVATAR_SIZE));
    }
}
