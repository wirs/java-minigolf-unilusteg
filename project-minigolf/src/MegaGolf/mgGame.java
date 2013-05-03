/*
 =================
 = MEGAGOLF GAME =
 =================
 */
package MegaGolf;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.TimerTask;
import javax.swing.*;

/**
 *
 * @author Daniel Warnimont, Claude Ernzer, Luc Welter
 */
public class mgGame extends JPanel implements MouseListener, MouseMotionListener {
    
    //FIELDS
    int clicks = 0;
    double mouseX, mouseY;
    boolean isMoving = false;
    boolean hasBall = false;
    boolean started = false;
    boolean drawHelperLine = false;
    public Rectangle screen, bounds;
    public JFrame frame;
    public MGBall ball;
    public MGHole goal;
    public MGTimerTask ballTask;
    public java.util.Timer ballTimer;

    //CONSTRUCTORS
    public mgGame() {

        super();
        screen = new Rectangle(0, 0, 800, 600);
        bounds = new Rectangle(0, 0, 800, 600);
        ball = new MGBall();
        ballTimer = new java.util.Timer();
        ballTask = new MGTimerTask();
        goal = new MGHole(750, 550, 15, 15);
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.validate();
    }

    class MGTimerTask extends TimerTask{

        @Override
        public void run() {
            ball.move();
            repaint();
	}
    }
    
    //INNER CLASS HOLE
    class MGHole extends Rectangle {

        //FIELDS
        int x, y, width, height;

        //CONSTRUCTORS
        public MGHole(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void draw(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillOval(x, y, width, height);
        }
    }

    //INNER CLASS BALL
    class MGBall extends Rectangle {

        //FIELDS
        double x, y, xVel, yVel;
        double oldX, oldY, oldXVel, oldYVel;

        //CONSTRUCTORS
        public MGBall() {
            super(0, 0, 10, 10);
        }

        //METHODS
        public void move() {

            //tests if the ball is moving
            if (xVel != 0. || yVel != 0.) {
                isMoving = true;
                drawHelperLine = false;
            } 
            else {
                isMoving = false;
                drawHelperLine = true;
            }

            //friction factor
            xVel *= 0.97;
            yVel *= 0.97;

            //determine new coordinates
            x += xVel;
            y += yVel;

            //collision detection for field bounds
            if (x > (bounds.width - ball.width)) {
                xVel = -xVel;
                x = bounds.width - ball.width;
            }
            
            if (y > (bounds.height - ball.height)) {
                yVel = -yVel;
                y = bounds.height - ball.height;
            }
            
            if (x <= 0) {
                xVel = -xVel;
                x = 0;
            }
            
            if (y <= 0) {
                yVel = -yVel;
                y = 0;
            }
            
            //determine if ball is in the hole
            if (x > 750 && x < 750 + 5 && y > 550 && y < 550 + 5) {
                xVel = 0;
                yVel = 0;
                hasBall = true;
                drawHelperLine = false;
            }
            
            //make the ball stop earlier
            if (Math.abs(xVel) < 0.05) {xVel = 0;}
            
            if (Math.abs(yVel) < 0.05) {yVel = 0;}
        }
        
        //draw the ball
        public void draw(Graphics g) {
            g.setColor(Color.yellow);
            g.fillOval((int) x, (int) y, width, height);
        }
        
        //setters & getters
        public double getX(){return x;}
        
        public double getY(){return y;}
        
        public double getXVel(){return xVel;}
        
        public double getYVel(){return yVel;}
        
        public void setX(double x){this.x=x;}
        
        public void setY(double y){this.y=y;}
        
        public void setXVel(double xVel){this.xVel=xVel;}
        
        public void setYVel(double yVel){this.yVel=yVel;}  
    }

    //MAIN CLASS METHODS
    private void startTimer(){
        ballTimer = new java.util.Timer();
        ballTask = new MGTimerTask();
        ballTimer.scheduleAtFixedRate(ballTask, 0, 20);
    }
    
    private void stopTimer(){
        ballTask.cancel();
        ballTimer.cancel();
    }
    
    public void start() {
        startTimer();
        started = true;
        drawHelperLine = true;
    }
    
    public void reset() {
        clicks = 0;
        ball.setX(0);
        ball.setY(0);
        ball.setXVel(0);
        ball.setYVel(0);
        hasBall = false;
        started = false;
        drawHelperLine = false;
        stopTimer();
    }
    
    public void pause() {
        started = false;
        drawHelperLine = false;
        ball.oldX=ball.getX();
        ball.oldY=ball.getY();
        ball.oldXVel=ball.getXVel();
        ball.oldYVel=ball.getYVel();
        stopTimer();
    }
    
    public void resume(){
        started=true;
        if(isMoving){drawHelperLine = false;}
        else{drawHelperLine = true;}
        ball.x=ball.oldX;
        ball.y=ball.oldY;
        ball.xVel=ball.oldXVel;
        ball.yVel=ball.oldYVel;
        startTimer();
    }
    
    @Override
    public void paintComponent(Graphics g) {

        //anti aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        bounds = g.getClipBounds();
        
        //draw playing field
        Color fieldColor = new Color(0, 128, 0);
        g.setColor(fieldColor);
        g.fillRect(screen.x, screen.y, screen.width, screen.height);
        
        //draw helper line
        g.setColor(Color.RED);
        if (drawHelperLine) {
            g.drawLine((int) (ball.x + ball.width / 2), (int) (ball.y + ball.width / 2), (int) (mouseX), (int) (mouseY));
        }

        //crosshair cursor
        g.setColor(Color.BLACK);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        //draw hole
        goal.draw(g);
        
        //draw ball
        ball.draw(g);
    }

    //MOUSE EVENTS
    @Override
    public void mouseClicked(MouseEvent e) {
        //ignore mouse clicks if game is not started
        if (!started) {
            e.consume();
        } 
        //ignore mouse clicks if the ball is in the hole
        else if (hasBall) {
            e.consume();
        } 
        //ignore mouse clicks if the ball is moving
        else if (isMoving) {
            e.consume();
        } 
        else {
            //get coordinates of click location
            int xclick = e.getX();
            int yclick = e.getY();

            //determine ball speed in relation to click distance
            ball.xVel = ((xclick - (ball.x + ball.width / 2)) / 10);
            ball.yVel = ((yclick - (ball.y + ball.height / 2)) / 10);

            //increment the shot counter
            clicks += 1;
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseMoved(MouseEvent e) {

        //get mouse coordinates
        mouseX = e.getX();
        mouseY = e.getY();

        if (!isMoving && drawHelperLine) {
            repaint();
        }

        //debug mouse coordinates
        //System.out.println("x:"+ mouseX + " "+ "y:"+mouseY);
    }
}
