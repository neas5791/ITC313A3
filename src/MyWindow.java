import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MyWindow extends JFrame {

	public MyWindow(){
		this.setTitle("Move the ball");
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.setLayout(new BorderLayout(0,0));
		this.addKeyListener(new KeyPress());
		
		
//		GoogleMap gm = new GoogleMap();
		
	}
	
	private class KeyPress implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main (String[] args){
		MyWindow w = new MyWindow();
//		JPanel p = new JPanel();

//		GoogleMap gm = new GoogleMap();

	}
	
	
	
}
