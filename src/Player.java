import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {

	Handler handler;
	Game game;

	Random r = new Random();
	private boolean facing = true;
	// true = right
	// false = left

	private BufferedImage player_imageL;
	private BufferedImage player_imageR;
	private BufferedImage player_imageLS;
	private BufferedImage player_imageRS;
	private BufferedImage fPlayer;
	private BufferedImage creeper_image;
	private BufferedImage creeper_imageS;
	private BufferedImage pete_image;
	private BufferedImage pete_imageS;
	private int checkpointX = 100, checkpointY = 450;
	public static boolean canF = false;
	public static int deaths = 0;

	private int size;
	private int woodSize;
	private int woodLength;

	public Player(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;

		// Images of different skins/orientatinos of player
		player_imageR = ss.grabImage(1, 1, 32, 32);
		player_imageL = ss.grabImage(2, 1, 32, 32);
		player_imageRS = ss.grabImage(4, 1, 32, 32);
		player_imageLS = ss.grabImage(4, 2, 32, 32);
		fPlayer = ss.grabImage(9, 1, 32, 32);
		creeper_image = ss.grabImage(11, 1, 32, 32);
		creeper_imageS = ss.grabImage(12, 1, 16, 16);
		pete_image = ss.grabImage(11, 2, 32, 32);
		pete_imageS = ss.grabImage(12, 2, 16, 16);
	}

	// Checking player attributes every frame
	public void update() {
		// Changes location based on velocity
		x += velX;
		y += velY;

		collision();

		if (KeyInput.reset == true) {
			handler.removeObject(this);
			handler.addObject(new Player(100, 450, id, handler, ss, game));
			KeyInput.reset = false;
		}

		if (handler.isFocused() && canF == true) {
			x += r.nextInt(3) - 1;
			y += r.nextInt(3) - 1;
		}

		if (handler.isUp())
			velY = -5;
		else if (!handler.isDown())
			velY = 0;

		if (handler.isDown())
			velY = 5;
		else if (!handler.isUp())
			velY = 0;

		if (handler.isLeft()) {
			velX = -5;
			facing = false;
		} else if (!handler.isRight())
			velX = 0;

		if (handler.isRight()) {
			velX = 5;
			facing = true;
		} else if (!handler.isLeft())
			velX = 0;

	}

	public void render(Graphics g) {

		//Renders different orientations of player based on conditions
		
		handler.getSelected();
		if(handler.getSelected() == 1) {
		if (facing == true) {
			if (handler.isShrunk())
				g.drawImage(player_imageRS, x, y, null);
			if (!handler.isShrunk())
				g.drawImage(player_imageR, x, y, null);
		}
		if (facing == false) {
			if (handler.isShrunk())
				g.drawImage(player_imageLS, x, y, null);
			if (!handler.isShrunk())
				g.drawImage(player_imageL, x, y, null);
		}

		if (handler.isFocused())
			g.drawImage(fPlayer, x, y, null);
	}
		
		if(handler.getSelected() == 2) {
			if (handler.isShrunk())
				g.drawImage(creeper_imageS, x, y, null);
			if (!handler.isShrunk())
				g.drawImage(creeper_image, x, y, null);
		}
		
		if(handler.getSelected() == 3) {
			if(handler.isShrunk())
				g.drawImage(pete_imageS, x, y, null);
			if(!handler.isShrunk())
				g.drawImage(pete_image, x, y, null);	
		}
	}

	// Checks for collision and has different affects on the player depending on the ID of the collider
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}

			if (tempObject.getId() == ID.Door && Door.canPass == true) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.switchLevel();
					handler.setHKey(false);
					handler.coins++;
				}
			}

			if (tempObject.getId() == ID.Boost) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y += velY = -6;
				}
			}

			if (tempObject.getId() == ID.BoostDown) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y += velY = 6;
				}
			}

			if (tempObject.getId() == ID.BoostRight) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += velX = 6;
				}
			}

			if (tempObject.getId() == ID.BoostLeft) {
				if (getBounds().intersects(tempObject.getBounds())) {

					x += velX = -6;
				}
			}

			if (tempObject.getId() == ID.InvBlock) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}

			if (tempObject.getId() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.addObject(new Player(checkpointX, checkpointY, id, handler, ss, game));
					deaths++;
				}

			}

			if (tempObject.getId() == ID.Checkpoint) {
				if (getBounds().intersects(tempObject.getBounds())) {
					checkpointX = this.getX();
					checkpointY = this.getY();
				}
			}

			if (tempObject.getId() == ID.Wood) {
				if (getBoundsWood().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}

				if (getBounds().intersects(tempObject.getBounds()))
					canF = true;

				if (!getBounds().intersects(tempObject.getBounds()))
					canF = false;

			}
		}
	}

	public Rectangle getBounds() {
		if (Game.LEVEL == 3 || Game.LEVEL == 4) {
			handler.setShrunk(true);
		}

		// Changes variable on the player shrinking
		if (handler.isShrunk()) {
			woodSize = -12;
			size = 16;
		} else if (!handler.isShrunk()) {
			woodSize = 12;
			size = 32;
		}
		return new Rectangle(x, y, size, size);
	}

	// Changes hitbox for wood (since it is a werid shape)
	public Rectangle getBoundsWood() {
		if (handler.isShrunk()) {
			size = 16;
			woodSize = -12;
			woodLength = 15;
		} else if (!handler.isShrunk()) {
			size = 32;
			woodSize = 12;
			woodLength = 6;
		}
		return new Rectangle(x + woodSize, y, woodLength, size);
	}
}
