import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.applet.Applet;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	int keysPressed = 0;
	ArrayList<Integer> keyCodes = new ArrayList<Integer>();

	public KeyHandler() {
		super();
	}

	public void keyPressed(KeyEvent e) {

		if(!(keyCodes.contains(e.getKeyCode()))) {
			keysPressed++;
			keyCodes.add(e.getKeyCode());
		}
		//System.out.println(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		if(keyCodes.contains(e.getKeyCode())) {
			keysPressed--;
			keyCodes.remove(new Integer(e.getKeyCode()));
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public int getKeysPressed() {
		return keysPressed;
	}
	public ArrayList<Integer> getKeyCodes() {
		return keyCodes;
	}

}
