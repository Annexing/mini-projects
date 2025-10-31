import java.awt.*;
import java.awt.Shape.*;

public class Player {
	int x;
	int y;
	int size = 10;
	int fWidth, fHeight;
	int speed = 3;
	KeyHandler k = new KeyHandler();

	String name;

	public Player(int fWidth, int fHeight, String n) {
		this.name = n;
		this.fWidth = fWidth;
		this.fHeight = fHeight;

		x = fWidth/2;
		y = fHeight/2;
	}

	public void move() {

		try {
			Thread.sleep(10);
		} catch(Exception e) {}
	}

	public String getName() {
		return name;
	}

	public void move(int dir) {
		if(dir==0) x+= speed;

		if(dir==1) y+= speed;

		if(dir==2) x-= speed;

		if(dir==3) y-= speed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getR() {
		return size/2;
	}

	public int getSize() {
		return size;
	}
}
