import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Snake {
	
	public float x = 0;
	public float y = 0;
	
	public float xspeed = 1;
	public float yspeed = 0;
	
	int total = 0;
	int scl = 20;
	
	boolean play = true;
	
	ArrayList<Vector2D> tail = new ArrayList<Vector2D>();
	
	
	public Snake() {}
	
	public double dist(double x1, double y1, double x2, double y2) 
	{       
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	
	public float constrain(float value, float min, float max) 
	{
		return Math.min(Math.max(value, min), max);
	}
	
	public boolean eat(Vector2D pos)
	{
		double d = dist(this.x, this.y, pos.x, pos.y);
		
		if(d < 1)
		{
			this.total++;
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	
	public void dir(float x, float y)
	{
		if(play)
		{
			this.xspeed = x;
			this.yspeed = y;
		}
		
	}
	
	public void death()
	{
		for(int i = 0; i < tail.size(); i++)
		{
			Vector2D pos = tail.get(i);
			double d = dist(x, y, pos.x, pos.y);
			
			if(d < 1)
			{
				gameover();
			}
		}
	}
	
	public void gameover()
	{
		this.total = 0;
		tail.clear();
		this.xspeed = 0;
		this.yspeed = 0;
		play = false;
		
	}
	
	public void update()
	{
		if(this.total > 0)
		{
			if(total == tail.size() && !tail.isEmpty())
			{
				tail.remove(0);
			}
			
			tail.add(new Vector2D(this.x, this.y));
		}
		
		this.x = this.x + this.xspeed * this.scl;
		this.y = this.y + this.yspeed * this.scl;
		
		this.x = constrain(this.x, 0, 580);
		this.y = constrain(this.y, 0, 580);	
		
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.green);
		
		for(Vector2D v : tail)
		{
			g.fillRect((int)v.x, (int)v.y, scl, scl);
		}
		
		g.fillRect((int)this.x, (int)this.y, this.scl, this.scl);
		
		for(int i = 0; i < tail.size(); i++)
		{
			Vector2D pos = tail.get(i);
			double d = dist(x, y, pos.x, pos.y);
			
			if(d < 1)
			{
				g.setColor(Color.black);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("" + this.total, 640, 190);
			}
		}
		
		
		
	}
		
}
