package minigolf;

import java.awt.Color;
import java.awt.Graphics;


public class Goldapplet extends java.applet.Applet implements Runnable {
	
	Ball[] a=new Ball[10];
public void init(){for(int i=0;i<a.length;i++){
	a[i]=new Ball((int)(Math.random()*700),(int)(Math.random()*700),1,Math.PI/4,50,20,Color.RED);//posx posy ang l b col
}}
public void paint(Graphics g){for(int i=0;i<a.length;i++){
	a[i].paint(g);}
}
public void start(){ new Thread(this).start();}
@Override
public void run() {
	// TODO Auto-generated method stub
	for (int k=0;k<5000;k++){
		for(int i=0;i<a.length;i++){
	a[i].go(10);
	a[i].turn(0.1);
	}
	try{Thread.sleep(100);} catch(Exception e){}
	repaint();
	}
	
	

}}
