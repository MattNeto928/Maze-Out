import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Key extends GameObject {

	private Handler handler;
	private BufferedImage key;

	public Key(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;

		key = ss.grabImage(8, 1, 32, 32);
	}

	public void update() {
		collision();
	}

	public void render(Graphics g) {
		g.drawImage(key, x, y, null);
	}

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this); // Destroys objects on frame
					handler.setHKey(true); // Player not has the key; door is unlocked
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
