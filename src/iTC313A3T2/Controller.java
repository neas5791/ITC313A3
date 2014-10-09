package iTC313A3T2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Controller extends JFrame{
	private List<View> theCars;
	private List<Thread> theThreads;
	private Dimension dimension;
	
	public Controller(){
		this.setTitle("Assignment 3 - Task 2 The Wacky Races ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(950,150);
		init();
		
		this.addKeyListener(new MyKeyListener());
		this.setFocusable(true);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	private void init() {
		theCars = new LinkedList<View>();
		theThreads = new LinkedList<Thread>();
	
		// Creates four objects
		for (int i = 0; i < 4; i++){
			theCars.add(new View(i));
		}

		// Sets the layout and size based on number of objects
//        JPanel main = new JPanel(new BorderLayout());
//        JPanel body = new JPanel(new GridLayout(0, theCars.size()));
//        dimension = new Dimension(175 * theCars.size(),150);
//        body.setSize(dimension);
//        Instructions instruction = new Instructions();
        // haven't finished the above layout stuff
//        
		// Sets the layout and size based on number of objects
		this.setLayout(new GridLayout(0, theCars.size()));
		this.setSize(175 * theCars.size(),150);
		

		Stack<String> s = new Stack<String>();
		
		s.push("Penelope Pitstop");
		s.push("The Ant Hill Mob");
		s.push("Dick Dastardly and Muttley");
		s.push("Peter Perfect");
		
		// Add the threads to Thread List
		for (View v : theCars){
			theThreads.add(new Thread(v));
			
			// Set up Panels
			v.setName(s.pop());
			this.add(v);
		}
		
//		main.add(body, BorderLayout.CENTER);
//		main.add(instruction, BorderLayout.SOUTH);
//		this.add(main);
//		
		// Start the threads
		for (Thread t : theThreads){
			t.start();
		}
	}

	private class Instructions extends JPanel{
		private Instructions(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			this.add(new JLabel("INSTRUCTIONS", (int) JLabel.LEFT_ALIGNMENT));

			JTextArea text = new JTextArea();
			JScrollPane scroll = new JScrollPane(text);
			scroll.setPreferredSize(dimension);
			this.add(scroll);

		}
	}
	
	public class MyKeyListener implements KeyListener{

		public void keyPressed(KeyEvent e) {
			int c = e.getKeyCode();

			if(c == KeyEvent.VK_1)
			{
				((View) (theCars.get(0))).changePostion(-5);
			}
			if(c == KeyEvent.VK_2)
			{
				((View) (theCars.get(1))).changePostion(-5);
			}
			if(c == KeyEvent.VK_3)
			{
				((View) (theCars.get(2))).changePostion(-5);
			}
			if(c == KeyEvent.VK_4)
			{
				((View) (theCars.get(3))).changePostion(-5);
			}
			if(c == KeyEvent.VK_Q)
			{
				((View) (theCars.get(0))).changePostion(5);
			}
			if(c == KeyEvent.VK_W)
			{
				((View) (theCars.get(1))).changePostion(5);
			}
			if(c == KeyEvent.VK_E)
			{
				((View) (theCars.get(2))).changePostion(5);
			}
			if(c == KeyEvent.VK_R)
			{
				((View) (theCars.get(3))).changePostion(5);
			}
		}

		public void keyReleased(KeyEvent e) {
			
		}

		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	
	public static void setToolTipRecursively(JComponent c, String text) {

	    c.setToolTipText(text);

	    for (Component cc : c.getComponents())
	        if (cc instanceof JComponent)
	            setToolTipRecursively((JComponent) cc, text);
	}
	
	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				@SuppressWarnings("unused")
				Controller theController = new Controller();
				
			}
		});
	}
}
