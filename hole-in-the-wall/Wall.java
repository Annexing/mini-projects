import java.awt.*;

public class Wall {
	int dir;
	int size = 10;
	int[] xC = new int[4];
	int[] yC = new int[4];
	int fWidth;
	int fHeight;

	int hX;		// hole attributes
	int hY;
	int hSize;

	private int speed;

/* 	1	4
	2	3
*/
	public Wall(int dir, int fWidth, int fHeight, int hSize, int speed) {
		this.dir = dir;
		this.fWidth = fWidth;
		this.fHeight = fHeight;

		this.hSize = hSize;

		this.speed = speed;

		if(dir==0) {
			xC[0] = xC[1] = hY = 0;
			xC[2] = xC[3] = size;

			yC[0] = yC[3] = 0;
			yC[1] = yC[2] = fHeight;

			hY = (int)(Math.random()*fHeight);
			if(hY > fHeight-hSize) hY -= hSize;
			if(hY < 0) hY += hSize;
		}

		if(dir==1) {
			xC[0] = xC[1] = 0;
			xC[2] = xC[3] = fWidth;

			yC[0] = yC[3] = hY = 0;
			yC[1] = yC[2] = size;

			hX = (int)(Math.random()*fWidth) - hSize;
			if(hX > fHeight-hSize) hX -= hSize;
			if(hX < 0) hX += hSize;
		}

		if(dir==2) {
			xC[0] = xC[1] = hX = fWidth - size;
			xC[2] = xC[3] = fWidth;

			yC[0] = yC[3] = 0;
			yC[1] = yC[2] = fHeight;

			hY = (int)(Math.random()*fHeight) - hSize;
			if(hY > fHeight-hSize) hY -= hSize;
			if(hY < 0) hY += hSize;
		}

		if(dir==3) {
			xC[0] = xC[1] = 0;
			xC[2] = xC[3] = fWidth;

			yC[0] = yC[3] = hY = fHeight - size;
			yC[1] = yC[2] = fHeight;

			hX = (int)(Math.random()*fWidth) - hSize;
			if(hX > fHeight-hSize) hX -= hSize;
			if(hX < 0) hX += hSize;
		}

	}
/*
	dir 0 = moves right
	dir 1 = moves down
	dir 2 = moves left
	dir 3 = moves up
*/
	public void move() {
		if(dir==0) {
			xC[0]+= speed;
			xC[1]+= speed;
			xC[2]+= speed;
			xC[3]+= speed;
			hX+= speed;
		}
		else if(dir==1) {
			yC[0]+= speed;
			yC[1]+= speed;
			yC[2]+= speed;
			yC[3]+= speed;
			hY+= speed;
		}
		else if(dir==2) {
			xC[0]-= speed;
			xC[1]-= speed;
			xC[2]-= speed;
			xC[3]-= speed;
			hX-=speed;
		}
		else if(dir==3) {
			yC[0]-= speed;
			yC[1]-= speed;
			yC[2]-= speed;
			yC[3]-= speed;
			hY--;
		}
	}

	public int[] getX() {
		return xC;
	}
	public int[] getY() {
		return yC;
	}

	public int getHoleX() {
		return hX;
	}

	public int getHoleY() {
		return hY;
	}

	public Rectangle getBounds() {
		if(dir==0) return new Rectangle(xC[0],yC[0],size,fHeight);

		if(dir==1) return new Rectangle(xC[0],yC[0],fWidth,size);

		if(dir==2) return new Rectangle(xC[0],yC[0],size,fHeight);

		if(dir==3) return new Rectangle(xC[0],yC[0],fWidth,size);

		return new Rectangle(0,0,fWidth,fHeight);
	}

	public Rectangle getHole() {
		if(dir==0) return new Rectangle(hX,hY,size,hSize);

		if(dir==1) return new Rectangle(hX,hY,hSize,size);

		if(dir==2) return new Rectangle(hX,hY,size,hSize);

		if(dir==3) return new Rectangle(hX,hY,hSize,size);

		return new Rectangle(0,0,fWidth,fHeight);
	}

	public int getSize() {
		return size;
	}

	public int getHoleSize() {
		return hSize;
	}

	public int getDir() {
		return dir;
	}
}

