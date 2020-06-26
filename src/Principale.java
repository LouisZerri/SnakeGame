import javax.swing.JFrame;

public class Principale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame object = new JFrame(); 
		
		Sketch s = new Sketch();
		
		object.setBounds(10,10,700,630); 
		
		object.setTitle("Snake Game"); 
		
		object.setResizable(false); 
		
		object.setVisible(true); 
		
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		object.add(s);
	}

}
