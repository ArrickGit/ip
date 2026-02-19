package pompompurin.gui;

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
    private final Image botImage = new Image(
            this.getClass().getResourceAsStream("/images/pompompurin_transparent.png"));
    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/optimus_transparent.png"));
    private final Image bgImage = new Image(
            this.getClass().getResourceAsStream("/images/background.png"));

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
        // Detect error messages and style them differently
        if (isErrorMessage(text)) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(text, botImage, BOT_AVATAR_SIZE));
        } else {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(text, botImage, BOT_AVATAR_SIZE));
        }
    }

    /**
     * Checks if a message is an error message.
     *
     * @param text The message text.
     * @return True if the message is an error.
     */
    private boolean isErrorMessage(String text) {
        return text.contains("OOPS") || text.startsWith("Error")
                || text.startsWith("Invalid") || text.contains("exception");
    }
}
