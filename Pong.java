import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer; 
public class Pong implements KeyListener,FocusListener, MouseListener {
	private JPanel panel;
	private JFrame frame;
	private DrawingArea canvas;
	private int xpos, ypos,width,height;
	private int xpos2, ypos2, width2, height2;
	private int dx, dy;
	private int ballX, ballY, ballR;
	private int score1, score2;
	
	public Pong(){
		dx = 25;
		dy = 25;
		xpos = 300;
		ypos = 550;
		width = 100;
		height = 50;
		
		xpos2 = 300;
		ypos2 = 100;
		width2 = 100;
		height2 = 50;
		
		ballR = 50;
		ballX = 325; 
		ballY = 330;
		
		frame = new JFrame("Pong");
		frame.setSize(700,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new DrawingArea();		// create a panel to draw on
      	canvas.setBackground(Color.WHITE);
		canvas.addFocusListener(this);
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		
		
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		Thread thread = new Thread(){
		public void run(){
			while(true){
				ballX = ballX + dx;
				ballY = ballY + dy;
				if(ballX - ballR < 0 ){
					dx = -dx;
					ballX = ballR;
				}
				else if(ballX + ballR > 700){
					dx = -dx;
					ballX = 700 - ballR;
				}
				
				if(ballY - ballR < 0){
					dy = -dy;
					ballX = 700-ballR;
				}
				else if(ballY+ballR > 700){
					dy = -dy;
					ballY = 700-ballR;
				}
				
				try{
					Thread.sleep(50);
				}catch(InterruptedException e){
					
				}
				
			}
			
			
		}
		
		
			
		};
		thread.start();
	}
	public static void main(String[]args){
		
		Pong p = new Pong();
	}
	
		
	

	class DrawingArea extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor ( Color.blue );
			g.fillRect ( xpos, ypos, width, height );
			g.setColor(Color.red);
			g.fillRect(xpos2, ypos2, width2, height2);
			
			
			g.setColor(Color.black);
			g.fillRect(0,0,700,50);
			g.fillRect(0,630,700,50);
			
			g.fillOval(ballX,ballY,ballR, ballR);
			
		}
	}
	public void focusGained(FocusEvent evt) {
		canvas.repaint();
	}
   
	public void focusLost(FocusEvent evt) {
		canvas.repaint();
	}

	public void mousePressed(MouseEvent evt) {
		canvas.requestFocus();
	}   
   
   	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseReleased(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void keyPressed ( KeyEvent e )    {
		int value = e.getKeyCode();
		
		switch ( value )    {
			case KeyEvent.VK_RIGHT:  	xpos += 50;	break;
			case KeyEvent.VK_LEFT:  	xpos -= 50;	break;
			case KeyEvent.VK_A:			xpos2 -= 50; break;
			case KeyEvent.VK_D:			xpos2 += 50; break;
			/*try to drop the ball with the space button
			 * case KeyEvent.VK_SPACE:
				ballX+=25;
				ballY+=25;
				break;
			case KeyEvent.VK_ENTER:
				xpos = (int)( Math.random ( ) * ( 500 - (2 * radius) ) );
				ypos = (int)( Math.random ( ) * ( 500 - (2 * radius) ) );
				break; 
			*/
		}
		if( (xpos < 0 || xpos >= 700) || (xpos2 < 0 || xpos2 >= 700)){
			 if(xpos < 0 || xpos2 < 0){
				if(xpos < 0) xpos = 0;
			    else if(xpos2 < 0) xpos2 = 0;
				return;
			}
			else if(xpos >= 700 || xpos2 >= 700){
				if(xpos >= 700)xpos = 550;
				else if(xpos2 >= 700) xpos2=550;
				return;
			}
		}
		
		canvas.repaint ( );
	}
	public void keyTyped ( KeyEvent e )   {}
	public void keyReleased ( KeyEvent e )   {}
	public int getWidth(){return 700;}
	public int getHeight(){return 700;}
}
