import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TitlePageController {
	@FXML
	public Button button1;

	public void changeMainScene(ActionEvent event) throws IOException {
		Parent sceneParent = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene1 = new Scene(sceneParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene1);
		window.show();
	}
}
