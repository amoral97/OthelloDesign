import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene1 = new Scene(root);

		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
