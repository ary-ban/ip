package larry.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import larry.LarryCore;

public class Main extends Application {

    private LarryCore core;

    @Override
    public void start(Stage stage) {
        core = new LarryCore();

        // ===== UI nodes =====
        TextArea dialog = new TextArea();
        dialog.setEditable(false);
        dialog.setWrapText(true);

        ScrollPane scroll = new ScrollPane(dialog);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        TextField input = new TextField();
        input.setPromptText("Type a command (e.g., todo read book)");
        Button send = new Button("Send");

        HBox bottom = new HBox(8, input, send);
        bottom.setPadding(new Insets(8));

        BorderPane root = new BorderPane();
        root.setCenter(scroll);
        root.setBottom(bottom);
        BorderPane.setMargin(scroll, new Insets(8, 8, 0, 8));

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Larry");
        stage.setScene(scene);
        stage.show();

        // greet once
        append(dialog, " Hello! I'm Larry\n What can I do for you?");

        // wire interactions
        Runnable handle = () -> {
            String text = input.getText();
            if (text == null || text.isBlank()) {
                return;
            }
            append(dialog, "> " + text);
            String reply = core.getResponse(text);
            if (!reply.isBlank()) {
                append(dialog, reply);
            }
            input.clear();
            if (core.shouldExit()) {
                stage.close();
            }
            dialog.setScrollTop(Double.MAX_VALUE); // auto-scroll down
        };

        send.setOnAction(e -> handle.run());
        input.setOnAction(e -> handle.run());
    }

    private static void append(TextArea dialog, String msg) {
        if (dialog.getText().isEmpty()) {
            dialog.setText(msg);
        } else {
            dialog.appendText("\n" + msg);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
