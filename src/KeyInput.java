import static java.awt.event.KeyEvent.VK_D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	Game game;
	Handler handler;
	public static boolean reset = false;
	private boolean backtoMenu = false;
	private boolean inControls = false;
	private boolean inCustomize = false;
	private boolean inMechanics = false;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	// Called when a key is pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {

			if (Game.State == Game.STATE.Game) {
				if (key == KeyEvent.VK_R)
					reset = true;

				// Movement (going up, down, left, right)
				if (!handler.isFocused()) {
					if (key == KeyEvent.VK_W)
						handler.setUp(true);
					if (key == KeyEvent.VK_S)
						handler.setDown(true);
					if (key == KeyEvent.VK_A)
						handler.setLeft(true);
					if (key == VK_D)
						handler.setRight(true);
				}
				
				// Shrinks and expands player on up, down keys
				if (Game.LEVEL != 3 && Game.LEVEL != 4 && Game.LEVEL != 2 && !handler.isFocused()) {
					if (key == KeyEvent.VK_UP)
						handler.setShrunk(false);
					if (key == KeyEvent.VK_DOWN)
						handler.setShrunk(true);
				}

				// Turns on focused ability
				if (!handler.isShrunk() && !handler.isDown() && !handler.isUp() && !handler.isRight()
						&& !handler.isLeft() && Player.canF == true) {
					if (key == KeyEvent.VK_SPACE)
						handler.setFocused(true);
				}
				
				// Pauses game
				if(key == KeyEvent.VK_P)
					Game.State = Game.STATE.Pause;
			}
		}

		// Allows player to move through menu using the arrow keys
		if (Game.State == Game.STATE.Menu) {
			if (key == KeyEvent.VK_UP)
				handler.setMode(handler.getMode() - 1);
			
			if (key == KeyEvent.VK_DOWN)
				handler.setMode(handler.getMode() + 1);
			if (handler.getMode() <= 0)
				handler.setMode(3);
			if (handler.getMode() >= 4)
				handler.setMode(1);

			if (key == KeyEvent.VK_ENTER && handler.getMode() == 1)
				Game.State = Game.STATE.Game;

			if (key == KeyEvent.VK_ENTER && handler.getMode() == 2) {
				Game.State = Game.STATE.Settings;
				handler.setSettingsMode(4);
			}

			if (key == KeyEvent.VK_ENTER && handler.getMode() == 3)
				System.exit(1);
		}

		if (Game.State == Game.STATE.Settings) {
			if (key == KeyEvent.VK_RIGHT)
				handler.setSettingsMode(handler.getSettingsMode() + 1);
			if (key == KeyEvent.VK_LEFT)
				handler.setSettingsMode(handler.getSettingsMode() - 1);
			if (key == KeyEvent.VK_DOWN)
				handler.setSettingsMode(4);
			if (handler.getSettingsMode() == 4) {
				if (key == KeyEvent.VK_UP)
					handler.setSettingsMode(2);
			}

			if (handler.getSettingsMode() <= 0)
				handler.setSettingsMode(4);
			if (handler.getSettingsMode() >= 5)
				handler.setSettingsMode(1);

			if (key == KeyEvent.VK_ENTER && handler.getSettingsMode() == 4)
				if (backtoMenu == true) {
					Game.State = Game.STATE.Menu;
				}
		}

		if (Game.State == Game.STATE.Controls) {
			if (key == KeyEvent.VK_ENTER && inControls == true) {
				Game.State = Game.STATE.Menu;
				inControls = false;
			}
		}

		if (Game.State == Game.STATE.Customize) {
			if (key == KeyEvent.VK_ENTER && inCustomize == true) {
				Game.State = Game.STATE.Menu;
				inCustomize = false;
			}
		}

		if (Game.State == Game.STATE.Mechanics) {
			if (key == KeyEvent.VK_ENTER && inMechanics == true) {
				Game.State = Game.STATE.Menu;
				inMechanics = false;
			}
		}

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	// Called when the key is released
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {

			// Turns off movements when key is released
			if (key == KeyEvent.VK_W)
				handler.setUp(false);
			if (key == KeyEvent.VK_S)
				handler.setDown(false);
			if (key == KeyEvent.VK_A)
				handler.setLeft(false);
			if (key == KeyEvent.VK_D)
				handler.setRight(false);
			if (key == KeyEvent.VK_SPACE)
				handler.setFocused(false);
		}

		if (Game.State == Game.STATE.Settings) {

			// Control Button
			if (key == KeyEvent.VK_ENTER && handler.getSettingsMode() == 1) {
				Game.State = Game.STATE.Controls;
			}

			// Customize Button
			if (key == KeyEvent.VK_ENTER && handler.getSettingsMode() == 2) {
				Game.State = Game.STATE.Customize;
			}

			// Mechanics Button
			if (key == KeyEvent.VK_ENTER && handler.getSettingsMode() == 3) {
				Game.State = Game.STATE.Mechanics;
			}
		}

		if (Game.State == Game.STATE.Settings) {
			if (key == KeyEvent.VK_ENTER && inControls == false) {
				Game.State = Game.STATE.Settings;
				inControls = true;
			}

			if (key == KeyEvent.VK_ENTER && inCustomize == false) {
				Game.State = Game.STATE.Settings;
				inCustomize = true;
			}

			if (key == KeyEvent.VK_ENTER && inMechanics == false) {
				Game.State = Game.STATE.Settings;
				inMechanics = true;
			}
		}

		if (Game.State == Game.STATE.Menu) {
			if (key == KeyEvent.VK_ENTER && backtoMenu == true) {
				backtoMenu = false;
			}

		}
	}
}
