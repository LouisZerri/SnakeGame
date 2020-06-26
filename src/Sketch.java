import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Sketch extends JPanel implements ActionListener, KeyListener {
	
	public Timer timer;
	public int delay = 60;
	public Vector2D food;
	
	public Snake snake;
	
	public int score;
	
	
	public Sketch()
	{
		timer = new Timer(delay,this);
		timer.start();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		snake = new Snake();
		
		this.score = 0;
		
		this.pickLocation();
	}
	
	public void pickLocation()
	{
		int cols = 30;
		int rows = 30;
		
		food = new Vector2D((int) (Math.random() * cols), (int) (Math.random() * rows));
		
		food.multiply(20);
	}
	

	public void paint(Graphics g)
	{
		g.setColor(Color.lightGray);
		g.fillRect(1, 1, 600, 600);
		
		for(int j = 0; j < 30; j++)
		{
			for(int i = 0; i < 30; i++)
			{
				g.setColor(Color.black);
				g.drawRect(i*20, j*20, 20, 20);
			}
		}
		
		if(snake.eat(food))
		{
			this.pickLocation();
		}
		
		snake.death();
		
		snake.update();
		
		snake.draw((Graphics2D) g);
		
		g.setColor(Color.RED);
		g.fillRect((int)food.x, (int)food.y, 20, 20);
		
		g.setColor(Color.black);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("Scores: ", 600, 150);
		
		
		g.dispose();
				
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) 
		{
			snake.dir(0, -1);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			snake.dir(0, 1);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			snake.dir(1, 0);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			snake.dir(-1, 0);
		}
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) 
		{

			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			
			snake = new Snake();
			
			this.score = 0;
			
			this.pickLocation();
			
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
