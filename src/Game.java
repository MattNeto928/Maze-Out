import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	Random r = new Random();

	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private LevelSelect select;
	private SpriteSheet ss;
	public static int LEVEL = 1;
	public static boolean creepLocked = true;
	public static boolean peteLocked = true;

	
	// Image sprites for levels
	private BufferedImage level = null;
	private BufferedImage level2 = null;
	private BufferedImage level3 = null;
	private BufferedImage level4 = null;
	private BufferedImage level5 = null;
	private BufferedImage level6 = null;
	private BufferedImage level7 = null;
	private BufferedImage level8 = null;
	private BufferedImage level9 = null;
	private BufferedImage level10 = null;
	private BufferedImage level11 = null;
	private BufferedImage level12 = null;
	private BufferedImage level13 = null;
	private BufferedImage level14 = null;
	private BufferedImage level15 = null;
	private BufferedImage AD = null;
	private BufferedImage Sprite_Sheet = null;
	private BufferedImage floor = null;

	
	//Constructor
	public Game() {

		
		new Window(966, 573, "Maze Out", this); // Calling Window constuctor with parameters(width, height, name, instance(this))
		start();

		handler = new Handler(); // Creating handler object (handles all objects and stores in a LinkedList

		this.addKeyListener(new KeyInput(handler)); // Adding listeners for player input
		this.addMouseListener(new MouseInput(handler));

		
		BufferedImageLoader loader = new BufferedImageLoader(); // Creating a BufferedImageLoader (to turn BufferedImage sprites into readable pixels
		level = loader.loadImage("/IND_Level.png");
		level2 = loader.loadImage("/IND_Level2.png");
		level3 = loader.loadImage("/IND_Level3.png");
		level4 = loader.loadImage("/IND_Level4.png");
		level5 = loader.loadImage("/IND_Level5.png");
		level6 = loader.loadImage("/IND_Level6.png");
		level7 = loader.loadImage("/IND_Level7.png");
		level8 = loader.loadImage("/IND_Level8.png");
		level9 = loader.loadImage("/IND_Level9.png");
		level10 = loader.loadImage("/IND_Level10.png");
		level11 = loader.loadImage("/IND_Level11.png");
		level12 = loader.loadImage("/IND_Level12.png");
		level13 = loader.loadImage("/IND_Level13.png");
		level14 = loader.loadImage("/IND_Level14.png");
		level15 = loader.loadImage("/IND_Level15.png");
		AD = loader.loadImage("/IND_LevelAD.png");
		Sprite_Sheet = loader.loadImage("/IND_Sprite.png");

		ss = new SpriteSheet(Sprite_Sheet); // Creating a sprite sheet for graphics (i.e. door, key, blocks)

		handler.setSelected(1); // Setting selected variable in handler to 1

		floor = ss.grabImage(2, 2, 32, 32); // Setting floor image to 2, 2 on sprite sheet

		if (LEVEL == 1)
			loadLevel(level);

	}

	// Creates game states that could be used to change the screen the user sees
	public static enum STATE {
		Game, Menu, Settings, Controls, Customize, Mechanics, Pause, levelSelect
	};

	public static STATE State = STATE.Menu; // Starting state is in the menu

	
	// Switch statement to load the current Game.LEVEL (int)
	public void switchLevel() {
		clearLevel();

		switch (Game.LEVEL) {
		case 1:
			loadLevel(level2);
			break;
		case 2:
			loadLevel(level3);
			break;
		case 3:
			loadLevel(level4);
			break;
		case 4:
			loadLevel(level5);
			handler.setShrunk(false);
			break;
		case 5:
			loadLevel(level6);
			break;
		case 6:
			loadLevel(level7);
			break;
		case 7:
			loadLevel(level8);
			break;
		case 8:
			loadLevel(level9);
			break;
		case 9:
			loadLevel(level10);
			break;
		case 10:
			loadLevel(level11);
			break;
		case 11:
			loadLevel(level12);
			break;
		case 12:
			loadLevel(level13);
			break;
		case 13:
			loadLevel(level14);
			break;
		case 14:
			loadLevel(level15);
			break;
		}
		LEVEL++;
	}

	// Reseting Handler objects
	private void clearLevel() {
		handler.object.clear();
	}

	public void start() {
		isRunning = true; 
		thread = new Thread(this); // Starting thread of this instance
		thread.start();
	}

	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	// If the game is being played, there will be a call to the Handler's update every frame
	public void tick() {
		if (State == STATE.Game) {
			handler.update();
		}
	}

	// Graphics
	public void render() {
		// Random numbers to make things shake
		int titleX = r.nextInt(5) + 245; 
		int titleY = r.nextInt(5) + 95;
		int modeX = r.nextInt(5) + 348;
		int modeY = r.nextInt(5) + 172;
		
		BufferStrategy bs = this.getBufferStrategy(); // BufferStrategy to render images
		if (bs == null) {
			this.createBufferStrategy(3); // Renders 3 frames at a time to keep the game smooth and reduce lag
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		// Assigning fonts
		Font title = new Font("Bauhaus 93", Font.PLAIN, 70);
		Font fnt = new Font("arial", 1, 23);
		Font fnt2 = new Font("arial", 1, 40);
		
		
		// Different State graphics are drawn below with some conditionals to 
		// differentiate text color, font, and text shake
		/////////////////////////////////

		for (int xx = 0; xx < 32 * 72; xx += 32) {
			for (int yy = 0; yy < 32 * 72; yy += 32) {

				g.drawImage(floor, xx, yy, null); // Setting background
			}

		}

		if (State == STATE.levelSelect) {

			// Level Select Title
			g.setFont(title);
			g.setColor(Color.YELLOW);
			g.drawString("LEVEL", 300, 75);
			g.setColor(Color.CYAN);
			g.drawString("SELECT", 485, 75);

			// Level Boxes
			LevelSelect.render(g);
		}

		if (State == STATE.Game) {
			handler.render(g);
		} else if (State == STATE.Menu) {
			// Title
			g.setFont(title);
			g.setColor(Color.yellow);
			g.drawString("MAZE", titleX + 100, titleY);
			g.setColor(Color.cyan);
			g.drawString("OUT", titleX + 200, titleY + 50);

			// Play
			if (handler.getMode() == 1) {
				g.setColor(Color.cyan);
				g.drawRect(modeX, modeY, 250, 100);
				g.drawString("Play", modeX + 50, modeY + 75);
			} else {
				g.setColor(Color.yellow);
				g.drawRect(350, 175, 250, 100);
				g.drawString("Play", 400, 250);
			}
			// Help
			if (handler.getMode() == 2) {
				g.setColor(Color.cyan);
				g.drawRect(modeX, modeY + 125, 250, 100);
				g.drawString("Settings", modeX + 7, modeY + 195);
			} else {
				g.setColor(Color.yellow);
				g.drawRect(350, 300, 250, 100);
				g.drawString("Settings", 357, 370);
			}

			// Quit
			if (handler.getMode() == 3) {
				g.setColor(Color.cyan);
				g.drawRect(modeX, modeY + 250, 250, 100);
				g.drawString("Quit", modeX + 50, modeY + 325);
			} else {
				g.setColor(Color.yellow);
				g.drawRect(350, 425, 250, 100);
				g.drawString("Quit", 400, 500);

			}
		}

		////////////////////////////////////
		// Help Menu
		if (State == STATE.Settings) {
			Font subTitle = new Font("magneto", 1, 32);
			// Title
			g.setColor(Color.cyan);
			g.setFont(title);
			g.drawString("Settings", titleX + 125, titleY);

			g.setFont(subTitle);

			// Control Button
			if (handler.getSettingsMode() == 1) {
				g.setColor(Color.cyan);
				g.drawRect(modeX - 300, modeY + 25, 200, 100);
				g.drawString("Controls", modeX + -275, modeY + 75);
			} else {
				g.setColor(Color.yellow);
				g.drawRect(50, 200, 200, 100);
				g.drawString("Controls", 75, 250);
			}

			// Customize Button
			if (handler.getSettingsMode() == 2) {
				g.setColor(Color.cyan);
				g.drawRect(modeX + 25, modeY + 25, 200, 100);
				g.drawString("Customize", modeX + 35, modeY + 75);
			} else {
				g.setColor(Color.yellow);
				g.drawRect(375, 200, 200, 100);
				g.drawString("Customize", 385, 250);
			}

			// Mechanics Button
			if (handler.getSettingsMode() == 3) {
				g.setColor(Color.cyan);
				g.drawRect(modeX + 350, modeY + 25, 200, 100);
				g.drawString("Mechanics", modeX + 360, modeY + 75);
			} else {
				g.setColor(Color.yellow);
				g.drawRect(700, 200, 200, 100);
				g.drawString("Mechanics", 708, 250);
			}
		}

		if (State == STATE.Settings || State == STATE.Controls || State == STATE.Customize
				|| State == STATE.Mechanics) {
			// Back Button
			if (handler.getSettingsMode() == 4 || State == STATE.Controls || State == STATE.Customize
					|| State == STATE.Mechanics) {
				g.setFont(title);
				g.setColor(Color.cyan);
				g.drawRect(modeX + 25, modeY + 250, 200, 100);
				g.drawString("Back", modeX + 45, modeY + 325);
			} else {
				g.setFont(title);
				g.setColor(Color.yellow);
				g.drawRect(375, 425, 200, 100);
				g.drawString("Back", 395, 500);
			}
		}

		if (State == STATE.Controls) {
			g.setFont(title);
			g.drawString("Controls", modeX, modeY - 50);
			g.setFont(fnt);
			g.setColor(Color.yellow);
			g.drawString(
					"Up Arrow ---------------------------------------------------------------------------- Increase Size",
					50, 200);
			g.drawString(
					"Down Arrow --------------------------------------------------------------------- Decreased Size",
					50, 225);
			g.drawString(
					"W ---------------------------------------------------------------------------------------------- Move Up",
					50, 250);
			g.drawString(
					"A --------------------------------------------------------------------------------------------- Move Left",
					50, 275);
			g.drawString(
					"S ------------------------------------------------------------------------------------------- Move Down",
					50, 300);
			g.drawString(
					"D ------------------------------------------------------------------------------------------- Move Right",
					50, 325);
			g.drawString(
					"Space ---------------------------------------------------------------------------------- Focus Ability",
					50, 350);
			g.drawString(
					"R ------------------------------------------------------------------------------------------ Reset Level",
					50, 375);
			g.drawString(
					"P ------------------------------------------------------------------------------------------ Pause Game",
					50, 400);
		}

		if (State == STATE.Customize) {
			g.setFont(title);
			g.drawString("Customize", modeX, modeY - 50);
			g.setFont(fnt2);
			g.drawString("Coins: " + handler.coins, 750, 50);
			if (handler.getSelected() == 1) {
				g.setColor(Color.CYAN);
			}
			if (handler.getSelected() != 1) {
				g.setColor(Color.YELLOW);
			}
			g.drawString("Original - - - - - - - - - - - - - - - - - - - - - - - -  ", 50, 200);
			g.drawRect(805, 135, 110, 110);
			g.setColor(Color.black);
			g.fillRect(825, 155, 75, 75);
			g.setColor(Color.white);
			g.fillRect(845, 170, 10, 18);
			g.fillRect(870, 170, 10, 18);

			if (handler.getSelected() == 2) {
				g.setColor(Color.CYAN);
			}
			if (handler.getSelected() != 2) {
				g.setColor(Color.YELLOW);
			}

			if (creepLocked == true) {
				g.drawString("Price: 5 coins - - - - - - - - - - - - - - - - - - -   ", 50, 350);
				g.drawRect(50, 260, 100, 50);
				g.drawString("BUY", 60, 300);
			}

			if (creepLocked == false) {
				g.drawString("Creeper - - - - - - - - - - - - - - - - - - - - - - - -  ", 50, 350);
			}
			g.drawRect(805, 285, 110, 110);
			g.setColor(Color.green);
			g.fillRect(825, 305, 75, 75);
			g.setColor(Color.black);
			g.fillRect(840, 320, 16, 16);
			g.fillRect(870, 320, 16, 16);
			g.fillRect(856, 336, 16, 24);
			g.fillRect(848, 350, 8, 24);
			g.fillRect(872, 350, 8, 24);

			if (creepLocked == true) {
				g.setColor(Color.red);
				g.drawString("LOCKED", 775, 350);
			}

			if (handler.getSelected() == 3) {
				g.setColor(Color.CYAN);
			}
			if (handler.getSelected() != 3) {
				g.setColor(Color.YELLOW);
			}

			if (peteLocked == true) {
				g.drawString("Price: 10 coins -                     - - - - - - - -" + "   ", 50, 500);
				g.drawRect(50, 410, 100, 50);
				g.drawString("BUY", 60, 450);
			}

			if (peteLocked == false) {
				g.drawString("Pete - - - - - - - - -                      - - - - - - - - ", 50, 500);
			}
			g.drawRect(805, 430, 110, 110);
			g.setColor(new Color(255, 228, 196));
			g.fillRect(825, 455, 75, 75);
			g.setColor(Color.red);
			g.fillRect(825, 455, 75, 15);
			g.fillRect(825, 470, 15, 15);
			g.fillRect(885, 470, 15, 15);
			g.setColor(Color.black);
			g.fillRect(843, 470, 12, 25);
			g.fillRect(870, 470, 12, 25);
			g.fillRect(848, 506, 30, 10);
			g.fillRect(860, 452, 8, 4);

			if (peteLocked == true) {
				g.setColor(Color.red);
				g.drawString("LOCKED", 775, 500);
			}
		}

		if (State == STATE.Mechanics) {
			g.setFont(title);
			g.drawString("Mechanics", modeX, modeY - 50);
			g.setFont(fnt);
			g.setColor(Color.yellow);
			g.drawString("� Get to the door to recieve a coin and advance to the next level", 125, 200);
			g.drawString("� Touching Boost Pads will push your chosen character in the showed direction", 35, 250);
			g.drawString("� Make sure you unlock the door using the door key before entering the door", 50, 300);
			g.drawString("� When using the focus ability, your character has to be in ", 100, 350);
			g.drawString("its biggest form and touching the side of the wood", 150, 400);
		}

		if (State == STATE.Pause) {
			g.setColor(Color.white);
			g.setFont(title);
			g.drawString("Paused", 375, 75);
			g.setFont(fnt2);
			g.setColor(Color.yellow);
			g.drawRect(385, 175, 200, 100);
			g.drawString("Resume", 405, 235);
			g.drawRect(385, 375, 200, 100);
			g.drawString("Menu", 430, 440);
		}

		if (State == STATE.Menu) {
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Version 1.4", 800, 525);
		}

		///////////////////////////////////
		/////////////////////////////////
		g.dispose(); // Disposes of graphics after use
		bs.show(); // Shows the next available frame
	}
	
	public void level1() {
		clearLevel();
		loadLevel(level);
	}

	
	// Loads level by image
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth(); // image width
		int h = image.getHeight(); // image height

		
		// Nested for loop that runs through each pixel and assigns a grapihc
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {

				int pixel = image.getRGB(xx, yy); // Getting color of pixel (x, y)

				
				// Hexadecimal color assigning
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				
				// Adds a new object (to then be rendered in Handler) based on the color of the pixel of the level image
				// Objects differentiated by ID
				if (red == 160 && green == 160 & blue == 160)
					handler.addObject(new Checkpoint(xx * 32, yy * 32, ID.Checkpoint, ss));

				if (red == 255 && green == 0 & blue == 0)
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));
				if (red == 127 && green == 0 & blue == 0)
					handler.addObject(new InvBlock(xx * 32, yy * 32, ID.InvBlock, ss));

				if (red == 0 && green == 255 & blue == 0)
					handler.addObject(new Door(xx * 32, yy * 32, ID.Door, ss, handler));

				if (red == 255 && green == 0 & blue == 110)
					handler.addObject(new Boost(xx * 32, yy * 32, ID.Boost, ss));
				if (red == 255 && green == 127 & blue == 182)
					handler.addObject(new BoostDown(xx * 32, yy * 32, ID.BoostDown, ss));
				if (red == 127 && green == 0 & blue == 55)
					handler.addObject(new BoostRight(xx * 32, yy * 32, ID.BoostRight, ss));
				if (red == 127 && green == 63 & blue == 91)
					handler.addObject(new BoostLeft(xx * 32, yy * 32, ID.BoostLeft, ss));

				if (red == 0 && green == 0 & blue == 255)
					handler.addObject(new Player(xx * 32, yy * 32, ID.Player, handler, ss, this));

				if (red == 255 && green == 216 & blue == 0)
					handler.addObject(new Key(xx * 32, yy * 32, ID.Key, ss, handler));

				if (red == 64 && green == 64 & blue == 64)
					handler.addObject(new Wood(xx * 32, yy * 32, ID.Wood, ss, handler));

				if (red == 0 && green == 255 & blue == 255)
					handler.addObject(new BasicEnemy(xx * 32, yy * 32, ID.BasicEnemy, ss, handler));

				if (red == 155 && green == 0 & blue == 0)
					handler.addObject(new FakeBlock(xx * 32, yy * 32, ID.Fake, ss));

			}
		}
	}

	public static void main(String args[]) {
		new Game();
	}

}
