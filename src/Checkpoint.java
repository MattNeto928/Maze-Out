import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Checkpoint extends GameObject {

	private BufferedImage checkpoint_image = null;

	public Checkpoint(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		checkpoint_image = ss.grabImage(10, 1, 32, 32);
	}

	public void update() {

	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawImage(checkpoint_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 15, y + 15, 2, 2);
	}

}
