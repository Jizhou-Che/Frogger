package frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import frogger.Main;

/**
 * Controls behaviours and handles events on the Help view.
 */
public class HelpController {
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
			Main.switchScene(0);
		}
	}
}
