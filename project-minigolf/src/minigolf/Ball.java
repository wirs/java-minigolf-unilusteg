package minigolf;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

//Deklarierung der Felder
int posX, posY,v,lang,breit;
double w;
Color c;
Graphics g;
	
//Konstruktoren
public Ball(int posX, int posY,int v,double w,int lang,int breit,Color c){
this.posX=posX;this.posY=posY; this.v=v;this.w=w;this.lang=lang;this.breit=breit;
this.c=c;

}
//Methoden
public void paint(Graphics g){
	g.setColor(c);
	int[]px=new int[4];
	
	px[0]=(int) (posX+lang/2*Math.cos(w)-breit/2*Math.sin(w));
	px[1]=(int) (posX+lang/2*Math.cos(w)+breit/2*Math.sin(w));
	px[2]=2*posX-px[0];
	px[3]=2*posX-px[1];;
	
	
	int[]py=new int[4];
	
	py[0]=(int) (posY+lang/2*Math.sin(w)+breit/2*Math.cos(w));
	py[1]=(int) (posY+lang/2*Math.sin(w)-breit/2*Math.cos(w));
	py[2]=2*posY-py[0];
	py[3]=2*posY-py[1];
	
	g.fillPolygon(px,py,4);
	
	g.setColor(Color.BLUE);
	
	int[]px2=new int[4];
	
	px2[0]=(int) (posX+lang/4*Math.cos(w)/2-breit/2*Math.sin(w)/2);
	px2[1]=(int) (posX+lang/4*Math.cos(w)/2+breit/2*Math.sin(w)/2);
	px2[2]=2*posX-(px2[0]);
	px2[3]=2*posX-(px2[1]);
	
	
	int[]py2=new int[4];
	
	py2[0]=(int) (posY+lang/4*Math.sin(w)/2+breit/2*Math.cos(w)/2);
	py2[1]=(int) (posY+lang/4*Math.sin(w)/2-breit/2*Math.cos(w)/2);
	py2[2]=2*posY-(py2[0]);
	py2[3]=2*posY-(py2[1]);
	
	g.fillPolygon(px2,py2,4);
	
}
public void go (int t){
	posX=posX+(int)(v*t*Math.cos(w));
	posY=posY+(int)(v*t*Math.sin(w));
}

public void turn(double dW){
	w=w+dW;
}
}
