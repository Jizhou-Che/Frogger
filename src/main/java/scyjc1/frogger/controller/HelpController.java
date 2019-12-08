package scyjc1.frogger.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import scyjc1.frogger.Main;

/**
 * Controls behaviours and handles events on the help view.
 */
public class HelpController {
	@FXML
	private void initialize() {
	}

	/**
	 * Handles key-pressed events on the help view.
	 * This includes the scene switching to the home view.
	 *
	 * @param keyEvent the key-pressed event.
	 */
	@FXML
	private void keyPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			// Switch to home view.
			try {
				Parent homeLoader = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
				Scene homeScene = new Scene(homeLoader, 600, 800);
				homeScene.getRoot().requestFocus();
				Main.mainStage.setScene(homeScene);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
