import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.Applet;

public class HoleInTheWall extends JPanel{

	JFrame frame;
	Player p;
	Player p2;
	KeyHandler k = new KeyHandler();
	Scanner keyboard = new Scanner(System.in);
	ArrayList<Wall> walls = new ArrayList<Wall>();

	Color backgroundColor = new Color(240,255,250);
	Color wallColor = new Color(0,0,0);

	boolean gameOver = false;
	boolean twoPlayer = false;

	long lastTime = System.currentTimeMillis();
	long lastWall = 0;
	long lastCollision = System.currentTimeMillis()-500;
	long startTime = System.currentTimeMillis();

	int wallCooldown = 3000;
	int currentWall = 0;
	int levelTimer = 42069;

	int wallSpeed = 1;
	int holeSize = 100;

	public static void main(String[] args) {
		HoleInTheWall asdjke = new HoleInTheWall(true);		// yes or no two player

	}

	public HoleInTheWall(boolean b) {
		frame = new JFrame("Hole In The Wall (not from MCC)");
		frame.setSize(800,800);	//width, height
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(k);

		twoPlayer = b;

		p = new Player(frame.getWidth(),frame.getHeight(), "Player One - WSAD");
		if(twoPlayer) p2 = new Player(frame.getWidth(),frame.getHeight(), "Player Two - Arrow Keys");

		System.out.println("/:");
		String s = keyboard.nextLine();
		if(s.equals("hax")) {
			wallCooldown = 5000;
			holeSize = 500;
			levelTimer = 100000;
		}
	}

	public void paintComponent(Graphics g) {
		drawBackground(g);
try{
		if(!gameOver) {

			if(System.currentTimeMillis()- lastTime > 5) {
				lastTime = System.currentTimeMillis();
				checkPlayerMotion();
				moveWalls();
			}

			if(System.currentTimeMillis()- lastWall > wallCooldown) {
				lastWall = System.currentTimeMillis();
				makeNewWall();
			}

			if(System.currentTimeMillis()- lastWall > levelTimer) {
				wallSpeed+=1;
				wallCooldown-= 100;
				holeSize -= 3;
			}

			drawWalls(g);

			drawPlayer(g, p);
			checkCollisions(g, p);

			if(twoPlayer) {
				drawPlayer(g, p2);
				checkCollisions(g, p2);
			}

		}
		else {
			endGame(g);
		}

		//checkRestart();

} catch(Exception e) {}
	}

	public void drawBackground(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		frame.repaint();
	}

	public void makeNewWall() {
		walls.add(new Wall((int)(Math.random()*4) ,frame.getWidth(),frame.getHeight(), holeSize, wallSpeed));
		currentWall++;
	}

	public void moveWalls() {
		for(Wall w : walls) {
			w.move();
		}
	}

	public void drawWalls(Graphics g) {
		for(Wall w : walls) {
			Rectangle wall = w.getBounds();
			g.setColor(wallColor);
			g.fillPolygon(w.getX(),w.getY(),4);

			g.setColor(backgroundColor);
			Rectangle r = w.getHole();
			g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());

			if(wall.getX() > frame.getWidth() || wall.getX()<0) walls.remove(w);
			if(wall.getY() > frame.getHeight() || wall.getY()<0) walls.remove(w);
		}

	}

	public void checkPlayerMotion() {
		if(k.getKeysPressed()>0) {
			for(int i = 0; i<k.getKeyCodes().size(); i++) {
				int keyCode = k.getKeyCodes().get(i);

				if(keyCode==68) p.move(0); //x++	(wsad)

				if(keyCode==83) p.move(1); //y++

				if(keyCode==65) p.move(2); //x--

				if(keyCode==87) p.move(3); //y--
			}
// player 2
			if(twoPlayer) {
				for(int i = 0; i<k.getKeyCodes().size(); i++) {
					int keyCode = k.getKeyCodes().get(i);

					if(keyCode==39) p2.move(0); //x++	(wsad)

					if(keyCode==40) p2.move(1); //y++

					if(keyCode==37) p2.move(2); //x--

					if(keyCode==38) p2.move(3); //y--
				}
			}
		}
	}

	public void checkRestart() {
		if(k.getKeysPressed()>0) {
			for(int i = 0; i<k.getKeyCodes().size(); i++) {
				int keyCode = k.getKeyCodes().get(i);
				if(keyCode==82) {		// reset (key: r);
					walls.clear();
					currentWall = 0;

					wallCooldown = 3000;
					currentWall = 0;
					wallSpeed = 1;
					holeSize = 100;

					gameOver = false;
				}
			}
		}
	}

	public void drawPlayer(Graphics g, Player p) {
		g.setColor(new Color(0,0,0));
		g.drawOval(p.getX(),p.getY(),p.getSize(),p.getSize());

	}

	public void checkCollisions(Graphics g, Player p) {
		for(int i = 0; i<walls.size(); i++) {
			int rX = (int)walls.get(i).getBounds().getX();
			int rY = (int)walls.get(i).getBounds().getY();
			int rW = (int)walls.get(i).getBounds().getWidth();
			int rL = (int)walls.get(i).getBounds().getHeight();

			int rS = (int)walls.get(i).getSize();

			int hX = (int)walls.get(i).getHole().getX();
			int hY = (int)walls.get(i).getHole().getY();
			int hW = (int)walls.get(i).getHole().getWidth();
			int hL = (int)walls.get(i).getHole().getHeight();

			int hS = (int)walls.get(i).getHoleSize();

			int pX = p.getX();
			int pY = p.getY();
			int r = p.getR();
			// wall player collision
			if((Math.abs(rX - pX) <= r + rS/4 || Math.abs(rY-pY) <= r + rS/4)) {
				if( !(Math.abs(hX - pX) <= r + hS && Math.abs(hY-pY) <= r + hS)) {	// checks if not in hole space
					if(!gameOver) {
						System.out.println(p.getName() + " has died. they is be throwing and should try getting gooder at the videogame.");
						double timeAlive = (System.currentTimeMillis()-startTime)/1000.0;
						System.out.println("You were alive for " + timeAlive + " seconds.");
					}
					endGame(g);
				}
			}

		}

	}

	public void endGame(Graphics g) {
		gameOver = true;
		g.setColor(new Color(0,0,0));
		g.drawString("game over", frame.getWidth()/2, frame.getHeight()/2);
	}

}



