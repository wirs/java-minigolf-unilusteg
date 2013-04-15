package minigolf;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Time1  extends JPanel {

	/**
	 * @param args
	 */
	
	public Time1(){
		this.setSize(100, 100);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 50, 50);
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.add(new Time1());
		jf.pack();
		jf.setVisible(true);

	}

}
