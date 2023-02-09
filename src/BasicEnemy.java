import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {

	private BufferedImage BasicEnemy;
	Handler handler;

	public BasicEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;

		BasicEnemy = ss.grabImage(9, 2, 32, 32); // Setting image of this

		if (Game.LEVEL != 14 && Game.LEVEL != 15) {  // Setting velocity (different for some levels)
			velX += 3;
		}

		if (Game.LEVEL == 14 || Game.LEVEL == 15) {
			velY += 3;
		}
	}

	// Called every frame
	public void update() {
		x += velX;
		y += velY;

		collision();
	}

	public void render(Graphics g) {
		g.drawImage(BasicEnemy, x, y, null);
	}

	// Collision detection
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (Game.LEVEL != 14 && Game.LEVEL != 15) {
				if (tempObject.getId() == ID.Block) {
					if (getBounds().intersects(tempObject.getBounds())) {
						velX *= -1; // Changes direction of object if it intersects with any block
					}
				}
			}

			if (Game.LEVEL == 14 || Game.LEVEL == 15) {
				if (tempObject.getId() == ID.Block) {
					if (getBounds().intersects(tempObject.getBounds())) {
						velY *= -1; // Changes direction of object if it intersects with any block
					}
				}
			}
		}
	}

	// Returns Rectangle bounds of This
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
