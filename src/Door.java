import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Door extends GameObject {

	private Handler handler;
	private BufferedImage door;
	private BufferedImage LockedDoor;
	public static boolean canPass = false;

	public Door(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;

		door = ss.grabImage(3, 1, 32, 64); // Setting graphcis
		LockedDoor = ss.grabImage(7, 1, 32, 64);
	}

	public void update() {

	}

	public void render(Graphics g) {
		if (Game.LEVEL == 1 || Game.LEVEL == 2 || Game.LEVEL == 3 || Game.LEVEL == 4) { // There is no key needed in these levels
			g.drawImage(door, x, y, null);
			canPass = true;
		}

		// Image of door is unlocked if the player has a key
		if (handler.isHKey()) {
			g.drawImage(door, x, y, null);
			canPass = true;
		}
		
		// Image of door is locked if the player does not have a key
		if (!handler.isHKey() && Game.LEVEL != 1 && Game.LEVEL != 2 && Game.LEVEL != 3 && Game.LEVEL != 4) {
			g.drawImage(LockedDoor, x, y, null);
			canPass = false;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 64);
	}

}
