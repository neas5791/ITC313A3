package iTC313A3T2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Controller extends JFrame{
	private List<View> theCars;
	private List<Thread> theThreads;
	private Dimension dimension;
	
	public Controller(){
		this.setTitle("Assignment 3 - Task 2 The Wacky Races ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		
		this.addKeyListener(new MyKeyListener());
		this.setFocusable(true);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private void init() {
		theCars = new LinkedList<View>();
		theThreads = new LinkedList<Thread>();
		dimension = new Dimension(175,150);
		// Creates four objects
		for (int i = 0; i < 4; i++){
			theCars.add(new View(i));
		}
		
		this.setLayout(new BorderLayout());
		// Sets the layout and size based on number of objects
		JPanel body = new JPanel(new GridLayout(0, theCars.size()));
       

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
			v.setPreferredSize(dimension);
			body.add(v);
		}

		this.add(body, BorderLayout.CENTER);
		this.add(new Instructions(), BorderLayout.SOUTH);
		// Start the threads
		for (Thread t : theThreads){
			t.start();
		}
	}

	private class Instructions extends JPanel{
		private Instructions(){
			Dimension area = new Dimension(190 * theCars.size(),150);
			JTextArea text = new JTextArea();
			text.setPreferredSize(area);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			text.setFocusable(false);

			JScrollPane scroll = new JScrollPane(text);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setPreferredSize(area);
			
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
	
	@SuppressWarnings("unused")
	private static void setToolTipRecursively(JComponent c, String text) {

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
