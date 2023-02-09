import java.awt.Graphics;
import java.awt.Rectangle;

public class InvBlock extends GameObject {

	public InvBlock(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
	}

	public void update() {

	}

	public void render(Graphics g) {

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
