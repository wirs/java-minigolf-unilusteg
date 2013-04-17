package minigolf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class Clock extends JPanel implements Runnable {

	
	
	//Felder
	
	int hours, minutes, seconds, millis = 0;
	int width, height;
	String dspSeconds, dspMinutes, dspHours, Time ="";
	String Time2, Time3 ="";
	public JFrame frame;
	
	
	
	//Konstruktor
	
	public Clock(int height, int width){
		
		super();
		
		frame = new JFrame("Clock");
		
		this.setPreferredSize(new Dimension(width,height));
		//this.setSize(width, height);
		this.width = width;	this.height = height;
		
		this.setOpaque(false);
		this.setVisible(true);
		//JButton jB = new JButton("Salut");
		//this.add(jB);
		count = null;
		start();
		String date = "" + new java.util.Date();
		//System.out.println(date);
		String strheures = date.substring(11, 13);		//get Time from Computer
		String strminutes = date.substring(14,16);
		String strsecondes = date.substring(17,19);
		/*
		hours = Integer.valueOf(strheures); 	//syncronization with Computer Clock
		minutes = Integer.valueOf(strminutes);
		seconds = Integer.valueOf(strsecondes);
		*/
	}
	
	
	//Define Thread
	Thread count;
	
	//METHODES
	public String toStringdsp(int pos_X, int pos_Y, Graphics g){
		Time ="";
		Time= Time + dspHours + ":" + dspMinutes + ":" + dspSeconds;
		g.drawString(Time, pos_X, pos_Y);
		return Time;
		
	}
	
	
	public String toString(){
		Time2 ="";
		Time2= Time2 + dspHours + ":" + dspMinutes + ":" + dspSeconds;
		return  Time2;
	}
	
	public String getTime(){
		Time3 ="";
		Time3= Time3 + dspHours + ":" + dspMinutes + ":" + dspSeconds;
		return  Time3;
	}
	
	
	
	public void paint(Graphics g){
		
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, this.width, this.height);
		g.drawString(toStringdsp(0,10,g), 0, 10);
		g.drawRect(0, 0, (int) (0.5*this.width), (int) (0.5*this.height));
		
	}
	
	public void start(){
		if (count == null){
			hours = 0;
			minutes = 0;
			seconds = 0;
			count = new Thread(this);
			count.start();
		}
		
	}
	
	public void stop(){
		if (count != null)
		count = null;
		
	}
	
	
	//Main run() Mathod of Thread
	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		while (count == thisThread){
			try{
				//repaint();
				seconds+=1;
				if (seconds == 60){
					seconds = 0;
					minutes+=1;
				}
				if (minutes == 60){
					minutes = 0;
					hours+=1;
				}
				
				if (seconds<10)dspSeconds="0"+seconds;
				else
					dspSeconds=Integer.toString(seconds);
				if (minutes<10)dspMinutes="0"+minutes;
				else
					dspMinutes = Integer.toString(minutes);
				if (hours<10)dspHours="0"+hours;
				else
					dspHours=Integer.toString(hours);
				
				Thread.sleep(1000);		//1 Sekonn waarden
			}catch(InterruptedException e){stop(); System.out.println("Error");}
		}
		
	}
	
	
	//MAIN Method of class for Testing
	public static void main(String[] args){
		//Frame vum konstruktor vun Clock benotzt
		Clock cl = new Clock(800,800);
		cl.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cl.frame.setSize(800, 800);
		cl.frame.setContentPane(cl);
		cl.frame.setVisible(true);
		
		//Neien Frame, ereischt am Main kreeiert
		JFrame jF = new JFrame("Clock2");
		jF.getContentPane().add(new Clock(800,800));
		//jF.setSize(800, 800);
		jF.pack();
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setVisible(true);
		
		
	}
	
	
}

/*
 
		//Clock with Timer. DO NOT USE!

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends TimerTask {

	
	private int Test = 0;
	
	
	
	public Clock(){
		super();
		Test = 1;
		
	}
	
	
	
	public String toString(){
		String Time = "";
		Time = Integer.toString(Test);
		
		return Time;
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void run() {
		System.out.println(new Date().getTime());
		System.out.println(Test);
		Test+=1;
		if (Test==5)
			try {
				Thread.currentThread();
				//Thread.sleep(3000);
				Thread.sleep(3000);
				//Thread.currentThread().suspend();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	/**
	 * @param args
	 */
	/*
	
	public void start(){
		Timer timer = new Timer();
		Clock cl = new Clock();
		timer.scheduleAtFixedRate(cl, 0, 1000);
	}
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer state = 0;
		Scanner control = new Scanner (System.in);
		
		for (int i=0; i>=3; i++){
			i--;
			state=control.nextInt();
			if (state == 1) Thread.currentThread().suspend(); 
			else if (state == 2)Thread.currentThread().resume();
		}
		Timer timer = new Timer();
		Clock cl = new Clock();
		timer.scheduleAtFixedRate(cl, 0, 1000);
		Thread.currentThread().suspend();
	}

}


*/
