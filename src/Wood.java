import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Wood extends GameObject {

	Handler handler;
	Player player;
	Random r = new Random();

	private int barrier;
	private BufferedImage wood;

	public Wood(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;

		wood = ss.grabImage(8, 2, 32, 32);
	}

	public void update() {
		x += velX;
		y += velY;

		// Moves wood upon focus ability use
		if (handler.isFocused() && Player.canF == true) {
			x += r.nextInt(11) - 5;
			y += r.nextInt(11) - 5;
		}
	}

	public void render(Graphics g) {
		g.drawImage(wood, x, y, null);
	}

	public Rectangle getBounds() {
		if (handler.isShrunk())
			barrier = 8;
		if (!handler.isShrunk())
			barrier = 32;

		return new Rectangle(x, y, barrier, 32);
	}

}
