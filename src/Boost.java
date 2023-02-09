import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Boost extends GameObject {

	private BufferedImage boostU;

	public Boost(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		boostU = ss.grabImage(5, 1, 32, 32); // Settings graphics
	}

	public void update() {

	}

	public void render(Graphics g) {
		g.drawImage(boostU, x, y, null); // Drawing graphics
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 5, y + 1, 21, 30);
	}

}
