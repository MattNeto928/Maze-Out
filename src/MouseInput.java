import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	Handler handler;

	public MouseInput(Handler handler) {
		this.handler = handler;
	}

	
	// Premade methods using MouseListener
	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	// Allows player to click on buttons using mouse coordinates
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.State == Game.STATE.Menu) {
			// Play Button
			if (mx >= 350 && mx <= 600) {
				if (my >= 175 && my <= 275) {
					Game.State = Game.STATE.Game;
				}
			}

			// Help Button
			if (mx >= 350 && mx <= 600) {
				if (my >= 300 && my <= 400) {
					Game.State = Game.STATE.Settings;
				}

				// Quit Button
				if (mx >= 350 && mx <= 600) {
					if (my >= 425 && my <= 525) {
						System.exit(1);
					}
				}
			}
		}
		////////////////////////////////
		// Help Sections
		if (Game.State == Game.STATE.Settings || Game.State == Game.STATE.Controls || Game.State == Game.STATE.Customize
				|| Game.State == Game.STATE.Mechanics) {
			// Back Button
			if (mx >= 375 && mx <= 575) {
				if (my >= 425 && my <= 525) {
					Game.State = Game.STATE.Menu;
				}
			}
		}
		if (Game.State == Game.STATE.Settings) {
			// Control Button
			if (mx >= 50 && mx <= 250) {
				if (my >= 200 && my <= 300) {
					Game.State = Game.STATE.Controls;
				}
			}

			// Customize Button
			if (mx >= 375 && mx <= 575) {
				if (my >= 200 && my <= 300) {
					Game.State = Game.STATE.Customize;
				}
			}

			// Mechanics Button
			if (mx >= 700 && mx <= 900) {
				if (my >= 200 && my <= 300) {
					Game.State = Game.STATE.Mechanics;
				}
			}
		}
		// Change Character
		if (Game.State == Game.STATE.Customize) {

			// Original
			if (mx >= 805 && mx <= 915) {
				if (my >= 135 && my <= 245) {
					handler.setSelected(1);
				}
			}

			// Creeper
			if (Game.creepLocked == false) {
				if (mx >= 805 && mx <= 915) {
					if (my >= 285 && my <= 395) {
						handler.setSelected(2);
					}
				}
			}

			// Pete
			if (Game.peteLocked == false) {
				if (mx >= 805 && mx <= 915) {
					if (my >= 430 && my <= 540) {
						handler.setSelected(3);
					}
				}
			}
			
			if(handler.coins >= 5) {
				if (mx >= 50 && mx <= 150) {
					if (my >= 260 && my <= 360) {
						Game.creepLocked = false;
						handler.coins -= 5;
					}
				}
			}
			
			if(handler.coins >= 10) {
				if (mx >= 50 && mx <= 150) {
					if (my >= 410 && my <= 510) {
						Game.peteLocked = false;
						handler.coins -= 10;
					}
				}
			}
		}

		if (Game.State == Game.STATE.Pause) {
			if (mx >= 385 && mx <= 585) {
				if (my >= 175 && my <= 275) {
					Game.State = Game.STATE.Game;
				}
			}

			if (mx >= 385 && mx <= 585) {
				if (my >= 375 && my <= 475) {
					Game.State = Game.STATE.Menu;
				}
			}
		}
		
		if(Game.LEVEL > 15) {
			//Game Over
			if (mx >= 385 && mx <= 585) {
				if (my >= 375 && my <= 475) {
					Game.State = Game.STATE.Menu;
				}
			}
		}

		////////////////////////////////
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
