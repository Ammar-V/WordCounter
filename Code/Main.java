package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		Scanner read = null;
		try {
			read = new Scanner(new File("w0rdcOu73r.txt"));
		} catch (FileNotFoundException e1) {
			File file = new File("w0rdcOu73r.txt");
		}
		String data = "";
		try {
			data = read.nextLine();
			read.close();
		} catch (Exception e) {
		}

		primaryStage.setTitle("Word Counter");
		BorderPane root = new BorderPane();

		Label words = new Label("Words: 0");
		words.setPadding(new Insets(5));

		Label chars = new Label("Characters: 0");
		chars.setPadding(new Insets(5));

		TextArea area = new TextArea();
		area.setWrapText(true);
		area.setPromptText("Start typing to count.");
		area.textProperty().addListener(e -> {
			String text = area.getText();
			Scanner scan = new Scanner(text);
			int wordCount = 0;
			while (scan.hasNext()) {
				scan.next();
				wordCount++;
			}
			scan.close();
			words.setText("Words: " + wordCount);
			chars.setText("Characters: " + text.length());
		});
		area.setOnMouseClicked(c -> {
			if (area.getSelectedText().length() != 0) {
				String text = area.getSelectedText();
				Scanner scan = new Scanner(text);
				int wordCount = 0;
				while (scan.hasNext()) {
					scan.next();
					wordCount++;
				}
				scan.close();
				words.setText("Words: " + wordCount);
				chars.setText("Characters: " + text.length());
			} else {
				String text = area.getText();
				Scanner scan = new Scanner(text);
				int wordCount = 0;
				while (scan.hasNext()) {
					scan.next();
					wordCount++;
				}
				scan.close();
				words.setText("Words: " + wordCount);
				chars.setText("Characters: " + text.length());
			}
		});
		area.setText(data);

		root.setCenter(area);
		primaryStage.setOnCloseRequest(a -> {
			try {
				PrintWriter write = new PrintWriter(new File("w0rdcOu73r.txt"));
				write.println(area.getText().replaceAll("\n", ""));
				write.close();
			} catch (FileNotFoundException e) {
			}
		});

		try {
			HBox bottom = new HBox(20);
			bottom.setPadding(new Insets(10, 10, 10, 10));

			Button clear = new Button("Clear");
			clear.setMinWidth(70);
			clear.setTextAlignment(TextAlignment.CENTER);
			clear.setOnAction(e -> {
				area.clear();
			});

			bottom.getChildren().addAll(clear, words, chars);
			root.setBottom(bottom);

			Scene scene = new Scene(root, 400, 400);
			scene.setFill(Paint.valueOf("#2a2b2a"));
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(400);
			primaryStage.setScene(scene);
			primaryStage.show();

			ClassLoader load = Thread.currentThread().getContextClassLoader();
			primaryStage.getIcons().add(new Image(load.getResourceAsStream("keyboarding.png")));

			root.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
